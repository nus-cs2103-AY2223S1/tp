package modtrekt.logic.parser;

import static modtrekt.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static modtrekt.testutil.Assert.assertThrows;
import static modtrekt.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.junit.jupiter.api.Test;

import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.model.task.Description;


public class ParserUtilTest {

    private static final String VALID_DESC = "Update User Guide";

    private static final String VALID_MOD_NAME = "Software Engineering";


    private static final String VALID_DATE = "2022-09-09";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d")
            .withResolverStyle(ResolverStyle.STRICT);

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
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseDesc_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDesc_validValueWithoutWhitespace_returnsName() throws Exception {
        Description expectedDescription = new Description(VALID_DESC);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESC));
    }

    @Test
    public void parseDesc_invalidValue_throws() {
        assertThrows(ParseException.class,
                Description.MESSAGE_CONSTRAINTS, () ->
                        ParserUtil.parseDescription(""));
    }

    @Test
    public void parseDesc_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_DESC + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESC);
        assertEquals(expectedDescription, ParserUtil.parseDescription(nameWithWhitespace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDueDate((String) null));
    }

    @Test
    public void parseDate_validValueAndFormat_success() throws ParseException {
        LocalDate expectedDate = LocalDate.parse(VALID_DATE, formatter);
        assertEquals(expectedDate, ParserUtil.parseDueDate(VALID_DATE));
    }

    @Test
    public void parseDate_invalidFormat_throws() {
        assertThrows(ParseException.class,
                "Invalid date, date must be in YYYY-MM-DD format", () ->
                        ParserUtil.parseDueDate("2020202020"));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_validValue_success() throws ParseException {
        ModName expectedName = new ModName(VALID_MOD_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_MOD_NAME));
    }

    @Test
    public void parseName_invalidValue_throws() {
        assertThrows(ParseException.class,
                ModName.MESSAGE_CONSTRAINTS, () ->
                        ParserUtil.parseName("$#$#$@#$"));
    }

    @Test
    public void parseCredit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCredit((String) null));
    }

    @Test
    public void parseCredit_validValue_success() throws ParseException {
        ModCredit expectedCredit = new ModCredit("4");
        assertEquals(expectedCredit, ParserUtil.parseCredit("4"));
    }

    @Test
    public void parseCredit_invalidValue_throws() {
        assertThrows(ParseException.class,
                ModCredit.MESSAGE_CONSTRAINTS, () ->
                        ParserUtil.parseCredit("4000000000000"));
    }

    @Test
    public void parseCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCode((String) null));
    }

    @Test
    public void parseCode_validValue_success() throws ParseException {
        ModCode expectedCode = new ModCode("CS2103T");
        assertEquals(expectedCode, ParserUtil.parseCode("CS2103T"));
    }

}
