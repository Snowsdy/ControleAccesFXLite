package fr.snowsdy.controleaccesfx.logger;

public abstract class AbstractCustomLogger {
    private final LogLevel logLevel;

    private final String message;

    @Override
    public String toString() {
        return "[" + this.logLevel + "]"
                + " " + this.message;
    }

    public AbstractCustomLogger(LogLevel logLevel, String message) {
        this.logLevel = logLevel;
        this.message = message;
    }
}
