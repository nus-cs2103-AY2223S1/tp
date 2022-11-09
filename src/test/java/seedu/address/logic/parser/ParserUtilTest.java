package seedu.address.logic.parser;

import static java.time.format.ResolverStyle.STRICT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.product.Product;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DATE = "29022021";
    private static final String INVALID_BIRTHDAY = "29022021";
    private static final String INVALID_PRODUCT = "#Invalid product";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "12345678";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_DATE = "28022021";
    private static final String VALID_START_TIME_1 = "1200";
    private static final String VALID_END_TIME_1 = "1300";
    private static final String VALID_BIRTHDAY = "28022021";
    private static final String VALID_PRODUCT_1 = "Product1";
    private static final String VALID_PRODUCT_2 = "Product2";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_ELEMENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ELEMENT, ParserUtil.parseIndex("  1  "));
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
    public void parseDate_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate(null, "birthday"));
    }

    @Test
    public void parseDate_validBirthday_returnsLocalDate() throws Exception {
        LocalDate expectedDate = LocalDate.parse(
                        VALID_BIRTHDAY,
                        DateTimeFormatter.ofPattern("ddMMuuuu").withResolverStyle(STRICT));
        // test heuristics: valid input at least once: valid birthday, valid meeting
        // ep: valid birthday, invalid birthday
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_BIRTHDAY, "birthday"));
    }

    @Test
    public void parseDate_validMeeting_returnsLocalDate() throws Exception {
        LocalDate expectedDate = LocalDate.parse(
                VALID_DATE,
                DateTimeFormatter.ofPattern("ddMMuuuu").withResolverStyle(STRICT));
        // test heuristics: valid input at least once: valid birthday, valid meeting
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE, "meeting"));
    }

    @Test
    public void parseDate_invalidBirthday_throwsParseException() {
        // ep: valid birthday, invalid birthday
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_BIRTHDAY, "birthday"));
    }

    @Test
    public void parseProduct_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProduct(null));
    }

    @Test
    public void parseProduct_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseProduct(INVALID_PRODUCT));
    }

    @Test
    public void parseProduct_validValueWithoutWhitespace_returnsProduct() throws Exception {
        Product expectedProduct = new Product(VALID_PRODUCT_1);
        assertEquals(expectedProduct, ParserUtil.parseProduct(VALID_PRODUCT_1));
    }

    @Test
    public void parseProduct_validValueWithWhitespace_returnsTrimmedProduct() throws Exception {
        String productWithWhitespace = WHITESPACE + VALID_PRODUCT_1 + WHITESPACE;
        Product expectedProduct = new Product(VALID_PRODUCT_1);
        assertEquals(expectedProduct, ParserUtil.parseProduct(productWithWhitespace));
    }

    @Test
    public void parseProducts_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProducts(null));
    }

    @Test
    public void parseProducts_collectionWithInvalidProducts_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseProducts(Arrays.asList(VALID_PRODUCT_1, INVALID_PRODUCT)));
    }

    @Test
    public void parseProducts_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseProducts(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseProducts_collectionWithValidProducts_returnsProductSet() throws Exception {
        Set<Product> actualProductSet = ParserUtil.parseProducts(Arrays.asList(VALID_PRODUCT_1, VALID_PRODUCT_2));
        Set<Product> expectedProductSet = new HashSet<>(
                Arrays.asList(new Product(VALID_PRODUCT_1), new Product(VALID_PRODUCT_2)));

        assertEquals(expectedProductSet, actualProductSet);
    }
}
