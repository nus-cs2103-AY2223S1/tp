package seedu.taassist.commons.core;

import seedu.taassist.logic.commands.HelpCommand;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_EMPTY_COMMAND = "Use the [ " + HelpCommand.COMMAND_WORD + " ] command to view "
            + "all available commands and their usages.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format for [ %1$s ]: \n%2$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX =
            "One or more student indices provided are invalid.";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d student(s) listed!";
    public static final String MESSAGE_MODULE_CLASS_DOES_NOT_EXIST = "A provided class does not exist.\n"
            + "Existing classes: %1$s";
    public static final String MESSAGE_NOT_IN_FOCUS_MODE = "Usage of [ %s ] requires you to be in focus mode to be "
            + "used!";
    public static final String MESSAGE_INVALID_SESSION = "The session [ %1$s ] does not exist in class [ %2$s ].";
}
