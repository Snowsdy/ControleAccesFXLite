package fr.snowsdy.controleaccesfx.logger;

public enum LogLevel {
    LOG_INFO,
    LOG_WARNING,
    LOG_DANGER;

    @Override
    public String toString() {
        return this.name();
    }
}
