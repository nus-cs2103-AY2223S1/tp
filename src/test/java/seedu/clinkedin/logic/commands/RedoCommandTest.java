package seedu.clinkedin.logic.commands;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_CANNOT_REDO;
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

class RedoCommandTest {

    private static final String NAME_STUB = "nameStub";

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Person personStub = new PersonBuilder().build();

    @Test
    void execute_initialState_failure() {
        RedoCommand redoCommand = new RedoCommand();
        assertCommandFailure(redoCommand, model, MESSAGE_CANNOT_REDO);
    }

    @Test
    void execute_redoAddCommand_success() {
        model.addPerson(personStub);
        model.undoAddressBook();
        RedoCommand redoCommand = new RedoCommand();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addPerson(personStub);
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_noNewState_failure() {
        model.addPerson(personStub);
        RedoCommand redoCommand = new RedoCommand();
        assertCommandFailure(redoCommand, model, MESSAGE_CANNOT_REDO);
    }

    @Test
    void execute_redoDeleteCommand_success() {
        model.addPerson(personStub);
        model.deletePerson(personStub);
        model.undoAddressBook();
        RedoCommand redoCommand = new RedoCommand();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_redoEditCommand_success() {
        model.addPerson(personStub);
        Person editedPersonStub = new PersonBuilder(personStub).withName(NAME_STUB).build();
        model.setPerson(personStub, editedPersonStub);
        model.undoAddressBook();
        RedoCommand redoCommand = new RedoCommand();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addPerson(personStub);
        expectedModel.setPerson(personStub, editedPersonStub);
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_redoClearCommand_success() {
        model.addPerson(personStub);
        model.setAddressBook(new AddressBook());
        model.undoAddressBook();
        RedoCommand redoCommand = new RedoCommand();
        Model expectedModel = new ModelManager();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_redundantStatesPurged_failure() {
        model.addPerson(personStub);
        model.deletePerson(personStub);
        model.undoAddressBook();
        model.undoAddressBook();
        model.addPerson(personStub);
        RedoCommand redoCommand = new RedoCommand();
        assertCommandFailure(redoCommand, model, MESSAGE_CANNOT_REDO);
    }

    @Test
    void execute_redoMultipleCommands_success() {
        model.addPerson(personStub);
        model.deletePerson(personStub);
        model.undoAddressBook();
        model.undoAddressBook();
        RedoCommand redoCommand = new RedoCommand();
        try {
            redoCommand.execute(model);
        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
