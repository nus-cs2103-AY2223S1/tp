package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.ReminderCommandGroup;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for the ReminderCommandGroup, and returns the desired command
 * in the ReminderCommandGroup, as indicated by the commandSpecifier.
 */
public class ReminderCommandGroupParser implements Parser<ReminderCommandGroup> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * and returns a command in the ReminderCommandGroup for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReminderCommandGroup parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            /*
            String errorMessage = String.format("%s\n\n%s\n\n%s",
                    CreateTagCommand.MESSAGE_USAGE,
                    TagCommand.MESSAGE_USAGE,
                    RemoveTagCommand.MESSAGE_USAGE);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
             */
        }

        String[] argArray = trimmedArgs.split("\\s+");
        String commandSpecifier = argArray[0];

        switch (commandSpecifier) {
        default:
            return new ReminderCreateCommandParser().parse(trimmedArgs);
        }
    }
}
