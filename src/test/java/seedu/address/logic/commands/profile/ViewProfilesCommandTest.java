package seedu.address.logic.commands.profile;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProfileAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROFILE;
import static seedu.address.testutil.TypicalProfiles.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.profile.ViewProfilesCommand;
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
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
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
}
