package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_APPOINTMENT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String EMPTY_STRING = "";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_EAR = "ear";
    public static final String VALID_TAG_NOSE = "nose";
    public static final String VALID_TAG_THROAT = "throat";

    public static final String VALID_REASON_AMY = "Sore Throat";
    public static final String VALID_DATE_AMY = "2019-12-10 16:30";
    public static final String VALID_REASON_BOB = "Ear Infection";
    public static final String VALID_DATE_BOB = "2019-12-20 16:30";
    public static final String VALID_TIME_PERIOD_AMY = "1Y";
    public static final String VALID_TIME_PERIOD_BOB = "";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_NOSE = " " + PREFIX_TAG + VALID_TAG_NOSE;
    public static final String TAG_DESC_EAR = " " + PREFIX_TAG + VALID_TAG_EAR;
    public static final String TAG_DESC_THROAT = " " + PREFIX_TAG + VALID_TAG_THROAT;
    public static final String TAG_APPOINTMENT_DESC_NOSE = " " + PREFIX_TAG_APPOINTMENT + VALID_TAG_NOSE;
    public static final String TAG_APPOINTMENT_DESC_EAR = " " + PREFIX_TAG_APPOINTMENT + VALID_TAG_EAR;
    public static final String TAG_APPOINTMENT_DESC_THROAT = " " + PREFIX_TAG_APPOINTMENT + VALID_TAG_THROAT;
    public static final String REASON_DESC_AMY = " " + PREFIX_REASON + VALID_REASON_AMY;
    public static final String DATE_DESC_AMY = " " + PREFIX_DATE + VALID_DATE_AMY;
    public static final String REASON_DESC_BOB = " " + PREFIX_REASON + VALID_REASON_BOB;
    public static final String DATE_DESC_BOB = " " + PREFIX_DATE + VALID_DATE_BOB;
    public static final String TIME_PERIOD_DESC_AMY = " " + PREFIX_RECURRING_PERIOD + VALID_TIME_PERIOD_AMY;
    public static final String TIME_PERIOD_DESC_BOB = " " + PREFIX_RECURRING_PERIOD + VALID_TIME_PERIOD_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "Sick";
    public static final String INVALID_REASON_DESC = " " + PREFIX_REASON + "";
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "2022-15-12 16:30";
    public static final String INVALID_TIME_PERIOD_DESC = " " + PREFIX_RECURRING_PERIOD + "0C";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPatientCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPatientCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_NOSE).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_EAR, VALID_TAG_NOSE).build();
    }

    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_APPT_AMY;
    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_APPT_BOB;

    static {
        DESC_APPT_AMY = new EditAppointmentDescriptorBuilder().withReason(VALID_REASON_AMY)
                .withDateTime(LocalDateTime.parse(VALID_DATE_AMY, Appointment.DATE_FORMATTER))
                .withTimePeriod(List.of(1, 0, 0))
                .withTags(VALID_TAG_EAR).build();
        DESC_APPT_BOB = new EditAppointmentDescriptorBuilder().withReason(VALID_REASON_BOB)
                .withDateTime(LocalDateTime.parse(VALID_DATE_BOB, Appointment.DATE_FORMATTER))
                .withTimePeriod(List.of(0, 0, 0))
                .withTags(VALID_TAG_NOSE).build();
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
     * Updates {@code model}'s filtered person list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered appointment list to show only the appointment at the given {@code targetIndex}
     * in the {@code model}'s address book.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointmentToShow = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        model.updateFilteredAppointmentList(appointment -> appointment.isSameAppointment(appointmentToShow));

        assertEquals(1, model.getFilteredAppointmentList().size());
    }
}
