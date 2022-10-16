package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseFailure;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static paymelah.model.debt.Money.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import paymelah.logic.commands.ListDebtorsCommand;
import paymelah.model.debt.Money;
import paymelah.model.person.DebtGreaterEqualAmountPredicate;

public class ListDebtorsCommandParserTest {

    private ListDebtorsCommandParser parser = new ListDebtorsCommandParser();

    @Test
    public void parse_preamblePresent_throwsParseException() {
        assertParseFailure(parser, "preamble",
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListDebtorsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "12345",
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListDebtorsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMoney_throwsParseException() {
        assertParseFailure(parser, " m/-9.9", MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " m/1.2.9", MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " m/invalid money", MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validMoney_returnsListDebtorsCommand() {
        ListDebtorsCommand expectedListDebtorsCommand =
                new ListDebtorsCommand(new DebtGreaterEqualAmountPredicate(new Money("10")));

        assertParseSuccess(parser, " m/10.0", expectedListDebtorsCommand);
        assertParseSuccess(parser, " m/10", expectedListDebtorsCommand);
        assertParseSuccess(parser, " m/$10.00", expectedListDebtorsCommand);
    }

    @Test
    public void parse_multipleMoneytakeLast_returnsListDebtorsCommand() {
        ListDebtorsCommand expectedListDebtorsCommand =
                new ListDebtorsCommand(new DebtGreaterEqualAmountPredicate(new Money("10")));

        assertParseSuccess(parser, " m/123 m/10.0", expectedListDebtorsCommand);
        assertParseSuccess(parser, " m/10 m/10", expectedListDebtorsCommand);
        assertParseSuccess(parser, " m/invalid money m/$10.00", expectedListDebtorsCommand);
    }

    @Test
    public void parse_noMoneySpecified_returnsGenericListDebtorsCommand() {
        ListDebtorsCommand expectedListDebtorsCommand = new ListDebtorsCommand();

        assertParseSuccess(parser, "", expectedListDebtorsCommand);
        assertParseSuccess(parser, "  ", expectedListDebtorsCommand);
        assertParseSuccess(parser, "  \n \t", expectedListDebtorsCommand);
    }
}
