package gim.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Exercises (Exercises are considered duplicates if they have the same
 * identity).
 */
public class DuplicateExerciseException extends RuntimeException {
    public DuplicateExerciseException() {
        super("Operation would result in duplicate persons");
    }
}
