package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import jeryl.fyp.logic.commands.FindStudentNameCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.StudentNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindStudentNameCommand object
 */
public class FindStudentNameCommandParser implements Parser<FindStudentNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindStudentNameCommand
     * and returns a FindStudentNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStudentNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStudentNameCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = Arrays.stream(trimmedArgs.split("/"))
                .map(keyword -> keyword.trim())
                .filter(keyword -> !keyword.equals(""))
                .toArray(size -> new String[size]); // split by "/" then trim each

        return new FindStudentNameCommand(new StudentNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
