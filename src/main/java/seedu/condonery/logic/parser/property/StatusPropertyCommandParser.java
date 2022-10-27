package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.condonery.logic.commands.property.StatusPropertyCommand;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.property.PropertyStatusContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class StatusPropertyCommandParser implements Parser<StatusPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TypeCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatusPropertyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusPropertyCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new StatusPropertyCommand(new PropertyStatusContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
