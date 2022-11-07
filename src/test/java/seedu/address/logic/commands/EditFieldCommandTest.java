package seedu.address.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.fields.AddFieldCommand.NO_INPUT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.logic.commands.fields.AddFieldCommand;
import seedu.address.logic.commands.fields.EditFieldCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//import seedu.address.model.item.DisplayItem;


public class EditFieldCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*@Test
    public void execute_validField_success() throws CommandException {
        AddFieldCommand addFieldCommand = new AddFieldCommand(INDEX_FIRST, "u", "p", "test");
        addFieldCommand.execute(model);
        DisplayItem itemStub = model.getFromFilteredPerson(INDEX_FIRST);
        EditFieldCommand editFieldCommand = new EditFieldCommand(INDEX_FIRST, "u", "p", "test123");
        editFieldCommand.execute(model);
        DisplayItem itemStubEdited = model.getFromFilteredPerson(INDEX_FIRST);
        assertFalse(itemStubEdited.getAttribute("p").equals(itemStub));
    }*/

    @Test
    public void invalidFType_throwsCommandException() {
        EditFieldCommand editFieldCommand = new EditFieldCommand(INDEX_FIRST, "a", "p", "test");
        assertThrows(CommandException.class, NO_INPUT, () -> editFieldCommand.execute(model));
    }
}
