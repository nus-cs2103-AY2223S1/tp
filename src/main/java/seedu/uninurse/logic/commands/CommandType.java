package seedu.uninurse.logic.commands;

/**
 * CommandType is an enum which contains the different types of commands in UniNurse.
 */
public enum CommandType {
    TEST, EMPTY, HELP, EXIT, TASK, ADD_PATIENT, EDIT_PATIENT, DELETE_PATIENT, ADD_CONDITION, DELETE_CONDITION, CLEAR,
    LIST, SCHEDULE, FIND, UNDO, REDO;
}
