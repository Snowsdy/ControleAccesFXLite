package fr.snowsdy.controleaccesfx.logger;

import static fr.snowsdy.controleaccesfx.logger.LogLevel.LOG_DANGER;

public class CustomDangerLogger extends AbstractCustomLogger{
    public CustomDangerLogger(String message) {
        super(LOG_DANGER, message);
    }
}
