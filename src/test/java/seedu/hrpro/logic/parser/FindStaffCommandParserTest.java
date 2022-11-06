package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.FindStaffCommand;
import seedu.hrpro.model.staff.StaffName;
import seedu.hrpro.model.staff.StaffNameContainsKeywordsPredicate;

public class FindStaffCommandParserTest {

    private FindStaffCommandParser parser = new FindStaffCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindStaffCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "12", StaffName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgWithInvalidArg_throwsParseException() {
        assertParseFailure(parser, "alan ()", StaffName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArg_returnsFindStaffCommand() {
        // no spaces in name
        FindStaffCommand expectedFindStaffCommand =
                new FindStaffCommand(new StaffNameContainsKeywordsPredicate(Arrays.asList("Alex")));
        assertParseSuccess(parser, "Alex", expectedFindStaffCommand);

        // whitespace inside keyword
        FindStaffCommand expectedFindStaffCommandWithSpaces =
                new FindStaffCommand(new StaffNameContainsKeywordsPredicate(Arrays.asList("Alex", "Lee")));
        assertParseSuccess(parser, "Alex Lee", expectedFindStaffCommandWithSpaces);
    }
}
