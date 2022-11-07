package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.deletePersonAtIndex;
import static seedu.address.testutil.TypicalPersons.getTypicalSurvin;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class UndoCommandTest {

    @Test
    public void execute_undoAfterDelete() {
        Model model = new ModelManager(getTypicalSurvin(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSurvin(), new UserPrefs());

        Index index = Index.fromZeroBased(0);

        deletePersonAtIndex(model, index);
        deletePersonAtIndex(expectedModel, index);

        expectedModel.undoSurvin();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_CANNOT_UNDO);
    }

    @Test
    public void execute_undoAfterAdd() {
        Model model = new ModelManager(getTypicalSurvin(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSurvin(), new UserPrefs());
        Person validPerson = new PersonBuilder().build();

        model.addPerson(validPerson);
        model.commitSurvin();
        expectedModel.addPerson(validPerson);
        expectedModel.commitSurvin();

        expectedModel.undoSurvin();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_CANNOT_UNDO);
    }

}
