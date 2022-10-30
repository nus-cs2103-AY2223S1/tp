package seedu.address.logic.commands;

/**
 * Represents a command that list entities.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String FEEDBACK_MESSAGE = "Valid list command format:\n"
            + "list student/tutor/class\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists either students, tutors or classes in the database.\n"
            + "\nParameters:\n"
            + "student OR\n"
            + "tutor OR\n"
            + "class \n"
            + "\nExample: \n" + COMMAND_WORD + " student\n";
}
