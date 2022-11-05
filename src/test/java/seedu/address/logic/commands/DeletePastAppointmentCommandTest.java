package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class DeletePastAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DeletePastAppointmentCommand deleteFirstPastAppointmentCommand = new DeletePastAppointmentCommand(
                INDEX_FIRST_PERSON);
        DeletePastAppointmentCommand deleteSecondPastAppointmentCommand = new DeletePastAppointmentCommand(
                INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstPastAppointmentCommand.equals(deleteFirstPastAppointmentCommand));

        // same values -> returns true
        DeletePastAppointmentCommand deleteFirstPastAppointmentCommandCopy = new DeletePastAppointmentCommand(
                INDEX_FIRST_PERSON);
        assertTrue(deleteFirstPastAppointmentCommand.equals(deleteFirstPastAppointmentCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstPastAppointmentCommand.equals(1));

        // different command -> returns false
        assertFalse(deleteFirstPastAppointmentCommand.equals(deleteSecondPastAppointmentCommand));
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeletePastAppointmentCommand deletePastAppointmentCommand = new DeletePastAppointmentCommand(outOfBoundIndex);
        String expectedMessage = MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(deletePastAppointmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_noPastAppointment_throwsCommandException() {
        DeletePastAppointmentCommand deletePastAppointmentCommand = new DeletePastAppointmentCommand(
                INDEX_FIRST_PERSON);
        String expectedMessage = DeletePastAppointmentCommand.INVALID_DELETE_MESSAGE;
        assertCommandFailure(deletePastAppointmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndex_success() {
        Person personToDeleteAppointment = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        DeletePastAppointmentCommand deletePastAppointmentCommand = new DeletePastAppointmentCommand(
                INDEX_SECOND_PERSON);

        String expectedMessage = String.format(DeletePastAppointmentCommand.MESSAGE_SUCCESS,
                personToDeleteAppointment.getName());

        PastAppointment pastAppointmentToDelete = preparePastAppointmentToDelete(personToDeleteAppointment);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(deletePastAppointmentCommand, model, expectedMessage, expectedModel);

        // add appointment back to model for tear down
        model.getFilteredPersonList()
                .get(INDEX_SECOND_PERSON.getZeroBased())
                .getPastAppointments().add(0, pastAppointmentToDelete);
    }

    private PastAppointment preparePastAppointmentToDelete(Person personToDeleteAppointment) {
        return personToDeleteAppointment.getPastAppointments().get(0);
    }
}
