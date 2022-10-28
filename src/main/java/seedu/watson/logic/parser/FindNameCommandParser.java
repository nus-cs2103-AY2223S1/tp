package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.watson.logic.commands.FindNameCommand;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.FindCommandPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindNameCommandParser implements Parser<FindNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNameCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindNameCommand(new FindCommandPredicate(Arrays.asList(nameKeywords)));
    }

}
