package seedu.uninurse.logic.commands;

/**
 * CommandType is an enum which contains the different types of commands in UniNurse.
 */
public enum CommandType {
    TEST,
    EMPTY,
    HELP,
    EXIT,
    ADD_PATIENT,
    EDIT_PATIENT,
    DELETE_PATIENT,
    LIST,
    TASK,
    SCHEDULE,
    FIND,
    CLEAR,
    UNDO,
    REDO;
}
