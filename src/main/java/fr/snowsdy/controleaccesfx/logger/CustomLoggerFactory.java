package fr.snowsdy.controleaccesfx.logger;

import static fr.snowsdy.controleaccesfx.logger.LogLevel.*;

public class CustomLoggerFactory {

    public static AbstractCustomLogger getInfoLogger(String message) {
        return CustomLoggerSingleton.getInstance(LOG_INFO, message);
    }

    public static AbstractCustomLogger getWarningLogger(String message) {
        return CustomLoggerSingleton.getInstance(LOG_WARNING, message);
    }

    public static AbstractCustomLogger getDangerLogger(String message) {
        return CustomLoggerSingleton.getInstance(LOG_DANGER, message);
    }

    public static AbstractCustomLogger getNetworkLogger(String message) {
        return CustomLoggerSingleton.getInstance(LOG_NETWORK, message);
    }
}
