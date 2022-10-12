package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_EXPENDITURE;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_INCOME;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONTH_MARCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.Month;

public class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        Month month = new Month(VALID_MONTH_MARCH);

        EntryType expenditureType = new EntryType(VALID_TYPE_EXPENDITURE);
        String validUserInputForExpenditure = TYPE_EXPENDITURE + TYPE_MONTH;

        assertParseSuccess(parser, validUserInputForExpenditure, new ViewCommand(expenditureType, month));

        EntryType incomeType = new EntryType(VALID_TYPE_INCOME);
        String validUserInputForIncome = TYPE_INCOME + TYPE_MONTH;
        assertParseSuccess(parser, validUserInputForIncome, new ViewCommand(incomeType, month));
    }

    @Test
    public void parse_invalidEntryType_throwsParseException() {
        String invalidUserInputForEntryType = INVALID_TYPE + TYPE_MONTH;

        assertParseFailure(
                parser,
                invalidUserInputForEntryType,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMonth_throwsParseException() {
        String invalidUserInputForMonth = TYPE_INCOME + INVALID_MONTH;

        assertParseFailure(
                parser,
                invalidUserInputForMonth,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
