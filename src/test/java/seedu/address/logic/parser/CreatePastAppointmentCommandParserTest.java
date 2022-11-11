package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

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
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser()
                .parse("a on/12-01-2020 diag/fever"));
    }

    @Test
    public void parse_missingDiagnosis_throwsParseException() {
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser().parse("1"));
    }

    @Test
    public void parse_emptyDiagnosis_throwsParseException() {
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser().parse("1 d/"));
    }

    @Test
    public void parse_emptyDiagnosisWithMedication_throwsParseException() {
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser()
                .parse("1 d/ m/ibuprofen"));
    }

    @Test
    public void parse_emptyMedication_successful() throws ParseException {
        new CreatePastAppointmentCommandParser().parse("1 on/12-01-2020 diag/fever m/");
    }

    @Test
    public void parse_missingDate_throwsParseException() {
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser().parse("1 diag/fever"));
    }

    @Test
    public void parse_emptyDate_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> new CreatePastAppointmentCommandParser()
                .parse("1 on/ diag/fever"));
    }

    @Test
    public void parse_invalidDate_throwsParseException() {
        assertThrows(ParseException.class, () -> new CreatePastAppointmentCommandParser()
                .parse("1 on/2022-01-01 diag/fever"));
    }

    @Test
    public void parse_allFieldsCorrect_success() {
        // no exception thrown
        assertDoesNotThrow(() -> new CreatePastAppointmentCommandParser()
                .parse("1 on/12-01-2020 diag/fever m/paracetamol"));
    }

    @Test
    public void parseTagsForEdit_medicationSetWithEmptyString_success() {
        assertDoesNotThrow(() ->
        new CreatePastAppointmentCommandParser().parse("1 on/12-01-2020 diag/fever m/ "));
    }

    @Test
    public void parseTagsForEdit_noMedicationSet_success() {
        assertDoesNotThrow(() ->
        new CreatePastAppointmentCommandParser().parse("1 on/12-01-2020 diag/fever"));
    }

}
