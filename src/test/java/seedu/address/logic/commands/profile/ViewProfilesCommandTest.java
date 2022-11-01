package seedu.address.logic.commands.profile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProfileAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROFILE;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewProfilesCommand.
 */
public class ViewProfilesCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalNuScheduler(), new UserPrefs());
        expectedModel = new ModelManager(model.getNuScheduler(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewProfilesCommand(), model,
                ViewProfilesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showProfileAtIndex(model, INDEX_FIRST_PROFILE);
        assertCommandSuccess(new ViewProfilesCommand(), model,
                ViewProfilesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        ViewProfilesCommand viewProfilesCommand = new ViewProfilesCommand();

        // same class -> returns true
        assertTrue(viewProfilesCommand.equals(new ViewProfilesCommand()));

        // same object -> returns true
        assertTrue(viewProfilesCommand.equals(viewProfilesCommand));

        // null -> returns false
        assertFalse(viewProfilesCommand.equals(null));

        // different types -> returns false
        assertFalse(viewProfilesCommand.equals(1));
    }
}
