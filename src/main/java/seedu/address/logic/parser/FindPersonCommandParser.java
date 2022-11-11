package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameStartsWithKeywordPredicate;

/**
 * Parses input arguments and creates a new {@code FindPersonCommand} object.
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code FindPersonCommand}
     * and returns a {@code FindPersonCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        return new FindPersonCommand(new NameStartsWithKeywordPredicate(trimmedArgs));
    }
}
