package seedu.uninurse.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_INVALID_PATIENT = "The person you selected is not a patient";

    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_MEDICATION_INDEX = "The medication index provided is invalid";
    public static final String MESSAGE_INVALID_TASK_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_INVALID_CONDITION_INDEX = "The condition index provided is invalid";
    public static final String MESSAGE_INVALID_REMARK_INDEX = "The remark index provided is invalid";
    public static final String MESSAGE_INVALID_TAG_INDEX = "The tag index provided is invalid";

    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in %1$s's task list";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in %1$s's tag list";
    public static final String MESSAGE_DUPLICATE_CONDITION = "This condition already exists in %1$s's condition list";
    public static final String MESSAGE_DUPLICATE_MEDICATION =
            "This medication already exists in %1$s's medication list";
    public static final String MESSAGE_DUPLICATE_REMARK = "This remark already exists in %1$s's remark list";
}
