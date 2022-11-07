package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.phu.logic.commands.ListCommand;
import seedu.phu.logic.parser.exceptions.InvalidCategoryException;
import seedu.phu.logic.parser.exceptions.ParseException;
import seedu.phu.model.internship.ComparableCategory;



/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();

        if (trimmedArgs.length() == 0) {
            return new ListCommand(ComparableCategory.NULL, false);
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);
        if (!argMultimap.getValue(PREFIX_CATEGORY).isPresent() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
        try {
            String words = argMultimap.getValue(PREFIX_CATEGORY).get();
            String[] keywords = words.split("\\s+");
            ComparableCategory category = ComparableCategoryParser.parse(keywords[0]);
            boolean isDescending = keywords[keywords.length - 1].equalsIgnoreCase("true");
            return new ListCommand(category, isDescending);
        } catch (InvalidCategoryException ice) {
            throw ice;
        }
    }
}
