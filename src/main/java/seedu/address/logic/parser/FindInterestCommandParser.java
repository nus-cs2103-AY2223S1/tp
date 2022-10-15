package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindInterestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsInterestPredicate;

/**
 * Parses input arguments and creates a new FindInterestCommandParser object
 */
public class FindInterestCommandParser implements Parser<FindInterestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindInterestCommand
     * and returns a FindInterestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindInterestCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInterestCommand.MESSAGE_USAGE));
        }

        String[] interestNameKeywords = trimmedArgs.split("\\s+");

        return new FindInterestCommand(new PersonContainsInterestPredicate(Arrays.asList(interestNameKeywords)));
    }

}
