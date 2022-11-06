package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditMedicationCommand.EditMedicationDescriptor;
import seedu.uninurse.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.PatientContainsKeywordsPredicate;
import seedu.uninurse.testutil.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_CONDITION_AMY = "COVID-19";
    public static final String VALID_CONDITION_BOB = "Alzheimer's disease";
    public static final String VALID_TASK_DESC_FIRST = "Administer 80mg of valsartan";
    public static final String VALID_TASK_DESC_SECOND = "Advise on over-the-counter prescriptions";
    public static final String VALID_TAG_ROOM = "Room 2";
    public static final String VALID_TAG_RISK = "high-risk";
    public static final String VALID_TASK_DATE_TIME_FIRST = "16-10-22 1015";
    public static final String VALID_DATE_FIRST = "22-10-22";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String CONDITION_DESC_AMY = " " + PREFIX_CONDITION + VALID_CONDITION_AMY;
    public static final String CONDITION_DESC_BOB = " " + PREFIX_CONDITION + VALID_CONDITION_BOB;
    public static final String TAG_DESC_RISK = " " + PREFIX_TAG + VALID_TAG_RISK;
    public static final String TAG_DESC_ROOM = " " + PREFIX_TAG + VALID_TAG_ROOM;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_CONDITION_DESC = " "
            + PREFIX_CONDITION; // empty string not allowed for conditions
    public static final String INVALID_TASK_DESC = " "
            + PREFIX_TASK_DESCRIPTION; // empty string not allowed for tasks descriptions
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG; // empty string not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPatientDescriptor DESC_AMY;
    public static final EditPatientDescriptor DESC_BOB;

    public static final EditMedicationDescriptor DESC_MED_AMOXICILLIN;
    public static final EditMedicationDescriptor DESC_MED_TYPE_AMOXICILLIN;
    public static final EditMedicationDescriptor DESC_MED_DOSAGE_AMOXICILLIN;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).build();

        DESC_MED_AMOXICILLIN = new EditMedicationDescriptor(
                Optional.of(TYPICAL_MEDICATION_AMOXICILLIN), Optional.of(TYPICAL_DOSAGE_AMOXICILLIN));
        DESC_MED_TYPE_AMOXICILLIN = new EditMedicationDescriptor(
                Optional.of(TYPICAL_MEDICATION_AMOXICILLIN), Optional.empty());
        DESC_MED_DOSAGE_AMOXICILLIN = new EditMedicationDescriptor(
                Optional.empty(), Optional.of(TYPICAL_DOSAGE_AMOXICILLIN));
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            CommandType expectedCommandType, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, expectedCommandType);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the uninurse book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        UninurseBook expectedUninurseBook = new UninurseBook(actualModel.getUninurseBook());
        List<Patient> expectedFilteredList = new ArrayList<>(actualModel.getPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedUninurseBook, actualModel.getUninurseBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s uninurse book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Patient person = model.getPatient(model.getFilteredPersonList().get(targetIndex.getZeroBased()));
        final String[] splitName = person.getName().getValue().split("\\s+");
        model.updateFilteredPatientList(new PatientContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
