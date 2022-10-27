package seedu.condonery.logic.parser.client;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.condonery.logic.commands.client.FindClientCommand;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.client.ClientNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindClientCommandParser implements Parser<FindClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindClientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindClientCommand(new ClientNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
