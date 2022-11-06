package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ConsultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.tag.Medication;
import seedu.address.testutil.TypicalIndexes;

class ConsultCommandParserTest {

    private ConsultCommandParser parser = new ConsultCommandParser();
    private String invalid_message = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConsultCommand.MESSAGE_USAGE);

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConsultCommandParser().parse(null));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "", invalid_message);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        Index index = TypicalIndexes.INDEX_FIRST_PERSON;
        String diagnosis = "fever";

        String toParse = index.getZeroBased() + " " + CliSyntax.PREFIX_DIAGNOSIS + diagnosis;

        assertParseFailure(parser, toParse, invalid_message);
    }

    @Test
    public void parse_missingDiagnosis_throwsParseException() {
        Index index = TypicalIndexes.INDEX_FIRST_PERSON;
        String diagnosis = "fever";

        String toParse = String.valueOf(index.getOneBased());
        assertParseFailure(parser, toParse, invalid_message);
    }

    @Test
    public void parse_emptyDiagnosis_throwsParseException() {
        Index index = TypicalIndexes.INDEX_FIRST_PERSON;
        String diagnosis = "fever";

        String toParse = index.getOneBased() + " " + CliSyntax.PREFIX_DIAGNOSIS;
        assertParseFailure(parser, toParse, invalid_message);
    }

    @Test
    public void parse_emptyDiagnosisWithMedication_throwsParseException() {
        Index index = TypicalIndexes.INDEX_FIRST_PERSON;
        String diagnosis = "fever";

        String toParse = index.getOneBased() + " " + CliSyntax.PREFIX_DIAGNOSIS + " " + CliSyntax.PREFIX_MEDICATION
                + "panadol";
        assertParseFailure(parser, toParse, invalid_message);
    }

    @Test
    public void parse_emptyMedication_successful() throws ParseException {
        Index index = TypicalIndexes.INDEX_FIRST_PERSON;
        LocalDate date = LocalDate.now();
        String diagnosis = "fever";

        String toParse = index.getOneBased() + " " + CliSyntax.PREFIX_DIAGNOSIS + diagnosis
                + " " + CliSyntax.PREFIX_MEDICATION;

        Set<Medication> meds = new HashSet<>();

        PastAppointment pastAppointment = new PastAppointment(date, meds, diagnosis);
        ConsultCommand expectedCommand = new ConsultCommand(index, pastAppointment);

        assertParseSuccess(parser, toParse, expectedCommand);
    }

    @Test
    public void parse_allFieldsCorrect_success() {
        Index index = TypicalIndexes.INDEX_FIRST_PERSON;
        LocalDate date = LocalDate.now();
        String diagnosis = "fever";
        String medication = "paracetamol";

        String toParse = index.getOneBased() + " " + CliSyntax.PREFIX_DIAGNOSIS + diagnosis
                + " " + CliSyntax.PREFIX_MEDICATION + medication;

        Set<Medication> meds = new HashSet<>();
        meds.add(new Medication(medication));

        PastAppointment pastAppointment = new PastAppointment(date, meds, diagnosis);
        ConsultCommand expectedCommand = new ConsultCommand(index, pastAppointment);

        assertParseSuccess(parser, toParse, expectedCommand);
    }

    @Test
    public void parseTagsForEdit_medicationSetWithEmptyString_success() {
        Index index = TypicalIndexes.INDEX_FIRST_PERSON;
        LocalDate date = LocalDate.now();
        String diagnosis = "fever";

        String toParse = index.getOneBased() + " " + CliSyntax.PREFIX_DIAGNOSIS + diagnosis
                + " " + CliSyntax.PREFIX_MEDICATION + " ";

        Set<Medication> meds = new HashSet<>();

        PastAppointment pastAppointment = new PastAppointment(date, meds, diagnosis);
        ConsultCommand expectedCommand = new ConsultCommand(index, pastAppointment);

        assertParseSuccess(parser, toParse, expectedCommand);
    }

    @Test
    public void parseTagsForEdit_noMedicationSet_success() {
        Index index = TypicalIndexes.INDEX_FIRST_PERSON;
        LocalDate date = LocalDate.now();
        String diagnosis = "fever";

        String toParse = index.getOneBased() + " " + CliSyntax.PREFIX_DIAGNOSIS + diagnosis;

        Set<Medication> meds = new HashSet<>();

        PastAppointment pastAppointment = new PastAppointment(date, meds, diagnosis);
        ConsultCommand expectedCommand = new ConsultCommand(index, pastAppointment);

        assertParseSuccess(parser, toParse, expectedCommand);
    }
}
