package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.FACEBOOK;
import static seedu.address.testutil.TypicalApplications.SHOPEE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.application.CompanyContainsKeywordsPredicate;
import seedu.address.model.application.PositionContainsKeywordsPredicate;
import seedu.address.testutil.ApplicationBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ApplicationBook(), new ApplicationBook(modelManager.getApplicationBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setApplicationBookFilePath(Paths.get("application/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setApplicationBookFilePath(Paths.get("new/application/book/file/path"));
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
    public void setApplicationBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setApplicationBookFilePath(null));
    }

    @Test
    public void setApplicationBookFilePath_validPath_setsApplicationBookFilePath() {
        Path path = Paths.get("application/book/file/path");
        modelManager.setApplicationBookFilePath(path);
        assertEquals(path, modelManager.getApplicationBookFilePath());
    }

    @Test
    public void hasApplication_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplication(null));
    }

    @Test
    public void hasApplication_applicationNotInApplicationBook_returnsFalse() {
        assertFalse(modelManager.hasApplication(FACEBOOK));
    }

    @Test
    public void hasApplication_applicationInApplicationBook_returnsTrue() {
        modelManager.addApplication(FACEBOOK);
        assertTrue(modelManager.hasApplication(FACEBOOK));
    }

    @Test
    public void getFilteredApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicationList()
                .remove(0));
    }

    @Test
    public void equals() {
        ApplicationBook applicationBook = new ApplicationBookBuilder().withApplication(FACEBOOK)
                .withApplication(SHOPEE).build();
        ApplicationBook differentApplicationBook = new ApplicationBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(applicationBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(applicationBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different ApplicationBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentApplicationBook, userPrefs)));

        // different filteredList (filtered by company) -> returns false
        String[] keywords = FACEBOOK.getCompany().company.split("\\s+");
        modelManager.updateFilteredApplicationList(new CompanyContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(applicationBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);

        // different filteredList (filtered by position) -> returns false
        String keyword = SHOPEE.getPosition().value.split("\\s+")[0]; //"Frontend"
        List<String> positionKeyword = new ArrayList<String>();
        positionKeyword.add(keyword);
        modelManager.updateFilteredApplicationList(new PositionContainsKeywordsPredicate(positionKeyword));
        assertFalse(modelManager.equals(new ModelManager(applicationBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setApplicationBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(applicationBook, differentUserPrefs)));
    }
}
