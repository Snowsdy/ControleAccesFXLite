package fr.snowsdy.controleaccesfx.logger;

import static fr.snowsdy.controleaccesfx.logger.LogLevel.LOG_NETWORK;

public final class CustomNetworkLogger extends AbstractCustomLogger {
    public CustomNetworkLogger(String message) {
        super(LOG_NETWORK, message);
    }
}
