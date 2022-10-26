package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import hobbylist.logic.commands.FilterTagCommand;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.activity.TagMatchesKeywordPredicate;

/**
 * Parses input arguments and creates a new FindTagCommand object
 */
public class FilterTagCommandParser implements Parser {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTagCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterTagCommand(new TagMatchesKeywordPredicate(Arrays.asList(nameKeywords)));
    }

}
