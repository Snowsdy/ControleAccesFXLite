package fr.snowsdy.controleaccesfx.logger;

public class CustomLoggerSingleton {
    private static AbstractCustomLogger logger = null;

    private CustomLoggerSingleton() {
    }

    public static AbstractCustomLogger getInstance(LogLevel level, String message) {
        if (logger == null) {
            switch (level) {
                case LOG_INFO -> logger = new CustomInfoLogger(message);
                case LOG_WARNING -> logger = new CustomWarningLogger(message);
                case LOG_DANGER -> logger = new CustomDangerLogger(message);
            }
        }

        return logger;
    }
}
