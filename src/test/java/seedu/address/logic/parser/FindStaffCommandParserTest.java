package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindStaffCommand;
import seedu.address.model.staff.StaffNameContainsKeywordsPredicate;

public class FindStaffCommandParserTest {

    private FindStaffCommandParser parser = new FindStaffCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindStaffCommand.MESSAGE_USAGE));
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
