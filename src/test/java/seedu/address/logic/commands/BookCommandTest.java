package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class BookCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final Appointment appointment = new Appointment("Sore Throat", "2022-12-10 16:30", "2M", false);


    @Test
    public void execute_validIndexBooking_success() {
        Person personToBookFor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToBookFor).withAppointment(appointment).build();

        BookCommand bookCommand = new BookCommand(INDEX_FIRST_PERSON, appointment);
        String expectedMessage = String.format(BookCommand.MESSAGE_BOOK_APPOINTMENT_SUCCESS, editedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToBookFor, editedPerson);
        expectedModel.addAppointment(appointment);

        assertCommandSuccess(bookCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        BookCommand bookCommand = new BookCommand(outOfBoundIndex, appointment);

        assertCommandFailure(bookCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAppointmentBooking_throwsCommandException() {
        Person personToBookFor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToBookFor).withAppointment(appointment).build();
        model.setPerson(personToBookFor, editedPerson);

        BookCommand bookCommand = new BookCommand(INDEX_FIRST_PERSON, appointment);
        assertCommandFailure(bookCommand, model, BookCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }
}
