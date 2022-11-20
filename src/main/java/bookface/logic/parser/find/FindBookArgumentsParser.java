package bookface.logic.parser.find;

import java.util.Arrays;

import bookface.commons.core.Messages;
import bookface.commons.util.StringUtil;
import bookface.logic.commands.find.FindBookCommand;
import bookface.logic.parser.Parseable;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.ObjectContainsKeywordsPredicate;

/**
 * Parses input arguments and creates the relevant new FindBookCommand object for the relevant entity to be added
 */
public class FindBookArgumentsParser implements Parseable<FindBookCommand> {
    /**
     * Parses the given arguments in the context of the FindBookCommand
     * and returns a FindBookCommand object for execution.
     */
    @Override
    public FindBookCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindBookCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindBookCommand(new ObjectContainsKeywordsPredicate<>(Arrays.asList(nameKeywords),
                book -> keyword -> StringUtil.containsPartialWordIgnoreCase(book.getTitle().toString(), keyword)
                        || StringUtil.containsPartialWordIgnoreCase(book.getAuthor().toString(), keyword)));
    }
}
