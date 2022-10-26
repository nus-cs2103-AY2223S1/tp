package tracko.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
// import static tracko.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static tracko.testutil.Assert.assertThrows;
import static tracko.testutil.TypicalOrders.ORDER_1;
import static tracko.testutil.TypicalOrders.ORDER_2;

import java.nio.file.Path;
import java.nio.file.Paths;
// import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tracko.commons.core.GuiSettings;
// import tracko.model.order.NameContainsKeywordsPredicate;
import tracko.testutil.TrackOBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TrackO(), new TrackO(modelManager.getTrackO()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTrackOFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackOFilePath(null));
    }

    @Test
    public void setTrackOFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("tracko/orders/file/path");
        modelManager.setTrackOFilePath(path);
        assertEquals(path, modelManager.getTrackOFilePath());
    }

    @Test
    public void equals() {
        TrackO trackO = new TrackOBuilder().withOrder(ORDER_1).withOrder(ORDER_2).build();
        TrackO differentTrackO = new TrackO();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(trackO, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(trackO, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different trackO -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTrackO, userPrefs)));

        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(trackO, differentUserPrefs)));
    }
}
