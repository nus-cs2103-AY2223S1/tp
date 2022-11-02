package seedu.masslinkers.logic.parser;

import static seedu.masslinkers.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;

import java.util.Arrays;

import seedu.masslinkers.logic.commands.FindInterestCommand;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.student.StudentContainsInterestPredicate;

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
                    String.format(MESSAGE_MISSING_ARGUMENTS, FindInterestCommand.MESSAGE_USAGE));
        }

        String[] interestNameKeywords = trimmedArgs.split("\\s+");

        return new FindInterestCommand(new StudentContainsInterestPredicate(Arrays.asList(interestNameKeywords)));
    }

}
