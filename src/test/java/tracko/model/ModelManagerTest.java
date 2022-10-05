package tracko.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static tracko.testutil.Assert.assertThrows;
import static tracko.testutil.TypicalOrders.ORDER_1;
import static tracko.testutil.TypicalOrders.ORDER_2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tracko.commons.core.GuiSettings;
import tracko.model.order.NameContainsKeywordsPredicate;
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
    public void setOrdersFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setOrdersFilePath(null));
    }

    @Test
    public void setOrdersFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("tracko/orders/file/path");
        modelManager.setOrdersFilePath(path);
        assertEquals(path, modelManager.getOrdersFilePath());
    }

//    @Test
//    public void hasPerson_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
//    }
//
//    @Test
//    public void hasPerson_personNotInAddressBook_returnsFalse() {
//        assertFalse(modelManager.hasPerson(ORDER_1));
//    }
//
//    @Test
//    public void hasPerson_personInAddressBook_returnsTrue() {
//        modelManager.addPerson(ORDER_1);
//        assertTrue(modelManager.hasPerson(ORDER_1));
//    }
//
//    @Test
//    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
//        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
//    }

    @Test
    public void equals() {
        TrackO addressBook = new TrackOBuilder().withOrder(ORDER_1).withOrder(ORDER_2).build();
        TrackO differentAddressBook = new TrackO();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

//        // different filteredList -> returns false
//        String[] keywords = ORDER_1.getName().fullName.split("\\s+");
//        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
//        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));
//
//        // resets modelManager to initial state for upcoming tests
//        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
