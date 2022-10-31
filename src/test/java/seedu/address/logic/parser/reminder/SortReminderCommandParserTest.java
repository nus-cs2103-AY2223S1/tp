package seedu.address.logic.parser.reminder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SORTING_CRITERIA;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_PREFIXES_ALL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.reminder.SortReminderCommand;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;

public class SortReminderCommandParserTest {

    private SortReminderCommandParser parser = new SortReminderCommandParser();

    /*
    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, SORTING_DESC_DEADLINE,
        new SortReminderCommand(SortReminderCommand.CRITERIA_DEADLINE));
    }
    */

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE, String.format(MESSAGE_MISSING_PREFIXES_ALL,
                Arrays.toString(new Prefix[]{CliSyntax.PREFIX_SORT_CRITERIA}), SortReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid criteria
        assertParseFailure(parser, INVALID_SORT_DESC, MESSAGE_INVALID_SORTING_CRITERIA);
    }
}
