package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.Command.MESSAGE_ADDRESS_BOOK_COMMAND_PREREQUISITE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class AddAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexValidDate_success() {
        Person personToAddAppointment = model.getFilteredPersonList().get(FIRST_INDEX.getZeroBased());
        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(FIRST_INDEX, "01-01-2000 1200");

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, personToAddAppointment);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person expectedPersonToAddAppointment =
                new PersonBuilder(personToAddAppointment).withAppointment("01-01-2000 1200").build();
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), expectedPersonToAddAppointment);

        assertCommandSuccess(addAppointmentCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_validIndexValidDateFilteredList_success() {
        showPersonAtIndex(model, FIRST_INDEX);

        Person personToAddAppointment = model.getFilteredPersonList().get(FIRST_INDEX.getZeroBased());
        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(FIRST_INDEX, "01-01-2000 1200");

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, personToAddAppointment);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person expectedPersonToAddAppointment =
                new PersonBuilder(personToAddAppointment).withAppointment("01-01-2000 1200").build();
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), expectedPersonToAddAppointment);

        assertCommandSuccess(addAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexValidDateFilteredList_failure() {
        showPersonAtIndex(model, FIRST_INDEX);

        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(SECOND_INDEX, "01-01-2000 1200");

        assertCommandFailure(addAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_addAppointmentInEmptyPatientList_failure() {
        Model model = new ModelManager();

        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(FIRST_INDEX, "01-01-2000 1200");

        assertCommandFailure(addAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidIndexValidDate_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(outOfBoundIndex, "01-01-2000 1200");

        assertCommandFailure(addAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }

    @Test
    public void execute_addAppointmentInRecordList_failure() {
        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(FIRST_INDEX, "01-01-2000 1200");

        model.setFilteredRecordList(BENSON);
        model.setRecordListDisplayed(true);

        assertCommandFailure(addAppointmentCommand, model, MESSAGE_ADDRESS_BOOK_COMMAND_PREREQUISITE);
    }


    @Test
    public void equals() {
        AddAppointmentCommand testFirstAppointmentCommand =
                new AddAppointmentCommand(FIRST_INDEX, "01-01-2000 1200");
        AddAppointmentCommand testSecondAppointmentCommand =
                new AddAppointmentCommand(SECOND_INDEX, "01-01-2000 1200");

        // same object -> returns true
        assertTrue(testFirstAppointmentCommand.equals(testFirstAppointmentCommand));

        // same values -> returns true
        AddAppointmentCommand testFirstAppointmentCommandCopy =
                new AddAppointmentCommand(FIRST_INDEX, "01-01-2000 1200");
        assertTrue(testFirstAppointmentCommand.equals(testFirstAppointmentCommandCopy));

        // different types -> returns false
        assertFalse(testFirstAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(testFirstAppointmentCommand.equals(null));

        // different person -> returns false
        assertFalse(testFirstAppointmentCommand.equals(testSecondAppointmentCommand));
    }
}
