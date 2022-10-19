package seedu.travelr.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.logic.parser.ParserUtil.EVENT_DESCRIPTION_PLACEHOLDER;
import static seedu.travelr.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_FIRST_TRIP;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_EVENT = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_DESCRIPTION = "123 Main Street #0505";
    private static final String VALID_EVENT_1 = "friend";
    private static final String VALID_EVENT_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_TRIP, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_TRIP, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((String) null));
    }

    @Test
    public void parseTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_NAME));
    }

    @Test
    public void parseTitle_validValueWithoutWhitespace_returnsTitle() throws Exception {
        Title expectedTitle = new Title(VALID_NAME);
        assertEquals(expectedTitle, ParserUtil.parseTitle(VALID_NAME));
    }

    @Test
    public void parseTitle_validValueWithWhitespace_returnsTrimmedTitle() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Title expectedTitle = new Title(VALID_NAME);
        assertEquals(expectedTitle, ParserUtil.parseTitle(nameWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parseEvent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEvent(null));
    }

    @Test
    public void parseEvent_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEvent(INVALID_EVENT));
    }

    @Test
    public void parseEvent_validValueWithoutWhitespace_returnsEvent() throws Exception {
        Event expectedEvent = new Event(new Title(VALID_EVENT_1), new Description(EVENT_DESCRIPTION_PLACEHOLDER));
        assertEquals(expectedEvent, ParserUtil.parseEvent(VALID_EVENT_1));
    }

    @Test
    public void parseEvent_validValueWithWhitespace_returnsTrimmedEvent() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_EVENT_1 + WHITESPACE;
        Event expectedEvent = new Event(new Title(VALID_EVENT_1), new Description(EVENT_DESCRIPTION_PLACEHOLDER));
        assertEquals(expectedEvent, ParserUtil.parseEvent(tagWithWhitespace));
    }

    @Test
    public void parseEvents_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEvents(null));
    }

    @Test
    public void parseEvents_collectionWithInvalidEvents_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEvents(Arrays.asList(VALID_EVENT_1, INVALID_EVENT)));
    }

    @Test
    public void parseEvents_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseEvents(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseEvents_collectionWithValidEvents_returnsEventSet() throws Exception {
        Set<Event> actualEventSet = ParserUtil.parseEvents(Arrays.asList(VALID_EVENT_1, VALID_EVENT_2));
        Set<Event> expectedEventSet = new HashSet<Event>(Arrays.asList(new Event(new Title(VALID_EVENT_1),
                new Description(EVENT_DESCRIPTION_PLACEHOLDER)), new Event(new Title(VALID_EVENT_2),
                    new Description(EVENT_DESCRIPTION_PLACEHOLDER))));

        assertEquals(expectedEventSet, actualEventSet);
    }
}
