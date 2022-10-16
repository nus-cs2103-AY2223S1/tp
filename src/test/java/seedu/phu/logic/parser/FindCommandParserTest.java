package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.phu.commons.core.keyword.Keyword;
import seedu.phu.commons.core.keyword.KeywordList;
import seedu.phu.logic.commands.FindCommand;
import seedu.phu.model.internship.ContainsKeywordsPredicate;
import seedu.phu.model.internship.FindableCategory;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        KeywordList keywords1 = prepareKeywords("Alice", "Bob");
        KeywordList keywords2 = prepareKeywords("02-02-2022", "10-12-2022");


        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ContainsKeywordsPredicate(
                        keywords1, FindableCategory.COMPANY_NAME));

        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);

        // Tests all possible categories
        FindableCategory[] categories = FindableCategory.values();

        for (FindableCategory category : categories) {
            String input = CliSyntax.PREFIX_CATEGORY + category.name() + " 02-02-2022 10-12-2022";
            FindCommand expected = (new FindCommand(
                    new ContainsKeywordsPredicate(keywords2, category)
            ));

            assertParseSuccess(parser, input, expected);
        }
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid category
        assertParseFailure(parser, "c/Random 123", FindableCategoryParser.EXCEPTION_MESSAGE);

        // No Keywords Given
        assertParseFailure(parser, "c/n", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Date category with invalid date keywords
        assertParseFailure(parser, "c/d 2022-02-02", FindCommandParser.CONSTRAINT_MESSAGE);
    }

    private KeywordList prepareKeywords(String ...keywords) {
        KeywordList keywordList = new KeywordList();

        for (String k : keywords) {
            keywordList.addKeyword(new Keyword(k));
        }

        return keywordList;
    }
}
