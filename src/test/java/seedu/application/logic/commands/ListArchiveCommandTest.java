package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.application.logic.commands.CommandTestUtil.showApplicationByArchiveStatus;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBookWithArchive;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListArchiveCommand.
 */
public class ListArchiveCommandTest {
    private Model model;
    private Model modelWithArchivedApplication;
    private Model expectedModel;
    private Model expectedModelWithArchivedApplication;

    @BeforeEach
    public void setUp() {
        modelWithArchivedApplication = new ModelManager(getTypicalApplicationBookWithArchive(), new UserPrefs());
        model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModelWithArchivedApplication = new ModelManager(
                modelWithArchivedApplication.getApplicationBook(), new UserPrefs());
    }

    @Test
    public void execute_listHasNoArchived_showEmptyList() {
        showNoApplication(expectedModel);
        assertCommandSuccess(new ListArchiveCommand(), model,
                ListArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFilteredWithNoArchived_showEmptyList() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        showNoApplication(expectedModel);
        assertCommandSuccess(new ListArchiveCommand(), model,
                ListArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listHasArchived_showsListWithArchived() {
        showApplicationByArchiveStatus(expectedModelWithArchivedApplication, true);
        assertCommandSuccess(new ListArchiveCommand(), modelWithArchivedApplication,
                ListArchiveCommand.MESSAGE_SUCCESS, expectedModelWithArchivedApplication);
    }

    @Test
    public void execute_listIsFilteredInArchiveList_showsSameList() {
        showApplicationByArchiveStatus(expectedModelWithArchivedApplication, true);
        showApplicationByArchiveStatus(modelWithArchivedApplication, true);
        assertCommandSuccess(new ListArchiveCommand(), modelWithArchivedApplication,
                ListArchiveCommand.MESSAGE_SUCCESS, expectedModelWithArchivedApplication);
    }

    /**
     * Updates {@code model}'s filtered list to show no application.
     */
    private void showNoApplication(Model model) {
        model.updateFilteredApplicationList(p -> false);

        assertTrue(model.getFilteredApplicationList().isEmpty());
    }
}
