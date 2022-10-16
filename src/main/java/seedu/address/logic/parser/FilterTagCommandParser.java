package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.TagContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterTagCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTagCommand
     * and returns a FindTagCommand object for execution.
     * @param args String of arguments to be parsed
     * @return FindTagCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTagCommand.MESSAGE_USAGE));
        }

        String[] keyword = trimmedArgs.split("\\s+");

        return new FilterTagCommand(new TagContainsKeywordPredicate(keyword[0]));
    }
}
