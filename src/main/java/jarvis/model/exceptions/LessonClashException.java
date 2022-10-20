package jarvis.model.exceptions;

/**
 * Signals that the operation will result in a clash in time period for Lessons
 * There cannot be 2 or more Lesson ongoing at the same time.
 */
public class LessonClashException extends RuntimeException {
    public LessonClashException() {
        super("Operation would result in a clash in time period");
    }
}
