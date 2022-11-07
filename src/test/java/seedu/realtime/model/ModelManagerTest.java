package seedu.realtime.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.realtime.testutil.Assert.assertThrows;
import static seedu.realtime.testutil.TypicalClients.ALICE;
import static seedu.realtime.testutil.TypicalClients.BENSON;
import static seedu.realtime.testutil.TypicalOffers.AMY;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.realtime.commons.core.GuiSettings;
import seedu.realtime.testutil.RealTimeBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new RealTime(), new RealTime(modelManager.getRealTime()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setRealTimeFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setRealTimeFilePath(Paths.get("new/address/book/file/path"));
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
    public void setRealTimeFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setRealTimeFilePath(null));
    }

    @Test
    public void setRealTimeFilePath_validPath_setsRealTimeFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setRealTimeFilePath(path);
        assertEquals(path, modelManager.getRealTimeFilePath());
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInRealTime_returnsFalse() {
        assertFalse(modelManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInRealTime_returnsTrue() {
        modelManager.addClient(ALICE);
        assertTrue(modelManager.hasClient(ALICE));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void hasOffer_nullOffer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasOffer(null));
    }

    @Test
    public void hasOffer_offerNotInRealTime_returnsFalse() {
        assertFalse(modelManager.hasOffer(AMY));
    }

    @Test
    public void hasOffer_offerInRealTime_returnsTrue() {
        modelManager.addOffer(AMY);
        assertTrue(modelManager.hasOffer(AMY));
    }

    @Test
    public void getFilteredOfferList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredOfferList().remove(0));
    }

    @Test
    public void equals() {
        RealTime realTime = new RealTimeBuilder().withClient(ALICE).withClient(BENSON).build();
        RealTime differentRealTime = new RealTime();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(realTime, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(realTime, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different realTime -> returns false
        //assertFalse(modelManager.equals(new ModelManager(differentRealTime, userPrefs)));

        // different filteredList -> returns false
        //String[] keywords = ALICE.getName().fullName.split("\\s+");
        //modelManager.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        //assertFalse(modelManager.equals(new ModelManager(realTime, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setRealTimeFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(realTime, differentUserPrefs)));
    }
}
