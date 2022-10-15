package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class CommandManagerTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandManager commandManager = new CommandManager();

    @Test
    public void undo_undoableCommandExecuted_undoSuccess() throws Exception {
        Person personToAdd = new PersonBuilder().build();
        CreateCommand createCommand = new CreateCommand(personToAdd);

        createCommand.execute(model);
        commandManager.pushNewCommand(createCommand);
        CommandResult commandResult = commandManager.undo(model);

        assertEquals(String.format(createCommand.MESSAGE_UNDO, personToAdd), commandResult.getFeedbackToUser());
    }

    @Test
    public void undo_emptyUndoStack_throwsCommandException() {
        assertThrows(CommandException.class, () -> commandManager.undo(model));
    }

    @Test
    public void redo_undoableCommandUndone_redoSuccess() throws Exception {
        Person personToAdd = new PersonBuilder().build();
        CreateCommand createCommand = new CreateCommand(personToAdd);

        createCommand.execute(model);
        commandManager.pushNewCommand(createCommand);
        commandManager.undo(model);
        CommandResult commandResult = commandManager.redo(model);

        assertEquals(String.format(createCommand.MESSAGE_REDO, personToAdd), commandResult.getFeedbackToUser());
    }

    @Test
    public void redo_emptyRedoStack_throwsCommandException() {
        assertThrows(CommandException.class, () -> commandManager.redo(model));
    }

    @Test
    public void redo_newUndoableCommandExecutedAfterUndo_throwsCommandException() throws Exception {
        Person personToAdd = new PersonBuilder().build();
        CreateCommand createCommand = new CreateCommand(personToAdd);
        UndoableCommand nextCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        createCommand.execute(model);
        createCommand.undo(model);
        nextCommand.execute(model);
        commandManager.pushNewCommand(nextCommand);

        assertThrows(CommandException.class, () -> commandManager.redo(model));
    }

    @Test
    public void pushNewCommand_nonUndoableCommandPushed_doesNothing() {
        ListCommand listCommand = new ListCommand();

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        listCommand.execute(model);
        commandManager.pushNewCommand(listCommand);

        assertEquals(expectedModel, model);
    }
}
