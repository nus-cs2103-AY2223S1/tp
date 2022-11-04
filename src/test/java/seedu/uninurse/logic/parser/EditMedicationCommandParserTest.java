package seedu.uninurse.logic.parser;

import static seedu.uninurse.logic.commands.EditMedicationCommand.MESSAGE_NOT_EDITED;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.model.medication.Medication.MESSAGE_CONSTRAINTS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.EditMedicationCommand;
import seedu.uninurse.logic.commands.EditMedicationCommand.EditMedicationDescriptor;

/**
 * Contains unit tests for {@code EditMedicationCommandParser}.
 */
class EditMedicationCommandParserTest {
    private static final String MEDICATION_TYPE_STUB = "Some medication";
    private static final String MEDICATION_DOSAGE_STUB = "Some dosage";
    private static final EditMedicationDescriptor DESC_MEDICATION_STUB =
            new EditMedicationDescriptor(Optional.of(MEDICATION_TYPE_STUB), Optional.of(MEDICATION_DOSAGE_STUB));
    private static final EditMedicationDescriptor DESC_MEDICATION_TYPE_STUB =
            new EditMedicationDescriptor(Optional.of(MEDICATION_TYPE_STUB), Optional.empty());
    private static final EditMedicationDescriptor DESC_MEDICATION_DOSAGE_STUB =
            new EditMedicationDescriptor(Optional.empty(), Optional.of(MEDICATION_DOSAGE_STUB));

    private final EditMedicationCommandParser parser = new EditMedicationCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_patientIndexSpecifiedMedicationIndexSpecified_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_MEDICATION + MEDICATION_TYPE_STUB + " | " + MEDICATION_DOSAGE_STUB;

        EditMedicationCommand expectedCommand =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_patientIndexMissingMedicationIndexSpecified_failure() {
        String userInput = INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_MEDICATION + MEDICATION_TYPE_STUB + " | " + MEDICATION_DOSAGE_STUB;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_patientIndexSpecifiedMedicationIndexMissing_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_MEDICATION + MEDICATION_TYPE_STUB + " | " + MEDICATION_DOSAGE_STUB;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyMedicationTypeEmptyMedicationDosageEdit_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_MEDICATION;

        assertParseFailure(parser, userInput, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_medicationPrefixMissing_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " ";

        assertParseFailure(parser, userInput, MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_emptyMedicationTypeEdit_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_MEDICATION + " | " + MEDICATION_DOSAGE_STUB;

        EditMedicationCommand expectedCommand =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_DOSAGE_STUB);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyMedicationDosageEdit_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_MEDICATION + MEDICATION_TYPE_STUB + " | ";

        EditMedicationCommand expectedCommand =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_TYPE_STUB);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noMedicationSeparatorEdit_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_MEDICATION + MEDICATION_TYPE_STUB;

        EditMedicationCommand expectedCommand =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_TYPE_STUB);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
