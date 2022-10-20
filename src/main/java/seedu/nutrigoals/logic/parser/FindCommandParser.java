package seedu.nutrigoals.logic.parser;

import seedu.nutrigoals.commons.core.Messages;
import seedu.nutrigoals.logic.commands.FindCommand;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.meal.Name;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        if (args.isBlank()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(args.toLowerCase());
        return new FindCommand(name);
    }
}
