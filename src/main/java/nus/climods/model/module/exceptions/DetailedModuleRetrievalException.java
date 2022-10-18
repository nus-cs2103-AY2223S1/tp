package nus.climods.model.module.exceptions;

/**
 * Exception thrown when there was an error retrieving detailed module information
 */
public class DetailedModuleRetrievalException extends Exception {
    public DetailedModuleRetrievalException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
