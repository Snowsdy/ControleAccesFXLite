package fr.snowsdy.controleaccesfx.logger;

import static fr.snowsdy.controleaccesfx.logger.LogLevel.LOG_INFO;

public final class CustomInfoLogger extends AbstractCustomLogger {
    public CustomInfoLogger(String message) {
        super(LOG_INFO, message);
    }
}
