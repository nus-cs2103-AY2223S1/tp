package seedu.classify.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The index provided is invalid!";
    public static final String MESSAGE_SINGLE_PERSON_LISTED_OVERVIEW = "%1$d student found!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d students found!";
    public static final String MESSAGE_INVALID_STUDENT_ID = "The student ID provided does not exist!";
    public static final String MESSAGE_INVALID_STUDENT_NAME = "The student name provided does not exist!";
    public static final String MESSAGE_STUDENT_CLASS_NOT_FOUND = "The class provided does not exist!";
    public static final String MESSAGE_PERSONS_LISTED_IN_CLASS = "There are %1$d students in this class!";
    public static final String MESSAGE_SINGLE_PERSON_LISTED_IN_CLASS = "There is %1$d student in this class!";
    public static final String MESSAGE_CLASS_SORTED_BY_GRADE = "Students of class %s sorted by grade!\n";
    public static final String MESSAGE_DISPLAY_MEAN = "Mean of %s for class %s is %.2f";
    public static final String MESSAGE_DELETE_COMMAND_DOUBLE_INPUT = "Name and ID inputs detected. "
            + "Please specify either name or ID only.";
}
