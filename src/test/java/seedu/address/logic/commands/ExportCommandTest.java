package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ExportCommandTest {

    @Test
    public void execute_emptyDisplayedList_success() {
        Model model = new ModelManager();

        assertDoesNotThrow(() -> new ExportCommand().execute(model));
    }

    @Test
    public void execute_nonEmptyDisplayedList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertDoesNotThrow(() -> new ExportCommand().execute(model));
    }
}
