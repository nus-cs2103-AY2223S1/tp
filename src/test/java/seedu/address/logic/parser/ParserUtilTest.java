package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.client.Name;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {

    private static final String INVALID_DATE_INPUT = "00/09/2000";

    private static final String INVALID_DATE_FORMAT = "eeeeee";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_QUANTITY = "100h";
    private static final String INVALID_QUANTITY_NEGATIVE = " -10";
    private static final String INVALID_QUANTITY_ZERO = "0";
    private static final String INVALID_QUANTITY_NEGATIVE_ZERO = "-0";

    private static final String INVALID_QUANTITY_MILLION = "10000000";


    private static final String INVALID_QUANTITY_EMPTY = " ";

    private static final String INVALID_PRICE = "1213a";
    private static final String INVALID_PRICE_NEGATIVE = "-100";

    private static final String INVALID_PRICE_EMPTY = " ";

    private static final String INVALID_PRICE_MILLION = "10000000";


    private static final String INVALID_TEXT = " ";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
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
        assertEquals(INDEX_FIRST_CLIENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_CLIENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseInvalidName_throwsParseException() {
        assertThrows(ParseException.class, Name.MESSAGE_CONSTRAINTS, ()
                -> ParserUtil.parseName("%$&^"));
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
    public void parseClientPhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        ClientPhone expectedPhone = new ClientPhone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parseClientPhone(phoneWithWhitespace));
    }

    @Test
    public void parseClientPhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClientPhone((String) null));
    }

    @Test
    public void parseClientPhone_validValue_returnsPhoneWithoutWarning() {
        ClientPhone phone = ParserUtil.parseClientPhone(VALID_PHONE);
        assertFalse(phone.hasWarning());
    }

    @Test
    public void parseClientPhone_invalidValue_returnsPhoneWithWarning() {
        ClientPhone phone = ParserUtil.parseClientPhone(INVALID_PHONE);
        assertTrue(phone.hasWarning());
    }

    @Test
    public void parseClientPhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        ClientPhone expectedPhone = new ClientPhone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parseClientPhone(VALID_PHONE));
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
    public void parseClientEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        ClientEmail expectedEmail = new ClientEmail(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseClientEmail(emailWithWhitespace));
    }

    @Test
    public void parseClientEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClientEmail((String) null));
    }

    @Test
    public void parseClientEmail_invalidValue_returnsEmailWithWarning() {
        ClientEmail email = ParserUtil.parseClientEmail(INVALID_EMAIL);
        assertTrue(email.hasWarning());
    }

    @Test
    public void parseClientEmail_validValue_returnsEmailWithoutWarning() {
        ClientEmail email = ParserUtil.parseClientEmail(VALID_EMAIL);
        assertFalse(email.hasWarning());
    }

    @Test
    public void parseClientEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        ClientEmail expectedEmail = new ClientEmail(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseClientEmail(VALID_EMAIL));
    }

    @Test
    public void parseClientEmail_withWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        ClientEmail expectedEmail = new ClientEmail(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseClientEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuantity(null));
    }

    @Test
    public void parsePrice_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePrice(null));
    }

    @Test
    public void parseGoods_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGoods(null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY_NEGATIVE));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY_ZERO));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY_NEGATIVE_ZERO));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY_EMPTY));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY_MILLION));
    }

    @Test
    public void parsePrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE));
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE_NEGATIVE));
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE_MILLION));
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE_EMPTY));

    }



    @Test

    public void parseDate_invalidDate_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_INPUT));
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_FORMAT));
    }

    @Test
    public void parseText_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseText(INVALID_TEXT));
    }

    @Test
    public void parseText_null_throwsParseException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseText(null));
    }

}
