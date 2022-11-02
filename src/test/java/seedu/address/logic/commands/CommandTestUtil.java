package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILL_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.HealthContact;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.NameContainsKeywordsPredicateAppointment;
import seedu.address.model.patient.NameContainsKeywordsPredicatePatient;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;

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
    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_DOCTOR_CAITIE = "Kaitlyn Dorsey Caitie";
    public static final String VALID_DOCTOR_DECKER = "Amy Decker";
    public static final String VALID_MEDICAL_TEST_7 = "glucose tolerance test";
    public static final String VALID_MEDICAL_TEST_8 = "cephalin-cholesterol flocculation";
    public static final String VALID_SLOT_7 = "2024-03-19 15:45";
    public static final String VALID_SLOT_8 = "2023-05-10 09:15";

    public static final String VALID_AMOUNT_7 = "10";
    public static final String VALID_BILL_DATE_7 = "2019-12-24";
    public static final String VALID_BILL_DATE_8 = "2019-12-25";
    public static final String VALID_AMOUNT_8 = "11.00";
    public static final String VALID_PAYMENT_STATUS_7 = "PAID";
    public static final String VALID_PAYMENT_STATUS_8 = "UNPAID";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;

    public static final String DOCTOR_DESC_7 = " " + PREFIX_DOCTOR + VALID_DOCTOR_CAITIE;
    public static final String DOCTOR_DESC_8 = " " + PREFIX_DOCTOR + VALID_DOCTOR_DECKER;
    public static final String MEDICAL_TEST_DESC_7 = " " + PREFIX_MEDICAL_TEST + VALID_MEDICAL_TEST_7;
    public static final String MEDICAL_TEST_DESC_8 = " " + PREFIX_MEDICAL_TEST + VALID_MEDICAL_TEST_8;
    public static final String SLOT_DESC_7 = " " + PREFIX_SLOT + VALID_SLOT_7;
    public static final String SLOT_DESC_8 = " " + PREFIX_SLOT + VALID_SLOT_8;

    public static final String BILL_DATE_DESC_7 = " " + PREFIX_BILL_DATE + VALID_BILL_DATE_7;
    public static final String BILL_DATE_DESC_8 = " " + PREFIX_BILL_DATE + VALID_BILL_DATE_8;
    public static final String AMOUNT_DESC_7 = " " + PREFIX_AMOUNT + VALID_AMOUNT_7;
    public static final String AMOUNT_DESC_8 = " " + PREFIX_AMOUNT + VALID_AMOUNT_8;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_DOCTOR_DESC = " " + PREFIX_DOCTOR + "John!";
    public static final String INVALID_MEDICAL_TEST_DESC = " " + PREFIX_MEDICAL_TEST;
    public static final String INVALID_SLOT_DESC = " " + PREFIX_SLOT + "2023-1-1 9:00";


    public static final String INVALID_BILL_DATE_DESC = " " + PREFIX_BILL_DATE + "2012-12-36";
    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "-5";
    public static final String INVALID_PAYMENT_STATUS = " ";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPatientCommand.EditPatientDescriptor DESC_AMY;
    public static final EditPatientCommand.EditPatientDescriptor DESC_BOB;

    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_APPOINTMENT_7;
    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_APPOINTMENT_8;


    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        //TODO
        DESC_APPOINTMENT_7 = null;
        DESC_APPOINTMENT_8 = null;
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
     * - the HealthContact, filtered patient list and selected patient in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        HealthContact expectedHealthContact = new HealthContact(actualModel.getHealthContact());
        List<Patient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedHealthContact, actualModel.getHealthContact());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s HealthContact.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicatePatient(splitName[0]));

        assertEquals(1, model.getFilteredPatientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the appointment at the given {@code targetIndex} in the
     * {@code model}'s HealthContact.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        final String[] splitName = appointment.getName().fullName.split("\\s+");
        String predicateName = splitName[0];
        for (int i = 1; i < splitName.length; i++) {
            predicateName += " " + splitName[i];
        }
        model.updateFilteredAppointmentList(new NameContainsKeywordsPredicateAppointment(predicateName));

        assertEquals(1, model.getFilteredAppointmentList().size());
    }

}
