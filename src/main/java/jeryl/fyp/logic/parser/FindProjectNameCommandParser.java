package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import jeryl.fyp.logic.commands.FindProjectNameCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.ProjectNameContainsKeywordsPredicate;
//import jeryl.fyp.model.student.TagsContainKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindProjectNameCommand object
 */
public class FindProjectNameCommandParser implements Parser<FindProjectNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindProjectNameCommand
     * and returns a FindProjectNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindProjectNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        //System.out.println(trimmedArgs);
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProjectNameCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = Arrays.stream(trimmedArgs.split("/"))
                .map(keyword -> keyword.trim())
                .filter(keyword -> !keyword.equals(""))
                .toArray(size -> new String[size]); // split by "/" then trim each

        return new FindProjectNameCommand(new ProjectNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
