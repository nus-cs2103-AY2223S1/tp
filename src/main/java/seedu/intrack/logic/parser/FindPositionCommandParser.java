package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.intrack.logic.commands.FindPositionCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.PositionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new {@code FindPositionCommand} object
 */
public class FindPositionCommandParser implements Parser<FindPositionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code FindPositionCommand}
     * and returns a {@code FindPositionCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPositionCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPositionCommand.MESSAGE_USAGE));
        }

        String[] posKeywords = trimmedArgs.split("\\s+");

        return new FindPositionCommand(new PositionContainsKeywordsPredicate(Arrays.asList(posKeywords)));
    }

}
