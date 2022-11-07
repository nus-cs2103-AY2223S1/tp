package jarvis.model.exceptions;

/**
 * Signals that the operation will result in a clash in time period for Lessons
 * There cannot be 2 or more Lesson ongoing at the same time.
 */
public class LessonClashException extends RuntimeException {
    public static final String MESSAGE = "Operation would result in a clash in time period";

    public LessonClashException() {
        super(MESSAGE);
    }
}
