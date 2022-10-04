package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getSortedAddressBook;
import static seedu.address.testutil.TypicalPersons.getUnsortedAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class SortLexicographicalCommandTest {

    private Model model = new ModelManager(
            getUnsortedAddressBook(), new UserPrefs());

    @Test
    void execute_SortUnfilteredList_success() {
        SortLexicographicalCommand sort = new SortLexicographicalCommand();
        Model expectedModel = new ModelManager(
                getSortedAddressBook(), new UserPrefs());
        String expectedMessage = SortLexicographicalCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sort, model, expectedMessage, expectedModel);
    }
}