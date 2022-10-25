package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import jeryl.fyp.logic.commands.FindStudentIdCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.StudentIdContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindStudentIdCommand object
 */
public class FindStudentIdCommandParser implements Parser<FindStudentIdCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindStudentIdCommand
     * and returns a FindStudentIdCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStudentIdCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStudentIdCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = Arrays.stream(trimmedArgs.split("/"))
                .map(keyword -> keyword.trim())
                .filter(keyword -> !keyword.equals(""))
                .toArray(size -> new String[size]); // split by "/" then trim each

        return new FindStudentIdCommand(new StudentIdContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
