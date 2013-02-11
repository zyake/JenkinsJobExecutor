package zyake.apps.jenkinsjobexecutor;

import zyake.apps.jenkinsjobexecutor.config.Argument;
import zyake.apps.jenkinsjobexecutor.config.ArgumentParser;
import zyake.apps.jenkinsjobexecutor.config.ConfigLoader;
import zyake.apps.jenkinsjobexecutor.config.ExecutorConfig;
import zyake.apps.jenkinsjobexecutor.loaders.JobLoader;
import zyake.apps.jenkinsjobexecutor.loaders.JobLoaderFactory;
import zyake.apps.jenkinsjobexecutor.reports.JobReportWriter;
import zyake.apps.jenkinsjobexecutor.reports.JobReportWriterFactory;
import zyake.apps.jenkinsjobexecutor.senders.JobRequestSender;
import zyake.apps.jenkinsjobexecutor.senders.JobRequestSenderFactory;
import zyake.apps.jenkinsjobexecutor.serializers.JobSerializer;
import zyake.apps.jenkinsjobexecutor.serializers.JobSerializerFactory;
import zyake.apps.jenkinsjobexecutor.util.ClassUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JenkinsJobExecutor {

    private static final String CONFIG_PATH = "config.properties";

    private static final Logger LOGGER = Logger.getLogger(JenkinsJobExecutor.class.getName());

    private ExecutorConfig config;

    private JobRequestSender sender;

    private JobSerializer serializer;

    private JobReportWriter reportWriter;

    private JobLoader loader;

    private Map<String, Argument> parsedArgs;

    public static void main(String[] args) {
        new JenkinsJobExecutor().execute(args);
    }

    public void execute(String[] args) {
        configure(args);
        if ( parsedArgs.containsKey("retry")) {
            System.out.println("retry...");
        } else {
            execute();
        }
    }

    private void execute() {
        if ( LOGGER.isLoggable(Level.INFO) ) {
            LOGGER.info("start execution...");
        }

        List<Job> jobs = loader.loadJobs(parsedArgs.get("url").getSingleValue());
        for ( Job job : jobs ) {
            sender.disableJob(job);
        }

        Stack<String> executionPath = new Stack<>();
        try {
            for ( Job job : jobs ) {
                doExecute(job, executionPath);
            }
        } catch (ExceedRetryCountException ex) {
            if ( LOGGER.isLoggable(Level.SEVERE) ) {
                LOGGER.log(Level.SEVERE, "exceed retry count. exception=" + ex);
            }
        }

        for ( Job job : jobs ) {
            sender.enableJob(job);
            serializer.serializeJob(job);
        }
        serializer.finish();

        reportWriter.writeJobs(jobs);
        reportWriter.finish();

        if ( LOGGER.isLoggable(Level.INFO) ) {
            LOGGER.info("end execution.");
        }
    }

    private void doExecute(Job job, Stack<String> executionPath) {
        executionPath.push(job.getName());
        for ( Job depJob :  job.getDependencies() ) {
            if ( depJob.isCompleted() ) {
                if ( LOGGER.isLoggable(Level.FINE) ) {
                    LOGGER.fine("already executed: " + job);
                }
                continue;
            }

            boolean circleRefFound = executionPath.contains(depJob);
            if( circleRefFound ) {
                throw new ExecutorException("circle reference found: " + executionPath);
            }

            executionPath.push(depJob.getName());
            doExecute(depJob, executionPath);
        }

        if ( job.isCompleted() ) {
            if ( LOGGER.isLoggable(Level.FINE) ) {
                LOGGER.fine("already executed: " + job);
            }
            return;
        }

        executionPath.pop();
        int retryCount = config.getRetryCount();
        while ( retryCount > 0 ) {
            try {
                sender.executeJob(job);
                job.setCompleted();
                return;
            } catch (Exception ex) {
                if ( LOGGER.isLoggable(Level.SEVERE) ) {
                    LOGGER.log(Level.SEVERE, "job execution failed. retrying... job=" + job +", exception="  + ex);
                }
                retryCount --;
            }
        }

        throw new ExceedRetryCountException(job);
    }

    private void configure(String[] args) {
        if ( LOGGER.isLoggable(Level.FINE) ) {
            LOGGER.fine("start configuring...");
        }

        config = ConfigLoader.loadCofig(CONFIG_PATH);
        if ( LOGGER.isLoggable(Level.FINE) ) {
            LOGGER.fine("configure=" + config);
        }

        ArgumentParser parser = ClassUtils.newInstance(config.getParser());
        JobRequestSenderFactory.configure(config.getRequestSenders());
        JobSerializerFactory.configure(config.getSerializers());
        JobReportWriterFactory.configure(config.getReportWriters());
        JobLoaderFactory.configure(config.getLoaders());

        parsedArgs = parser.parse(args);

        if ( LOGGER.isLoggable(Level.FINE) ) {
            LOGGER.fine("arguments=" + parsedArgs);
        }

        sender = JobRequestSenderFactory.newInstance((String) parsedArgs.get("sender").getSingleValue(), new HashMap<String, String>());
        serializer = JobSerializerFactory.newInstance(parsedArgs.get("serializer").getSingleValue(), new HashMap<String, String>());
        reportWriter = JobReportWriterFactory.newInstance(parsedArgs.get("output").getSingleValue(), new HashMap<String, String>());
        loader = JobLoaderFactory.newInstance(parsedArgs.get("loader").getSingleValue(), new HashMap<String, String>());

        if ( LOGGER.isLoggable(Level.FINE) ) {
            LOGGER.fine("end configuring.");
        }
    }
}
