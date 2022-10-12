package hobbylist.model;

import static hobbylist.model.Model.PREDICATE_SHOW_ALL_ACTIVITIES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import hobbylist.commons.core.GuiSettings;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;
import hobbylist.testutil.Assert;
import hobbylist.testutil.HobbyListBuilder;
import hobbylist.testutil.TypicalActivities;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new HobbyList(), new HobbyList(modelManager.getHobbyList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setHobbyListFilePath(Paths.get("hobby/list/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setHobbyListFilePath(Paths.get("new/hobby/list/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setHobbyListFilePath_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setHobbyListFilePath(null));
    }

    @Test
    public void setHobbyListFilePath_validPath_setsHobbyListFilePath() {
        Path path = Paths.get("hobby/list/file/path");
        modelManager.setHobbyListFilePath(path);
        assertEquals(path, modelManager.getHobbyListFilePath());
    }

    @Test
    public void hasActivity_nullActivity_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.hasActivity(null));
    }

    @Test
    public void hasActivity_activityNotInHobbyList_returnsFalse() {
        assertFalse(modelManager.hasActivity(TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void hasActivity_activityInHobbyList_returnsTrue() {
        modelManager.addActivity(TypicalActivities.ACTIVITY_A);
        assertTrue(modelManager.hasActivity(TypicalActivities.ACTIVITY_A));
    }

    @Test
    public void getFilteredActivityList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
                -> modelManager.getFilteredActivityList().remove(0));
    }

    @Test
    public void equals() {
        HobbyList hobbyList = new HobbyListBuilder().withActivity(TypicalActivities.ACTIVITY_A)
                .withActivity(TypicalActivities.ACTIVITY_B).build();
        HobbyList differentHobbyList = new HobbyList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(hobbyList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(hobbyList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different HobbyList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentHobbyList, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TypicalActivities.ACTIVITY_A.getName().fullName.split("\\s+");
        modelManager.updateFilteredActivityList(new NameOrDescContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(hobbyList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredActivityList(PREDICATE_SHOW_ALL_ACTIVITIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setHobbyListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(hobbyList, differentUserPrefs)));
    }
}
