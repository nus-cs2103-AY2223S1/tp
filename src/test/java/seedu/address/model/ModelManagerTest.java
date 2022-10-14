package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPANIES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.ALICE;
import static seedu.address.testutil.TypicalCompanies.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.testutil.JeeqTrackerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new JeeqTracker(), new JeeqTracker(modelManager.getJeeqTracker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setJeeqTrackerFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setJeeqTrackerFilePath(Paths.get("new/address/book/file/path"));
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
    public void setJeeqTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setJeeqTrackerFilePath(null));
    }

    @Test
    public void setJeeqTrackerFilePath_validPath_setsJeeqTrackerFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setJeeqTrackerFilePath(path);
        assertEquals(path, modelManager.getJeeqTrackerFilePath());
    }

    @Test
    public void hasCompany_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCompany(null));
    }

    @Test
    public void hasCompany_companyNotInJeeqTracker_returnsFalse() {
        assertFalse(modelManager.hasCompany(ALICE));
    }

    @Test
    public void hasCompany_companyInJeeqTracker_returnsTrue() {
        modelManager.addCompany(ALICE);
        assertTrue(modelManager.hasCompany(ALICE));
    }

    @Test
    public void getFilteredCompanyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCompanyList().remove(0));
    }

    @Test
    public void equals() {
        JeeqTracker jeeqTracker = new JeeqTrackerBuilder().withCompany(ALICE).withCompany(BENSON).build();
        JeeqTracker differentJeeqTracker = new JeeqTracker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(jeeqTracker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(jeeqTracker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different jeeqTracker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentJeeqTracker, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredCompanyList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(jeeqTracker, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setJeeqTrackerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(jeeqTracker, differentUserPrefs)));
    }
}
