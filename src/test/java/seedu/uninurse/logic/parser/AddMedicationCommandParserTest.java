package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.commands.AddMedicationCommand.MESSAGE_USAGE;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.model.medication.Medication.MESSAGE_CONSTRAINTS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalMedications.MEDICATION_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.AddMedicationCommand;

/**
 * Contains unit tests for {@code AddMedicationCommandParser}.
 */
public class AddMedicationCommandParserTest {
    private final AddMedicationCommandParser parser = new AddMedicationCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_success() {
        String args = INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_MEDICATION + TYPICAL_MEDICATION_AMOXICILLIN + "|" + TYPICAL_DOSAGE_AMOXICILLIN;
        assertParseSuccess(parser, args, new AddMedicationCommand(INDEX_FIRST_PERSON, MEDICATION_AMOXICILLIN));
        assertParseSuccess(parser, " " + args + " ",
                new AddMedicationCommand(INDEX_FIRST_PERSON, MEDICATION_AMOXICILLIN));
    }

    @Test
    public void parse_invalidPatientIndex_failure() {
        String argsOne = "a " + PREFIX_MEDICATION + TYPICAL_MEDICATION_AMOXICILLIN + "|" + TYPICAL_DOSAGE_AMOXICILLIN;
        String argsTwo = "0 " + PREFIX_MEDICATION + TYPICAL_MEDICATION_AMOXICILLIN + "|" + TYPICAL_DOSAGE_AMOXICILLIN;
        assertParseFailure(parser, argsOne, MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, argsTwo, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingPatientIndex_failure() {
        String args = PREFIX_MEDICATION + TYPICAL_MEDICATION_AMOXICILLIN + "|" + TYPICAL_DOSAGE_AMOXICILLIN;
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyMedication_failure() {
        String args = "1 " + PREFIX_MEDICATION;
        assertParseFailure(parser, args, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyMedicationType_failure() {
        String args = "1 " + PREFIX_MEDICATION + "|" + TYPICAL_DOSAGE_AMOXICILLIN;
        assertParseFailure(parser, args, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyMedicationDosage_failure() {
        String argsOne = "1 " + PREFIX_MEDICATION + TYPICAL_MEDICATION_AMOXICILLIN;
        String argsTwo = "1 " + PREFIX_MEDICATION + TYPICAL_MEDICATION_AMOXICILLIN + "|";
        assertParseFailure(parser, argsOne, MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, argsTwo, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingMedicationPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);
        assertParseFailure(parser, "1" , expectedMessage);
    }
}
