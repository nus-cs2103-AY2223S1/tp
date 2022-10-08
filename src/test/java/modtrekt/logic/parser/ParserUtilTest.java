package modtrekt.logic.parser;

import static modtrekt.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static modtrekt.testutil.Assert.assertThrows;
import static modtrekt.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.task.Description;


public class ParserUtilTest {

    private static final String VALID_DESC = "Update User Guide";

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
    public void parseDesc_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_DESC + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESC);
        assertEquals(expectedDescription, ParserUtil.parseDescription(nameWithWhitespace));
    }

}
