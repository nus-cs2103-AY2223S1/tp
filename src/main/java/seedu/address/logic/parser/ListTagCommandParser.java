package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.list.ListTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListTagCommand object
 */
public class ListTagCommandParser implements Parser<ListTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListTagCommand
     * and returns a ListTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTagCommand parse(String args) throws ParseException {
        String[] listTypes = args.split(" ", 2);

        if (listTypes.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Tag.MESSAGE_CONSTRAINTS));
        }

        return new ListTagCommand(getPredicate(listTypes[1]));
    }

    public TagContainsKeywordsPredicate getPredicate(String keywords) throws ParseException {
        ParserUtil.parseTag(keywords);

        return new TagContainsKeywordsPredicate(List.of(keywords));
    }
}
