package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_patientListIsNotFiltered_showsSameList() {
        assertCommandSuccess(
                new ListCommand("patients"), model, ListCommand.MESSAGE_SUCCESS_PATIENTS, expectedModel);
    }

    @Test
    public void execute_patientListIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(
                new ListCommand("patients"), model, ListCommand.MESSAGE_SUCCESS_PATIENTS, expectedModel);
    }

    @Test
    public void execute_appointmentListIsNotFiltered_showsSameList() {
        assertCommandSuccess(
                new ListCommand("appts"), model, ListCommand.MESSAGE_SUCCESS_APPOINTMENTS, expectedModel);
    }

    @Test
    public void execute_appointmentListIsFiltered_showsEverything() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);
        assertCommandSuccess(
                new ListCommand("appts"), model, ListCommand.MESSAGE_SUCCESS_APPOINTMENTS, expectedModel);
    }

    @Test
    public void execute_patientAndAppointmentListIsNotFiltered_showsSameList() {
        assertCommandSuccess(
                new ListCommand("all"), model, ListCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void execute_patientAndAppointmentListIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);
        assertCommandSuccess(
                new ListCommand("all"), model, ListCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }
}
