package tracko.logic.parser;

import static tracko.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tracko.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.testutil.TypicalOrders.ORDER_11;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.CommandTestUtil;
import tracko.logic.commands.order.AddOrderCommand;
import tracko.logic.parser.exceptions.ParseException;
import tracko.logic.parser.order.AddOrderCommandParser;
import tracko.model.item.Quantity;
import tracko.model.order.Address;
import tracko.model.order.Email;
import tracko.model.order.Name;
import tracko.model.order.Order;
import tracko.model.order.Phone;
import tracko.testutil.OrderBuilder;

public class AddOrderCommandParserTest {
    private AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parseInitial_allFieldsPresent_success() {
        Order expectedOrder = new OrderBuilder(ORDER_11).withEmptyItemList().build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));

        // multiple names - last name accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));

        // multiple emails - last email accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.ADDRESS_DESC_AMY
                + CommandTestUtil.ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));
    }

    @Test
    public void parseInitial_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.VALID_PHONE_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.VALID_EMAIL_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB + CommandTestUtil.VALID_PHONE_BOB
                + CommandTestUtil.VALID_EMAIL_BOB + CommandTestUtil.VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parseInitial_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.INVALID_PHONE_DESC
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.INVALID_EMAIL_DESC + CommandTestUtil.ADDRESS_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.INVALID_ADDRESS_DESC,
                Address.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseAndUpdate_compulsoryFieldMissing_failure() {
        Order baseOrder = new OrderBuilder().withEmptyItemList().build();
        AddOrderCommand baseCommand = new AddOrderCommand(baseOrder);

        // missing item prefix
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE_2);
        try {
            parser.parseAndUpdate(CommandTestUtil.QUANTITY_AMY, baseCommand);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(expectedMessage, e.getMessage());
        }

        // missing quantity prefix
        try {
            parser.parseAndUpdate(CommandTestUtil.ITEM_NAME_AMY, baseCommand);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void parseAndUpdate_invalidValue_failure() {
        Order baseOrder = new OrderBuilder().withEmptyItemList().build();
        AddOrderCommand baseCommand = new AddOrderCommand(baseOrder);

        // invalid quantity value
        try {
            parser.parseAndUpdate(CommandTestUtil.ITEM_NAME_BOB
                + CommandTestUtil.INVALID_QUANTITY_DESC, baseCommand);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(Quantity.MESSAGE_CONSTRAINTS, e.getMessage());
        }
    }
}
