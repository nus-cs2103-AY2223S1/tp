package nus.climods.model.module.exceptions;

/**
 * Signals that the operation will result in duplicate Modules (Modules are considered duplicates if they have the same
 * code).
 */
public class DuplicateUserModuleException extends RuntimeException {
    public DuplicateUserModuleException() {
        super("Operation would result in duplicate modules");
    }
}
