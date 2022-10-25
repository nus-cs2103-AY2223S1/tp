package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXPENDITURE_BY_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.EXPENDITURE_BY_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRAPH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_EXPENDITURE;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_GRAPH_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_GRAPH_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_INCOME;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_MONTH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewCommand.ViewEntriesDescriptor;
import seedu.address.testutil.ViewEntriesDescriptorBuilder;

public class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        String validUserInputForExpenditureMonth = TYPE_EXPENDITURE + TYPE_MONTH + TYPE_GRAPH_MONTH;
        assertParseSuccess(parser, validUserInputForExpenditureMonth, new ViewCommand(EXPENDITURE_BY_MONTH));

        String validUserInputForExpenditureCategory = TYPE_EXPENDITURE + TYPE_GRAPH_CATEGORY;
        assertParseSuccess(parser, validUserInputForExpenditureCategory, new ViewCommand(EXPENDITURE_BY_CATEGORY));

        String validUserInputForExpenditureCategoryWithMonth = TYPE_EXPENDITURE + TYPE_GRAPH_CATEGORY + TYPE_MONTH;
        ViewEntriesDescriptor expenditureCategoryWithMonthDescriptor = new ViewEntriesDescriptorBuilder(
                EXPENDITURE_BY_CATEGORY
        ).build();

        assertParseSuccess(parser, validUserInputForExpenditureCategoryWithMonth,
                new ViewCommand(expenditureCategoryWithMonthDescriptor));
    }

    @Test
    public void parse_invalidValid_throwsParseException() {
        String invalidUserInputForMonth = TYPE_INCOME + INVALID_MONTH + TYPE_GRAPH_MONTH;
        assertParseFailure(
                parser,
                invalidUserInputForMonth,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        String invalidUserInputForEntryType = INVALID_TYPE + TYPE_MONTH + TYPE_GRAPH_MONTH;
        assertParseFailure(
                parser,
                invalidUserInputForEntryType,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        String invalidUserInputForGraphType = TYPE_EXPENDITURE + TYPE_MONTH + INVALID_GRAPH;
        assertParseFailure(
                parser,
                invalidUserInputForGraphType,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_throwsParseException() {
        // Specified 'm' graph type without providing a valid month
        String invalidUserInputForMonthGraphType = TYPE_INCOME + TYPE_GRAPH_MONTH;
        assertParseFailure(
                parser,
                invalidUserInputForMonthGraphType,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // Missing graph type
        assertParseFailure(
                parser,
                TYPE_INCOME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // Missing entry type
        assertParseFailure(
                parser,
                TYPE_GRAPH_MONTH + TYPE_MONTH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
