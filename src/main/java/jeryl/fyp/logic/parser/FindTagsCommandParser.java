package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import jeryl.fyp.logic.commands.FindTagsCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.TagsContainKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTagsCommand object
 */
public class FindTagsCommandParser implements Parser<FindTagsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTagsCommand
     * and returns a FindTagsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTagsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagsCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = Arrays.stream(trimmedArgs.split("/"))
                .map(keyword -> keyword.trim())
                .filter(keyword -> !keyword.equals(""))
                .toArray(size -> new String[size]); // split by "/" then trim each

        return new FindTagsCommand(new TagsContainKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
