package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.contact.FilterContactCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterContactCommandParser object
 */
public class FilterContactCommandParser implements Parser<FilterContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterContactCommand
     * and returns a FilterContactCommand object for execution.
     *
     * @param args Raw arguments from user.
     * @return FilterContactCommand object.
     * @throws ParseException If the user input does not conform the expected format
     */
    public FilterContactCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        List<String> tags = List.of(trimmedArgs.split(" "));

        if (tags.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterContactCommand.MESSAGE_USAGE));
        }

        return new FilterContactCommand(new PersonContainsKeywordsPredicate(ParserUtil.parseTags(tags)));
    }
}
