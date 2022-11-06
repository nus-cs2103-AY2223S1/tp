package jarvis.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = "The lesson index provided is invalid";
    public static final String MESSAGE_INVALID_LESSON_TYPE = "The lesson at given lesson index is not a studio";
    public static final String MESSAGE_LESSONS_LISTED_OVERVIEW = "%1$d lessons listed!";
    public static final String MESSAGE_INVALID_PARTICIPATION = "The participation value provided should be an "
            + "integer from 0 to 500";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student %1$s cannot be found in lesson %2$s";
    public static final String MESSAGE_OVERALL_NOTE_NOT_FOUND = "The overall note at index %1$d for %2$s cannot be "
            + "found";
    public static final String MESSAGE_STUDENT_NOTE_NOT_FOUND = "The student note at index %1$d for %2$s in %3$s "
            + "cannot be found";
}
