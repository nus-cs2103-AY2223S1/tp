package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PastAppointmentBuilder;
import seedu.address.testutil.PersonBuilder;

class ConsultCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final PastAppointment pastAppointment = new PastAppointmentBuilder().build();
    private final ConsultCommand consultCommand = new ConsultCommand(INDEX_FIRST_PERSON, pastAppointment);

    @Test
    void execute_nullIndex_failure() {
        assertThrows(NullPointerException.class, () -> new ConsultCommand(null, pastAppointment));
    }

    @Test
    void execute_nullPastAppointment_failure() {
        assertThrows(NullPointerException.class, () -> new ConsultCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    void execute_nullModel_failure() {
        assertThrows(NullPointerException.class, () -> consultCommand.execute(null));
    }

    @Test
    void execute_allFieldsSpecifiedFilteredList_success() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(ConsultCommand.MESSAGE_SUCCESS, person.getName().fullName);

        assertCommandSuccess(consultCommand, model, expectedMessage, model);
        //Teardown
        model.getFilteredPersonList().get(0).getPastAppointments().remove(pastAppointment);
    }

    @Test
    void execute_invalidIndex_failure() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ConsultCommand invalidIndexConsult = new ConsultCommand(invalidIndex, pastAppointment);
        System.out.println("hi");
        assertCommandFailure(invalidIndexConsult, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        System.out.println("hi");
    }

    @Test
    void execute_upcomingAppointmentRemovedSuccess_success() {
        int personIndex = INDEX_FIRST_PERSON.getZeroBased();
        Person person = model.getFilteredPersonList().get(personIndex);
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        Person personUpcoming =
                new PersonBuilder().withName(person.getName().fullName).withUpcomingAppointment(date).build();
        model.setPerson(person, personUpcoming);
        String expectedMessage = String.format(ConsultCommand.MESSAGE_SUCCESS, person.getName().fullName);

        assertCommandSuccess(consultCommand, model, expectedMessage, model);

        assertEquals(model.getFilteredPersonList().get(personIndex).getUpcomingAppointment().orElse(null).value,
                "");
        //Teardown
        model.setPerson(personUpcoming, person);
    }

    @Test
    void testEquals() {
        PastAppointment firstPastAppointment = new PastAppointmentBuilder().withDiagnosis("dead").build();
        PastAppointment secondPastAppointment = new PastAppointmentBuilder().withDiagnosis("alive").build();

        ConsultCommand firstConsultCommand = new ConsultCommand(INDEX_FIRST_PERSON, firstPastAppointment);
        ConsultCommand secondConsultCommand = new ConsultCommand(INDEX_FIRST_PERSON, secondPastAppointment);
        ConsultCommand thirdConsultCommand = new ConsultCommand(INDEX_SECOND_PERSON, firstPastAppointment);
        ConsultCommand fourthConsultCommand = new ConsultCommand(INDEX_SECOND_PERSON, secondPastAppointment);

        // same object -> returns true
        assertTrue(firstConsultCommand.equals(firstConsultCommand));

        // same values -> returns true
        ConsultCommand firstConsultCommandCopy = new ConsultCommand(INDEX_FIRST_PERSON, firstPastAppointment);
        assertTrue(firstConsultCommand.equals(firstConsultCommandCopy));

        // different types -> returns false
        assertFalse(firstConsultCommandCopy.equals(1));

        // null -> returns false
        assertFalse(firstConsultCommandCopy.equals(null));

        // different person -> returns false
        assertFalse(firstConsultCommandCopy.equals(secondConsultCommand));
        assertFalse(firstConsultCommandCopy.equals(thirdConsultCommand));
        assertFalse(firstConsultCommandCopy.equals(fourthConsultCommand));
        assertFalse(secondConsultCommand.equals(thirdConsultCommand));
        assertFalse(secondConsultCommand.equals(fourthConsultCommand));
        assertFalse(thirdConsultCommand.equals(fourthConsultCommand));

    }
}
