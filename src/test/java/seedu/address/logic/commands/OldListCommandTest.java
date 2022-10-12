package seedu.address.logic.commands;

import static seedu.address.logic.commands.OldCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.OldCommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.OldModel;
import seedu.address.model.OldModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the OldModel) and unit tests for OldListCommand.
 */
public class OldListCommandTest {

    private OldModel model;
    private OldModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new OldModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new OldModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new OldListCommand(), model, OldListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new OldListCommand(), model, OldListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
