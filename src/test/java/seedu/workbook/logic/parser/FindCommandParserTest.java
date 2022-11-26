package seedu.workbook.logic.parser;

import static seedu.workbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.workbook.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.NO_ARGS_COMPANY;
import static seedu.workbook.logic.commands.CommandTestUtil.NO_ARGS_ROLE;
import static seedu.workbook.logic.commands.CommandTestUtil.NO_ARGS_STAGE;
import static seedu.workbook.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.STAGE_DESC_AMY;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.workbook.logic.commands.FindCommand;
import seedu.workbook.model.internship.CompanyContainsKeywordsPredicate;
import seedu.workbook.model.internship.RoleContainsKeywordsPredicate;
import seedu.workbook.model.internship.StageContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedCompanyFindCommand =
                new FindCommand(new CompanyContainsKeywordsPredicate(Arrays.asList("Amy", "Bee")));
        assertParseSuccess(parser, COMPANY_DESC_AMY, expectedCompanyFindCommand);

        FindCommand expectedStageFindCommand =
                new FindCommand(new StageContainsKeywordsPredicate(Arrays.asList("Technical", "Interview")));
        assertParseSuccess(parser, STAGE_DESC_AMY, expectedStageFindCommand);

        FindCommand expectedRoleFindCommand =
                new FindCommand(new RoleContainsKeywordsPredicate(Arrays.asList("Software", "Engineer")));
        assertParseSuccess(parser, ROLE_DESC_AMY, expectedRoleFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, COMPANY_DESC_AMY, expectedCompanyFindCommand);
        assertParseSuccess(parser, STAGE_DESC_AMY, expectedStageFindCommand);
        assertParseSuccess(parser, ROLE_DESC_AMY, expectedRoleFindCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no searching for company and role in the same command
        assertParseFailure(parser, COMPANY_DESC_AMY
                + ROLE_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        //no prefix provided
        assertParseFailure(parser, "amy bee", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArguments_throwsParseException() {
        assertParseFailure(parser, NO_ARGS_COMPANY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, NO_ARGS_ROLE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, NO_ARGS_STAGE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
