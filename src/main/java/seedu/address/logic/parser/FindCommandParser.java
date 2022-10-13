package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.keyword.Keyword;
import seedu.address.commons.core.keyword.KeywordList;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.FindableCategory;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    public static final String CONSTRAINT_MESSAGE =
            "For the date category, all the keywords must be a valid date in dd-mm-yyyy format";
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        //Default category is company name
        String lastCategoryString = null;
        FindableCategory category = FindableCategory.COMPANY_NAME;
        KeywordList keywords = new KeywordList();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] splittedArgs = trimmedArgs.split("\\s+");

        for (int i = 0; i < splittedArgs.length; ++i) {
            if (splittedArgs[i].startsWith(String.valueOf(CliSyntax.PREFIX_CATEGORY))) {
                String categoryString = splittedArgs[i]
                        .split(String.valueOf(CliSyntax.PREFIX_CATEGORY), 2)[1];
                lastCategoryString = categoryString;
                continue;
            }

            keywords.addKeyword(new Keyword(splittedArgs[i]));
        }

        //at least one keyword must be specified
        if (keywords.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (lastCategoryString != null) {
            category = FindableCategoryParser.parse(lastCategoryString);
        }

        if (category == FindableCategory.DATE && !keywords.isAllKeywordDate()) {
            throw new ParseException(CONSTRAINT_MESSAGE);
        }

        return new FindCommand(new ContainsKeywordsPredicate(keywords, category));
    }
}
