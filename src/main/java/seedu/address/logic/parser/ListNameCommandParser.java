package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.list.ListNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListNameCommandParser implements Parser<ListNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListNameCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ListNameCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
    /**
     * Gets the predicate for the ListNameCommand.
     * @param keywords string of keywords to be parsed.
     * @return the predicate to filter the list with.
     * @throws ParseException if the given {@code keywords} is invalid.
     */
    public NameContainsKeywordsPredicate getPredicate(String keywords) throws ParseException {
        return new NameContainsKeywordsPredicate(ParserUtil.parseKeywords(keywords));
    }

}
