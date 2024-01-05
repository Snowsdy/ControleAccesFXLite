package fr.snowsdy.controleaccesfx.logger;

import static fr.snowsdy.controleaccesfx.logger.LogLevel.LOG_WARNING;

public final class CustomWarningLogger extends AbstractCustomLogger {
    public CustomWarningLogger(String message) {
        super(LOG_WARNING, message);
    }
}
