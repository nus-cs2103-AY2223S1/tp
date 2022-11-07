package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagsContainKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindTagCommandParser implements Parser<FindTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindTagCommand object for execution.
     * @return A new command to find a contact
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
        }

        return new FindTagCommand(new TagsContainKeywordPredicate(trimmedArgs));
    }

}
