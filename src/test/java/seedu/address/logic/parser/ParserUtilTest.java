package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.index.Index.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_REQUESTS;
import static seedu.address.model.ModelManager.PREFERRED_DATE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addcommands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.Height;
import seedu.address.model.pet.PetCertificate;
import seedu.address.model.pet.Species;
import seedu.address.model.pet.VaccinationStatus;
import seedu.address.model.pet.Weight;
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
    public void parseLocation_invalidArgs_throwsParseException() {
        String expected = Location.MESSAGE_CONSTRAINTS;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseLocation("&nta@rt1c@"));
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
    public void parseOrders() {
        List<Order> orderList = TypicalOrders.getTypicalOrders();
        List<String> inputList = orderList.stream().map(x -> OrderUtil.getOrderBody(x)).collect(Collectors.toList());

        try {
            List<Order> resultingOrderList = ParserUtil.parseOrders(inputList, false);

            //Conpare attributes because each order is unique and calling
            // equals() on two separate orders always returns false

            for (int i = 0; i < resultingOrderList.size(); i++) {
                Order result = resultingOrderList.get(i);
                Order expected = orderList.get(i);

                assertEquals(result.getOrderStatus(), expected.getOrderStatus());
                assertEquals(result.getSettledPrice(), expected.getSettledPrice());
                assertEquals(result.getByDate(), expected.getByDate());
                assertEquals(result.getRequestedPriceRange(), expected.getRequestedPriceRange());
                assertEquals(result.getSettledPrice(), expected.getSettledPrice());
                assertEquals(result.getAdditionalRequests(), expected.getAdditionalRequests());
            }
        } catch (ParseException e) {
            assert false;
        }
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
    public void parsePrice_invalidArgs_throwParseException() {
        String expected = Price.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parsePrice("^4v"));
    }

    @Test
    public void parsePrice_negativePrice_throwParseException() {
        String expected = Price.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parsePrice("-267832"));
    }

    @Test
    public void parsePriceRange_noUpperBound_throwParseException() {
        String expected = PriceRange.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parsePriceRange("6,"));
    }

    @Test
    public void parsePriceRange_negativeUpperBound_throwParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriceRange("-45,-10"));
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

    @Test
    public void parseAdditionalRequests_invalidAdditionalRequest_throwsParseException() {
        AdditionalRequests additionalRequests = new AdditionalRequests("HGBJBV^^^&&&#");
        List<String> additionalRequestsString = additionalRequests.getAdditionalRequestsToString();

        String expected = AdditionalRequests.MESSAGE_CONSTRAINTS;
        assertThrows(ParseException.class, expected, ()
                -> ParserUtil.parseAdditionalRequests(additionalRequestsString));
    }

    @Test
    public void parseDate_invalidArgs_throwsParseException() {
        String expected = "The date should be in this format: " + PREFERRED_DATE_FORMAT;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseDate("10-02-2002"));
    }

    @Test
    public void parseAge_invalidArgs_throwsParseException() {
        String expected = Age.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseAge("hello world"));
    }

    @Test
    public void parseAge_negativeAge_throwsParseException() {
        String expected = Age.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseAge("-1"));
    }

    @Test
    public void parseColor_nonAlphanumeric_throwsParseException() {
        String expected = Color.MESSAGE_CONSTRAINTS;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseColor("@qu&m&r1ne"));
    }

    @Test
    public void parseSpecies_nonAlphanumeric_throwsParseException() {
        String expected = Species.MESSAGE_CONSTRAINTS;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseSpecies("c@t"));
    }

    @Test
    public void parseDateOfBirth_invalidFormat_throwsParseException() {
        String expected = DateOfBirth.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseDateOfBirth("10-03-2009"));
    }

    @Test
    public void parseHeight_invalidNumber_throwsParseException() {
        String expected = Height.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseHeight("hello world"));
    }

    @Test
    public void parseHeight_negativeNumber_throwsParseException() {
        String expected = Height.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseHeight("-1"));
    }

    @Test
    public void parsePetCertificate_nonAlphanumeric_throwsParseException() {
        String expected = PetCertificate.MESSAGE_CONSTRAINTS;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parsePetCertificate("&v&"));
    }

    @Test
    public void parsePetCertificate_validArgs_success() {
        try {
            PetCertificate expected = new PetCertificate("AVA");
            PetCertificate certificate = ParserUtil.parsePetCertificate("AVA");
            assertEquals(certificate, expected);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parseVaccinationStatus_boolean_success() {
        try {
            VaccinationStatus expected = new VaccinationStatus(true);
            VaccinationStatus vaccinationStatus = ParserUtil.parseVaccinationStatus("true");
            assertEquals(vaccinationStatus, expected);

            expected = new VaccinationStatus(false);
            vaccinationStatus = ParserUtil.parseVaccinationStatus("false");
            assertEquals(vaccinationStatus, expected);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parseVaccinationStatus_nonBooleanInput_throwsParseException() {
        String expected = VaccinationStatus.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseVaccinationStatus("6"));
    }

    @Test
    public void parseWeight_invalidNumber_throwsParseException() {
        String expected = Weight.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseWeight("invalid"));
    }

    @Test
    public void parseWeight_negativeWeight_throwsParseException() {
        String expected = Weight.MESSAGE_USAGE;
        assertThrows(ParseException.class, expected, () -> ParserUtil.parseWeight("-1"));
    }

}
