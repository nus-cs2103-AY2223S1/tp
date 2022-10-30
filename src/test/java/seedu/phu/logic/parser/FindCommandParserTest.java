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
    public void parse_validArgsGeneral_returnsFindCommand() {
        KeywordList keywords = prepareKeywords("Amazon", "Blackrock");

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ContainsKeywordsPredicate(
                        keywords, FindableCategory.COMPANY_NAME));

        assertParseSuccess(parser, "Amazon Blackrock", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Amazon \n \t Blackrock  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsNameCategory_returnsFindCommand() {
        KeywordList keywords = prepareKeywords("Amazon", "Blackrock");
        String input = CliSyntax.PREFIX_CATEGORY + FindableCategory.COMPANY_NAME.name() + " Amazon Blackrock";
        FindCommand expected = (new FindCommand(
                new ContainsKeywordsPredicate(keywords, FindableCategory.COMPANY_NAME)
        ));

        assertParseSuccess(parser, input, expected);
    }

    @Test
    public void parse_validArgsPositionCategory_returnsFindCommand() {
        KeywordList keywords = prepareKeywords("Backend", "Frontend");
        String input = CliSyntax.PREFIX_CATEGORY + FindableCategory.POSITION.name() + " Backend Frontend";
        FindCommand expected = (new FindCommand(
                new ContainsKeywordsPredicate(keywords, FindableCategory.POSITION)
        ));

        assertParseSuccess(parser, input, expected);
    }

    @Test
    public void parse_validArgsApplicationProcessCategory_returnsFindCommand() {
        KeywordList keywords = prepareKeywords("APPLIED", "OFFER", "REJECTED");
        String input = CliSyntax.PREFIX_CATEGORY + FindableCategory.APPLICATION_PROCESS.name()
                + " APPLIED OFFER REJECTED";
        FindCommand expected = (new FindCommand(
                new ContainsKeywordsPredicate(keywords, FindableCategory.APPLICATION_PROCESS)
        ));

        assertParseSuccess(parser, input, expected);
    }

    @Test
    public void parse_validArgsDateCategory_returnsFindCommand() {
        KeywordList keywords = prepareKeywords("29-02-2020", "31-01-2022", "30-04-2022");
        String input3 = CliSyntax.PREFIX_CATEGORY + FindableCategory.DATE.name()
                + " 29-02-2020 31-01-2022 30-04-2022";
        FindCommand expected3 = (new FindCommand(
                new ContainsKeywordsPredicate(keywords, FindableCategory.DATE)
        ));

        assertParseSuccess(parser, input3, expected3);
    }

    @Test
    public void parse_validArgsTagsCategory_returnsFindCommand() {
        KeywordList keywords = prepareKeywords("Trading", "Startup");
        String input = CliSyntax.PREFIX_CATEGORY + FindableCategory.TAG.name() + " Trading Startup";
        FindCommand expected = (new FindCommand(
                new ContainsKeywordsPredicate(keywords, FindableCategory.TAG)
        ));

        assertParseSuccess(parser, input, expected);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid category
        assertParseFailure(parser, "c/Random 123", FindableCategoryParser.EXCEPTION_MESSAGE);

        // No Keywords Given
        assertParseFailure(parser, "c/n", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Date category with invalid date keywords
        assertParseFailure(parser, "c/d 2022-02-02", FindCommandParser.INVALID_DATE_MESSAGE);
        assertParseFailure(parser, "c/d 29-02-2022", FindCommandParser.INVALID_DATE_MESSAGE);

        //Application process category with invalid state
        assertParseFailure(parser, "c/pr NOT_A_CATEGORY", FindCommandParser.INVALID_PROCESS_MESSAGE);
    }

    private KeywordList prepareKeywords(String ...keywords) {
        KeywordList keywordList = new KeywordList();

        for (String k : keywords) {
            keywordList.addKeyword(new Keyword(k));
        }

        return keywordList;
    }
}
