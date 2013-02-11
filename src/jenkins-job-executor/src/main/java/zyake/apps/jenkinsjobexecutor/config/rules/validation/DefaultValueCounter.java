package zyake.apps.jenkinsjobexecutor.config.rules.validation;

public class DefaultValueCounter implements ValueCounter {

    private int count;

    public DefaultValueCounter(int count) {
        this.count = count;
    }

    @Override
    public boolean checkCount(int size) {
        boolean result;
        if ( count < 0 ) {
            return size > 0;
        }

        return count == size;
    }

    @Override
    public String toString() {
        return count < 0 ? "more than one" : Integer.toString(count);
    }
}
