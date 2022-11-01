package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PastAppointmentBuilder;
/**
 * Contains integration tests (interaction with the Model) and unit tests for CreatePastAppointmentCommand.
 */
class CreatePastAppointmentCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final PastAppointment pastAppointment = new PastAppointmentBuilder().build();

    @Test
    public void execute_allFieldsSpecifiedFilteredList_success() {
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CreatePastAppointmentCommand createPastAppointmentCommand =
                new CreatePastAppointmentCommand(INDEX_FIRST_PERSON, pastAppointment);

        String expectedMessage = String.format(CreatePastAppointmentCommand.MESSAGE_SUCCESS,
                personInFilteredList.getName()) + pastAppointment;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(createPastAppointmentCommand, model, expectedMessage, expectedModel);

        // remove the appointment from the model for tear down
        expectedModel.getFilteredPersonList().get(0).getPastAppointments().remove(pastAppointment);
    }

    @Test
    public void execute_duplicatePastCommand_failure() {
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Medication> medication = new HashSet<>();
        medication.add(new Medication("Paracetamol"));
        PastAppointment pastAppointment = new PastAppointment(LocalDate.now(), medication, "Fever");
        CreatePastAppointmentCommand createPastAppointmentCommand =
                new CreatePastAppointmentCommand(INDEX_FIRST_PERSON, pastAppointment);

        String expectedMessage = CreatePastAppointmentCommand.DUPLICATE_APPOINTMENT_MESSAGE;

        model.getFilteredPersonList().get(0).addPastAppointment(pastAppointment);

        assertCommandFailure(createPastAppointmentCommand, model, expectedMessage);

        // remove the appointment from the model for tear down
        model.getFilteredPersonList().get(0).getPastAppointments().remove(pastAppointment);

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        CreatePastAppointmentCommand createPastAppointmentCommand =
                new CreatePastAppointmentCommand(Index.fromZeroBased(20), pastAppointment);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                createPastAppointmentCommand.execute(model));

    }
}
