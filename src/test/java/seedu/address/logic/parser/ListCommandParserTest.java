package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.listcommands.ListCommand;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("this is invalid input"));
    }

    @Test
    public void parse_emptyString_success() {
        ListCommand expected = new ListCommand(ListCommand.LIST_EMPTY);
        assertParseSuccess(parser, "", expected);
    }

    @Test
    public void parse_buyer_success() {
        ListCommand expected = new ListCommand(ListCommand.LIST_BUYER);
        for (String param : CommandUtil.ACCEPTABLE_BUYER_PARAMETER) {
            assertParseSuccess(parser, param, expected);
        }
    }

    @Test
    public void parse_supplier_success() {
        ListCommand expected = new ListCommand(ListCommand.LIST_SUPPLIER);
        for (String param : CommandUtil.ACCEPTABLE_SUPPLIER_PARAMETER) {
            assertParseSuccess(parser, param, expected);
        }
    }

    @Test
    public void parse_deliverer_success() {
        ListCommand expected = new ListCommand(ListCommand.LIST_DELIVERER);
        for (String param : CommandUtil.ACCEPTABLE_DELIVERER_PARAMETER) {
            assertParseSuccess(parser, param, expected);
        }
    }

    @Test
    public void parse_order_success() {
        ListCommand expected = new ListCommand(ListCommand.LIST_ORDER);
        for (String param : CommandUtil.ACCEPTABLE_ORDER_PARAMETER) {
            assertParseSuccess(parser, param, expected);
        }
    }

    @Test
    public void parse_pet_success() {
        ListCommand expected = new ListCommand(ListCommand.LIST_PET);
        for (String param : CommandUtil.ACCEPTABLE_PET_PARAMETER) {
            assertParseSuccess(parser, param, expected);
        }
    }

    @Test
    public void parse_all_success() {
        ListCommand expected = new ListCommand(ListCommand.LIST_ALL);
        for (String param : CommandUtil.ACCEPTABLE_ALL_PARAMETER) {
            assertParseSuccess(parser, param, expected);
        }
    }

    @Test
    public void parse_lowerCaseBuyer_success() {
        ListCommand expected = new ListCommand(ListCommand.LIST_ALL);
        for (String param : CommandUtil.ACCEPTABLE_ALL_PARAMETER) {
            param = param.toLowerCase();
            assertParseSuccess(parser, param, expected);
        }
    }
}

