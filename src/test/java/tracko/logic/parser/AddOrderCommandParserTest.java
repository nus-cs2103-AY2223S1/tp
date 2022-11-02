package tracko.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static tracko.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static tracko.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static tracko.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static tracko.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static tracko.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static tracko.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static tracko.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static tracko.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static tracko.logic.commands.CommandTestUtil.ITEM_NAME_ERASER;
import static tracko.logic.commands.CommandTestUtil.ITEM_NAME_PEN;
import static tracko.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static tracko.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static tracko.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static tracko.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static tracko.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static tracko.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static tracko.logic.commands.CommandTestUtil.QUANTITY_PEN;
import static tracko.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tracko.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tracko.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tracko.testutil.TypicalOrders.ORDER_11;

import org.junit.jupiter.api.Test;

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
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB, new AddOrderCommand(expectedOrder));
    }

    @Test
    public void parseInitial_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB
                + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parseInitial_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Address.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseAndUpdate_compulsoryFieldMissing_failure() {
        Order baseOrder = new OrderBuilder().withEmptyItemList().build();
        AddOrderCommand baseCommand = new AddOrderCommand(baseOrder);

        // missing item prefix
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE_2);
        try {
            parser.parseAndUpdate(QUANTITY_PEN, baseCommand);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(expectedMessage, e.getMessage());
        }

        // missing quantity prefix
        try {
            parser.parseAndUpdate(ITEM_NAME_PEN, baseCommand);
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
            parser.parseAndUpdate(ITEM_NAME_ERASER + INVALID_QUANTITY_DESC, baseCommand);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(Quantity.MESSAGE_CONSTRAINTS, e.getMessage());
        }
    }
}
