package seedu.address.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.fields.AddFieldCommand.NO_INPUT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.logic.commands.fields.AddFieldCommand;
import seedu.address.logic.commands.fields.DeleteFieldCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class DeleteFieldCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*@Test
    public void execute_validField_success() throws CommandException {
        AddFieldCommand addFieldCommand = new AddFieldCommand(INDEX_SECOND, "u", "p", "test");
        addFieldCommand.execute(model);
        DeleteFieldCommand deleteFieldCommand = new DeleteFieldCommand(INDEX_SECOND, "u", "p");
        deleteFieldCommand.execute(model);
        seedu.address.model.item.DisplayItem itemStub = model.getFromFilteredPerson(INDEX_SECOND);
        assertFalse(itemStub.getAttribute("p").isPresent());
    }*/

    @Test
    public void execute_invalidFType_throwsCommandException() throws CommandException {
        DeleteFieldCommand deleteFieldCommand = new DeleteFieldCommand(INDEX_SECOND, "a", "p");
        assertThrows(CommandException.class, NO_INPUT, () -> deleteFieldCommand.execute(model));
    }
}
