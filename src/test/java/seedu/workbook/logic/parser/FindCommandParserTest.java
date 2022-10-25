package seedu.workbook.logic.parser;

import static seedu.workbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.workbook.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.workbook.logic.commands.FindCommand;
import seedu.workbook.model.internship.CompanyContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyContainsKeywordsPredicate(Arrays.asList("Amy", "Bee")));
        assertParseSuccess(parser, COMPANY_DESC_AMY, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, COMPANY_DESC_AMY, expectedFindCommand);
    }

}
