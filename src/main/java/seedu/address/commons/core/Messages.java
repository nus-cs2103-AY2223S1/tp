package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_FEATURE_TYPE_FORMAT = "Invalid %s command format!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_INVALID_TASK_INDEX_TOO_LARGE =
        "Please provide a task index greater than 0 and less than %d";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_MODULES_LISTED_OVERVIEW = "%1$d modules listed!";
    public static final String MESSAGE_INVALID_MODULE_DISPLAYED_INDEX =
            "Please provide a positive integer for the index of a module.";
    public static final String MESSAGE_INVALID_MODULE_DELETION_AS_TIED_WITH_TASK = "The module"
            + " cannot be deleted as it is tied with an existing task";
    public static final String MESSAGE_INVALID_MODULE_EDIT_AS_TIED_WITH_TASK = "The module"
            + " cannot be edited as it is tied with an existing task";
    public static final String MESSAGE_INVALID_MODULE_INDEX_TOO_LARGE =
            "Please provide a module index greater than 0 and less than %d";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list";
    public static final String MESSAGE_INVALID_TASK_INDEX =
        "Please provide a positive integer for the index of a task.";
    public static final String MESSAGE_MODULE_NOT_FOUND = "This module does not exist";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module list";
    public static final String MESSAGE_DUPLICATE_EXAM = "This exam already exists in the exam list";
    public static final String MESSAGE_INVALID_EXAM_DISPLAYED_INDEX =
            "The exam index provided is invalid";
    public static final String MESSAGE_INVALID_EXAM_INDEX_TOO_LARGE =
            "Please provide an exam index greater than 0 and less than %d";
    public static final String MESSAGE_INVALID_EXAM_INDEX =
            "Please provide a positive integer for the index of an exam.";

}
