package seedu.address.logic.parser.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.contact.CopyContactEmailCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new CopyContactEmailCommandParser object.
 */
public class CopyContactEmailCommandParser implements Parser<CopyContactEmailCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CopyContactEmailCommand
     * and returns a CopyContactEmailCommand object for execution.
     *
     * @param arg input from user.
     * @return CopyContactEmailCommand object.
     * @throws ParseException If the user input does not conform the expected format
     */
    public CopyContactEmailCommand parse(String arg) throws ParseException {
        requireNonNull(arg);
        String trimmedArg = arg.trim();

        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyContactEmailCommand.MESSAGE_USAGE));
        }

        List<String> tags = List.of(trimmedArg);
        return new CopyContactEmailCommand(new PersonContainsKeywordsPredicate(ParserUtil.parseTags(tags)));
    }
}
