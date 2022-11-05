package seedu.address.logic.commands.getcommands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class GetPastAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        GetPastAppointmentCommand getFirstPastAppointmentCommand = new GetPastAppointmentCommand(
                INDEX_FIRST_PERSON);
        GetPastAppointmentCommand getSecondPastAppointmentCommand = new GetPastAppointmentCommand(
                INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(getFirstPastAppointmentCommand.equals(getFirstPastAppointmentCommand));

        // same values -> returns true
        GetPastAppointmentCommand getFirstPastAppointmentCommandCopy = new GetPastAppointmentCommand(
                INDEX_FIRST_PERSON);
        assertTrue(getFirstPastAppointmentCommand.equals(getFirstPastAppointmentCommandCopy));

        // different types -> returns false
        assertFalse(getFirstPastAppointmentCommand.equals(1));

        // different command -> returns false
        assertFalse(getFirstPastAppointmentCommand.equals(getSecondPastAppointmentCommand));
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        GetPastAppointmentCommand getPastAppointmentCommand = new GetPastAppointmentCommand(
                outOfBoundIndex);
        String expectedMessage = MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(getPastAppointmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndex_success() {
        GetPastAppointmentCommand getPastAppointmentCommand = new GetPastAppointmentCommand(
                INDEX_SECOND_PERSON);
        Person personToGetPastAppointment = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        String pastAppointments = prepareExpectedMessage(personToGetPastAppointment.getPastAppointments());
        String expectedMessage = String.format(GetPastAppointmentCommand.MESSAGE_OBTAIN_PAST_APPOINTMENTS_SUCCESS,
                pastAppointments);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(getPastAppointmentCommand, model, expectedMessage, expectedModel);
    }

    private String prepareExpectedMessage(List<PastAppointment> pastAppointments) {
        StringBuilder output = new StringBuilder();
        for (PastAppointment pastAppointment : pastAppointments) {
            output.append(pastAppointment.toString()).append("\n");
        }
        return output.toString();
    }
}
