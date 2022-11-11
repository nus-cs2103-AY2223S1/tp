package swift.model.bridge.exceptions;

/**
 * Signals that the operation will result in duplicate PersonTaskBridge
 * (PersonTaskBridge are
 * considered duplicates if they have the same
 * person ID and task ID).
 */
public class DuplicateBridgeException extends RuntimeException {
    public DuplicateBridgeException() {
        super("Operation would result in duplicate bridges");
    }
}
