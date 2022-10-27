package seedu.pennywise.logic.parser;

import static seedu.pennywise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pennywise.logic.commands.CommandTestUtil.EXPENDITURE_BY_CATEGORY;
import static seedu.pennywise.logic.commands.CommandTestUtil.EXPENDITURE_BY_MONTH;
import static seedu.pennywise.logic.commands.CommandTestUtil.INCOME_BY_CATEGORY;
import static seedu.pennywise.logic.commands.CommandTestUtil.INCOME_BY_MONTH;
import static seedu.pennywise.logic.commands.CommandTestUtil.INVALID_MONTH;
import static seedu.pennywise.logic.commands.CommandTestUtil.INVALID_TYPE;
import static seedu.pennywise.logic.commands.CommandTestUtil.TYPE_EXPENDITURE;
import static seedu.pennywise.logic.commands.CommandTestUtil.TYPE_INCOME;
import static seedu.pennywise.logic.commands.CommandTestUtil.TYPE_MONTH;
import static seedu.pennywise.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pennywise.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.pennywise.logic.commands.ViewCommand;

public class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        // Valid user input for expenditure by month
        assertParseSuccess(parser, TYPE_EXPENDITURE + TYPE_MONTH, new ViewCommand(EXPENDITURE_BY_MONTH));

        // Valid user input for expenditure by category
        assertParseSuccess(parser, TYPE_EXPENDITURE, new ViewCommand(EXPENDITURE_BY_CATEGORY));

        // Valid user input for income by month
        assertParseSuccess(parser, TYPE_INCOME + TYPE_MONTH, new ViewCommand(INCOME_BY_MONTH));

        // Valid user input for income by category
        assertParseSuccess(parser, TYPE_INCOME, new ViewCommand(INCOME_BY_CATEGORY));
    }

    @Test
    public void parse_invalidValid_throwsParseException() {
        String invalidUserInputForMonth = TYPE_INCOME + INVALID_MONTH;
        assertParseFailure(
                parser,
                invalidUserInputForMonth,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        String invalidUserInputForEntryType = INVALID_TYPE + TYPE_MONTH;
        assertParseFailure(
                parser,
                invalidUserInputForEntryType,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_throwsParseException() {
        // Missing entry type
        assertParseFailure(
                parser,
                "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(
                parser,
                TYPE_MONTH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
