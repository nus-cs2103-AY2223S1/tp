package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ReminderClearCommand;
import seedu.address.logic.commands.ReminderCommandGroup;
import seedu.address.logic.commands.ReminderCreateCommand;
import seedu.address.logic.commands.ReminderDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for the ReminderCommandGroup, and returns the desired command
 * in the ReminderCommandGroup, as indicated by the commandSpecifier.
 */
public class ReminderCommandGroupParser implements Parser<ReminderCommandGroup> {
    public static final String MESSAGE_USAGE = String.format("%s\n\n%s\n\n%s",
                    ReminderCreateCommand.MESSAGE_USAGE,
                    ReminderDeleteCommand.MESSAGE_USAGE,
                    ReminderClearCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} of arguments in the context of the
     * and returns a command in the ReminderCommandGroup for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReminderCommandGroup parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        String[] argArray = args.trim().split("\\s+", 2);
        String commandSpecifier = argArray[0];

        switch (commandSpecifier) {
        case ReminderClearCommand.COMMAND_SPECIFIER:
            return new ReminderClearCommand();
        case ReminderDeleteCommand.COMMAND_SPECIFIER:
            if (argArray.length < 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderDeleteCommand.MESSAGE_USAGE));
            }
            return new ReminderDeleteCommandParser().parse(argArray[1]);
        default:
            if (argArray.length < 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
            }
            return new ReminderCreateCommandParser().parse(args);
        }
    }
}
