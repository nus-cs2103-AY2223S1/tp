package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model, EditCommand, and
 * EditCommandParser) and unit tests for UndoCommand.
 */
class UndoCommandTest {

    @Test
    void execute() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ReadOnlyAddressBook original = (ReadOnlyAddressBook)
                model.getAddressBook().clone();
        EditCommandParser editCommandParser = new EditCommandParser();
        EditCommand editCommand = null;
        try {
            editCommand = editCommandParser.parse("1 p/1234321");
        } catch (ParseException pe) {
            fail();
        }
        try {
            editCommand.execute(model);
        } catch (CommandException e) {
            fail();
        }
        ReadOnlyAddressBook afterEdit = (ReadOnlyAddressBook)
                model.getAddressBook().clone();
        UndoCommand undoCommand1 = new UndoCommand();
        try {
            undoCommand1.execute(model);
        } catch (CommandException e) {
            fail();
        }
        assertEquals(original, model.getAddressBook());

        UndoCommand undoCommand2 = new UndoCommand();
        try {
            undoCommand2.execute(model);
        } catch (CommandException e) {
            fail();
        }
        assertEquals(afterEdit, model.getAddressBook());
    }
}
