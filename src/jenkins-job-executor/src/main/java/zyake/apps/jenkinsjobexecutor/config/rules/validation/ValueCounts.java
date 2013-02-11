package zyake.apps.jenkinsjobexecutor.config.rules.validation;

public enum ValueCounts {
    NoValue(new DefaultValueCounter(0)),
    Once(new DefaultValueCounter(1)),
    Many(new DefaultValueCounter(-1));

    private ValueCounter counter;

    private ValueCounts(ValueCounter counter) {
        this.counter = counter;
    }

    public ValueCounter getCounter() {
        return counter;
    }
}
