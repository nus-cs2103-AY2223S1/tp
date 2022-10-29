package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROFILES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.profile.NameContainsKeywordsPredicate;
import seedu.address.testutil.NuSchedulerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new NuScheduler(), new NuScheduler(modelManager.getNuScheduler()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setNuSchedulerFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setNuSchedulerFilePath(Paths.get("new/address/book/file/path"));
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
    public void setNuSchedulerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setNuSchedulerFilePath(null));
    }

    @Test
    public void setNuSchedulerFilePath_validPath_setsNuSchedulerFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setNuSchedulerFilePath(path);
        assertEquals(path, modelManager.getNuSchedulerFilePath());
    }

    @Test
    public void hasEmail_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEmail(null));
    }

    @Test
    public void hasEmail_emailNotInNuScheduler_returnsFalse() {
        assertFalse(modelManager.hasEmail(ALICE));
    }

    @Test
    public void hasEmail_emailInNuScheduler_returnsTrue() {
        modelManager.addProfile(ALICE);
        assertTrue(modelManager.hasEmail(ALICE));
    }

    @Test
    public void hasPhone_nullPhone_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPhone(null));
    }

    @Test
    public void hasPhone_phoneNotInNuScheduler_returnsFalse() {
        assertFalse(modelManager.hasPhone(ALICE));
    }

    @Test
    public void hasPhone_phoneInNuScheduler_returnsTrue() {
        modelManager.addProfile(ALICE);
        assertTrue(modelManager.hasPhone(ALICE));
    }

    @Test
    public void hasTelegram_nullTelegram_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTelegram(null));
    }

    @Test
    public void hasTelegram_telegramNotInNuScheduler_returnsFalse() {
        assertFalse(modelManager.hasTelegram(ALICE));
    }

    @Test
    public void hasTelegram_telegramInNuScheduler_returnsTrue() {
        modelManager.addProfile(ALICE);
        assertTrue(modelManager.hasTelegram(ALICE));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInNuScheduler_returnsFalse() {
        assertFalse(modelManager.hasEvent(PRESENTATION));
    }

    @Test
    public void hasEvent_eventInNuScheduler_returnsTrue() {
        modelManager.addEvent(PRESENTATION);
        assertTrue(modelManager.hasEvent(PRESENTATION));
    }

    @Test
    public void getFilteredProfileList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredProfileList().remove(0));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEventList().remove(0));
    }

    @Test
    public void equals() {
        NuScheduler nuScheduler = new NuSchedulerBuilder().withProfile(ALICE).withProfile(BENSON).build();
        NuScheduler differentNuScheduler = new NuScheduler();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(nuScheduler, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(nuScheduler, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different nuScheduler -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentNuScheduler, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredProfileList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(nuScheduler, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredProfileList(PREDICATE_SHOW_ALL_PROFILES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setNuSchedulerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(nuScheduler, differentUserPrefs)));
    }
}
