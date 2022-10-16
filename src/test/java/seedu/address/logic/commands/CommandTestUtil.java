package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLOOR_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOSPITAL_WING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD_NUMBER;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PatientType.PatientTypes;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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
    public static final String VALID_NEXT_OF_KIN_AMY = "Joe Bee, Husband, 82354371";
    public static final String VALID_NEXT_OF_KIN_BOB = "Cec Choo, Son, 92723675";
    public static final String VALID_PATIENT_TYPE_AMY = "i";
    public static final String VALID_PATIENT_TYPE_BOB = "outpatient";
    public static final String VALID_HOSPITAL_WING_AMY = "south";
    public static final String VALID_HOSPITAL_WING_BOB = "hes an outpatient, so no hospital wing";
    public static final String VALID_FLOOR_NUMBER_AMY = "1";
    public static final String VALID_FLOOR_NUMBER_BOB = "hes an outpatient, so no floor number";
    public static final String VALID_WARD_NUMBER_AMY = "D312";
    public static final String VALID_WARD_NUMBER_BOB = "hes an outpatient, so no ward number";
    public static final String VALID_MEDICATION_IBUPROFEN = "Ibuprofen";
    public static final String VALID_MEDICATION_PARACETAMOL = "Paracetamol";
    public static final String VALID_MEDICATION_XANAX = "Xanax";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String NEXT_OF_KIN_DESC_AMY = " " + PREFIX_NEXT_OF_KIN + VALID_NEXT_OF_KIN_AMY;
    public static final String NEXT_OF_KIN_DESC_BOB = " " + PREFIX_NEXT_OF_KIN + VALID_NEXT_OF_KIN_BOB;
    public static final String PATIENT_TYPE_DESC_AMY = " " + PREFIX_PATIENT_TYPE + VALID_PATIENT_TYPE_AMY;
    public static final String PATIENT_TYPE_DESC_BOB = " " + PREFIX_PATIENT_TYPE + VALID_PATIENT_TYPE_BOB;
    public static final String HOSPITAL_WING_DESC_AMY = " " + PREFIX_HOSPITAL_WING + VALID_HOSPITAL_WING_AMY;
    public static final String HOSPITAL_WING_DESC_BOB = " " + PREFIX_HOSPITAL_WING + VALID_HOSPITAL_WING_BOB;
    public static final String FLOOR_NUMBER_DESC_AMY = " " + PREFIX_FLOOR_NUMBER + VALID_FLOOR_NUMBER_AMY;
    public static final String FLOOR_NUMBER_DESC_BOB = " " + PREFIX_FLOOR_NUMBER + VALID_FLOOR_NUMBER_BOB;
    public static final String WARD_NUMBER_DESC_AMY = " " + PREFIX_WARD_NUMBER + VALID_WARD_NUMBER_AMY;
    public static final String WARD_NUMBER_DESC_BOB = " " + PREFIX_WARD_NUMBER + VALID_WARD_NUMBER_BOB;
    public static final String MEDICATION_DESC_IBUPROFEN = " " + PREFIX_MEDICATION + VALID_MEDICATION_IBUPROFEN;
    public static final String MEDICATION_DESC_PARACETAMOL = " " + PREFIX_MEDICATION + VALID_MEDICATION_PARACETAMOL;
    public static final String MEDICATION_DESC_XANAX = " " + PREFIX_MEDICATION + VALID_MEDICATION_XANAX;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_NEXT_OF_KIN_DESC = " " + PREFIX_NEXT_OF_KIN
            + "Charlie is his mother with number 85854754"; // must be in the format of name, relationship, contact
    public static final String INVALID_PATIENT_TYPE_DESC = " "
            + PREFIX_PATIENT_TYPE + "a"; // patient type can only be i(inpatient) or o(outpatient)
    public static final String INVALID_HOSPITAL_WING_DESC = " "
            + PREFIX_HOSPITAL_WING + "room@1north"; // only alphanumeric chars and space allowed in hospital wing
    public static final String INVALID_FLOOR_NUMBER_DESC = " "
            + PREFIX_FLOOR_NUMBER + "-1"; // non-positive numbers not allowed for floor number & ward number
    public static final String INVALID_WARD_NUMBER_DESC = " "
            + PREFIX_WARD_NUMBER + "hello"; // non-numbers not allowed for floor number & ward number

    public static final String INVALID_MEDICATION_DESC = " "
            + PREFIX_MEDICATION + "hubby*"; // '*' not allowed in medications

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withNextOfKin(VALID_NEXT_OF_KIN_AMY)
                .withPatientType(PatientTypes.parsePatientType(VALID_PATIENT_TYPE_AMY))
                .withHospitalWing(VALID_HOSPITAL_WING_AMY).withFloorNumber(Integer.valueOf(VALID_FLOOR_NUMBER_AMY))
                .withWardNumber(VALID_WARD_NUMBER_AMY)
                .withMedication(VALID_MEDICATION_IBUPROFEN).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withNextOfKin(VALID_NEXT_OF_KIN_BOB)
                .withPatientType(PatientTypes.parsePatientType(VALID_PATIENT_TYPE_BOB))
                .withMedication(VALID_MEDICATION_PARACETAMOL).build();
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
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
