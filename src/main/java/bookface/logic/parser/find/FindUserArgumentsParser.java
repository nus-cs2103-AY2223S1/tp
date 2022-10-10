package bookface.logic.parser.find;

import java.util.Arrays;

import bookface.commons.core.Messages;
import bookface.commons.util.StringUtil;
import bookface.logic.commands.find.FindUserCommand;
import bookface.logic.parser.ArgumentsParsable;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.ObjectContainsKeywordsPredicate;

/**
 * Parses input arguments and creates the relevant new FindCommand object for the relevant entity to be added
 */
public class FindUserArgumentsParser implements ArgumentsParsable<FindUserCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindUserCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindUserCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindUserCommand(new ObjectContainsKeywordsPredicate<>(Arrays.asList(nameKeywords),
                person -> keyword -> StringUtil.containsPartialWordIgnoreCase(person.getName().fullName, keyword)));
    }
}
