package seedu.address.logic.commands.event;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.logic.commands.Command;

/**
 * Represents a Event command with hidden internal logic and the ability to be executed.
 */
public abstract class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String EVENT_FORMAT = "Format: " + COMMAND_WORD + " -option\n";

    public static final String VALID_FLAGS = "Please use one of the following valid flags: "
            + PREFIX_OPTION + AddEventCommand.COMMAND_OPTION + ", "
            + PREFIX_OPTION + EditEventCommand.COMMAND_OPTION + ", "
            + PREFIX_OPTION + ViewEventsCommand.COMMAND_OPTION + ".";

    public static final String OPTION_UNKNOWN = "That is not a valid option flag.\n";

    public static final String OPTION_NO_MULTIPLE = "Multiple option flags are not allowed.\n" + EVENT_FORMAT;

    public static final String OPTION_WRONG_ORDER = "Option flag should be in front.\n" + EVENT_FORMAT;

    public static final String OPTION_WRONG_ORDER_NO_MULTIPLE =
            "Only one option flag should be specified in front.\n" + EVENT_FORMAT;

}
