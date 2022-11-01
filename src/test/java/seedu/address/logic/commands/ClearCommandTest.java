package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModelType;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBookAll_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("all"), model, ClearCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookAll_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand("all"), model, ClearCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void execute_emptyAddressBookStudent_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("student"), model,
                ClearCommand.MESSAGE_SUCCESS_STUDENT, ModelType.STUDENT, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookStudent_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.resetStudents();

        assertCommandSuccess(new ClearCommand("student"), model,
                ClearCommand.MESSAGE_SUCCESS_STUDENT, ModelType.STUDENT, expectedModel);
    }

    @Test
    public void execute_emptyAddressBookConsultation_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("consultation"), model,
                ClearCommand.MESSAGE_SUCCESS_CONSULTATION, ModelType.CONSULTATION, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookConsultation_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.resetConsultations();

        assertCommandSuccess(new ClearCommand("consultation"), model,
                ClearCommand.MESSAGE_SUCCESS_CONSULTATION, ModelType.CONSULTATION, expectedModel);
    }

    @Test
    public void execute_emptyAddressBookTutorial_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("tutorial"), model,
                ClearCommand.MESSAGE_SUCCESS_TUTORIAL, ModelType.TUTORIAL, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookTutorial_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.resetTutorials();

        assertCommandSuccess(new ClearCommand("tutorial"), model,
                ClearCommand.MESSAGE_SUCCESS_TUTORIAL, ModelType.TUTORIAL, expectedModel);
    }

    @Test
    public void execute_emptyAddressBookReminder_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("reminder"), model,
                ClearCommand.MESSAGE_SUCCESS_REMINDER, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookReminder_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.resetReminders();

        assertCommandSuccess(new ClearCommand("reminder"), model,
                ClearCommand.MESSAGE_SUCCESS_REMINDER, expectedModel);
    }
}
