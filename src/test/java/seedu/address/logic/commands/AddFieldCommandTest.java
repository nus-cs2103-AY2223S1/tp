package seedu.address.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.fields.AddFieldCommand.MESSAGE_DUPLICATE;
import static seedu.address.logic.commands.fields.AddFieldCommand.NO_INPUT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.fields.AddFieldCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//import seedu.address.model.item.DisplayItem;


public class AddFieldCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*@Test
    public void execute_validField_success() throws CommandException {
        AddFieldCommand addFieldCommand = new AddFieldCommand(INDEX_SECOND, "u", "p", "test");
        addFieldCommand.execute(model);
        DisplayItem itemStub = model.getFromFilteredPerson(INDEX_SECOND);
        assertTrue(itemStub.getAttribute("p").isPresent());
    }*/

    @Test
    public void execute_invalidFType_throwsCommandException() throws CommandException {
        AddFieldCommand addFieldCommand = new AddFieldCommand(INDEX_SECOND, "a", "p", "test");
        assertThrows(CommandException.class, NO_INPUT, () -> addFieldCommand.execute(model));
    }

    /*@Test
    public void execute_duplicateField_throwsCommandException() throws CommandException {
        AddFieldCommand addFieldCommand = new AddFieldCommand(INDEX_SECOND, "u", "p", "test");
        DisplayItem itemStub = model.getFromFilteredPerson(INDEX_SECOND);
        itemStub.addAttribute("u", "test");
        assertThrows(CommandException.class, MESSAGE_DUPLICATE, () -> addFieldCommand.execute(model));
    }*/
}
