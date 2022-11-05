package seedu.hrpro.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.model.Model.PREDICATE_SHOW_ALL_PROJECTS;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalProjects.APPLE;
import static seedu.hrpro.testutil.TypicalProjects.BANANA;
import static seedu.hrpro.testutil.TypicalStaff.STAFF_1;
import static seedu.hrpro.testutil.TypicalTasks.TASK_1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.GuiSettings;
import seedu.hrpro.model.project.ProjectNameContainsKeywordsPredicate;
import seedu.hrpro.testutil.HrProBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new HrPro(), new HrPro(modelManager.getHrPro()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setHrProFilePath(Paths.get("hr/pro/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setHrProFilePath(Paths.get("new/hr/pro/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setHrProFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setHrProFilePath(null));
    }

    @Test
    public void setHrProFilePath_validPath_setsHrProFilePath() {
        Path path = Paths.get("hr/pro/file/path");
        modelManager.setHrProFilePath(path);
        assertEquals(path, modelManager.getHrProFilePath());
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInHrPro_returnsFalse() {
        assertFalse(modelManager.hasProject(APPLE));
    }

    @Test
    public void hasProject_projectInHrPro_returnsTrue() {
        modelManager.addProject(APPLE);
        assertTrue(modelManager.hasProject(APPLE));
    }

    @Test
    public void getFilteredProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredProjectList().remove(0));
    }

    @Test
    public void getFilteredStaffList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStaffList().remove(0));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInHrPro_returnsFalse() {
        assertFalse(modelManager.hasTask(TASK_1));
    }

    @Test
    public void hasTask_taskInHrPro_returnsTrue() {
        modelManager.addTask(TASK_1);
        assertTrue(modelManager.hasTask(TASK_1));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        HrPro hrPro = new HrProBuilder().withProject(APPLE)
                .withProject(BANANA).withTask(TASK_1).withStaff(STAFF_1).build();
        HrPro differentHrPro = new HrPro();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(hrPro, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(hrPro, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different hrPro -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentHrPro, userPrefs)));

        // different project filteredList -> returns false
        String[] keywords = APPLE.getProjectName().fullName.split("\\s+");
        modelManager.updateFilteredProjectList(new ProjectNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(hrPro, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setHrProFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(hrPro, differentUserPrefs)));
    }
}
