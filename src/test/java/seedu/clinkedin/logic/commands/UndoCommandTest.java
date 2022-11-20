package seedu.clinkedin.logic.commands;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_CANNOT_UNDO;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.AddressBook;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.testutil.PersonBuilder;

class UndoCommandTest {

    private static final String NAME_STUB = "nameStub";

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Person personStub = new PersonBuilder().build();

    @Test
    void execute_initialState_failure() {
        UndoCommand undoCommand = new UndoCommand();
        assertCommandFailure(undoCommand, model, MESSAGE_CANNOT_UNDO);
    }

    @Test
    void execute_undoAddCommand_success() {
        model.addPerson(personStub);
        UndoCommand undoCommand = new UndoCommand();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_noNewState_failure() {
        model.addPerson(personStub);
        UndoCommand undoCommand = new UndoCommand();
        model.undoAddressBook();
        assertCommandFailure(undoCommand, model, MESSAGE_CANNOT_UNDO);
    }

    @Test
    void execute_undoDeleteCommand_success() {
        model.addPerson(personStub);
        model.deletePerson(personStub);
        UndoCommand undoCommand = new UndoCommand();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addPerson(personStub);
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_undoEditCommand_success() {
        model.addPerson(personStub);
        Person editedPersonStub = new PersonBuilder(personStub).withName(NAME_STUB).build();
        model.setPerson(personStub, editedPersonStub);
        UndoCommand undoCommand = new UndoCommand();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addPerson(personStub);
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_undoClearCommand_success() {
        model.addPerson(personStub);
        model.setAddressBook(new AddressBook());
        UndoCommand undoCommand = new UndoCommand();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addPerson(personStub);
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_undoTwice_success() {
        model.addPerson(personStub);
        model.deletePerson(personStub);
        UndoCommand undoCommand = new UndoCommand();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        try {
            undoCommand.execute(model);
        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
