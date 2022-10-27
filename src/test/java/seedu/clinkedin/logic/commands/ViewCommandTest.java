package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.person.Person;

public class ViewCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewCommand(null));
    }

    @Test
    public void constructor_validIndex_success() {
        assertDoesNotThrow(() -> new ViewCommand(Index.fromOneBased(1)));
    }

    @Test
    public void execute_validIndex_success() {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));

        Person personToView = model.getFilteredPersonList().get(0);
        String expectedMessage = personToView.toString();

        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToView);
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(Integer.MAX_VALUE));

        assertThrows(CommandException.class, () -> viewCommand.execute(model));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));
        assert viewCommand.equals(viewCommand);
    }

    @Test
    public void equals_sameIndex_returnsTrue() {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));
        ViewCommand viewCommand2 = new ViewCommand(Index.fromOneBased(1));
        assert viewCommand.equals(viewCommand2);
    }

    @Test
    public void equals_differentIndex_returnsFalse() {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));
        ViewCommand viewCommand2 = new ViewCommand(Index.fromOneBased(2));
        assert !viewCommand.equals(viewCommand2);
    }
}
