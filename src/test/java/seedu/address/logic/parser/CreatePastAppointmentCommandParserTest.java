package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

class CreatePastAppointmentCommandParserTest {

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreatePastAppointmentCommandParser().parse(null));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser().parse(""));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser().parse("a"));
    }

    @Test
    public void parse_missingDiagnosis_throwsParseException() {
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser().parse("1"));
    }

    @Test
    public void parse_missingDate_throwsParseException() {
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser().parse("1 d/"));
    }

}
