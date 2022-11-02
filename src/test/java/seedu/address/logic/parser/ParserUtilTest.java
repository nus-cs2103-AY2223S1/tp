package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.index.Index.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_REQUESTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addcommands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.order.Request;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.OrderUtil;
import seedu.address.testutil.TypicalOrders;

public class ParserUtilTest {
    //For persons
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";

    //For orders
    private static final String INVALID_ORDER_STATUS = "Sleeping";
    private static final String INVALID_REQUEST = "p_s/139746 p_a/";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseOrder_null_throws() {
        assertThrows(NullPointerException.class, null, ()
                -> ParserUtil.parseOrder(null, true));
    }

    @Test
    public void parseOrder_validArgs_success() {
        String input = OrderUtil.getOrderBody(TypicalOrders.ORDER_1);

        try {
            Order result = ParserUtil.parseOrder(input, false);

            //Conpare attributes because each order is unique and calling
            // equals() on two separate orders always returns false
            assertEquals(result.getOrderStatus(), TypicalOrders.ORDER_1.getOrderStatus());
            assertEquals(result.getSettledPrice(), TypicalOrders.ORDER_1.getSettledPrice());
            assertEquals(result.getByDate(), TypicalOrders.ORDER_1.getByDate());
            assertEquals(result.getRequestedPriceRange(), TypicalOrders.ORDER_1.getRequestedPriceRange());
            assertEquals(result.getSettledPrice(), TypicalOrders.ORDER_1.getSettledPrice());
            assertEquals(result.getAdditionalRequests(), TypicalOrders.ORDER_1.getAdditionalRequests());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parseOrder_noPrefixesPresentExistingBuyer_throwsParseException() {
        String expected = AddOrderCommand.MESSAGE_USAGE_EXISTING_BUYER;
        assertThrows(ParseException.class, () -> ParserUtil.parseOrder("bv/rubf", true));
    }

    @Test
    public void parseOrder_noPrefixesPresentNoExistingBuyer_throwsParseException() {
        String expected = AddOrderCommand.MESSAGE_USAGE_NEW_BUYER;
        assertThrows(ParseException.class, () -> ParserUtil.parseOrder("bv/rubf", true));
    }

    @Test
    public void parseOrderStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrderStatus(null));
    }

    @Test
    public void parseOrderStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderStatus(INVALID_ORDER_STATUS));
    }

    @Test
    public void parseRequest_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRequest(null));
    }

    @Test
    public void parseRequest_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRequest("nrijfg"));
    }

    @Test
    public void parseRequest_validArgs_success() {
        String input = OrderUtil.getRequestBody(TypicalOrders.ORDER_1.getRequest());

        try {
            Request result = ParserUtil.parseRequest(PREFIX_ORDER_REQUESTS + " add-r " + input);
            Request expected = TypicalOrders.ORDER_1.getRequest();

            //Conpare attributes because each order is unique and calling
            // equals() on two separate orders always returns false

            assertEquals(result.getRequestedSpecies(), expected.getRequestedSpecies());
            assertEquals(result.getRequestedAge(), expected.getRequestedAge());
            assertEquals(result.getRequestedColor(), expected.getRequestedColor());
            assertEquals(result.getRequestedColorPattern(), expected.getRequestedColorPattern());

        } catch (ParseException e) {
            assert false;
        }
    }
}
