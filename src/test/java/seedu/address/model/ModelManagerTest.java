package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalProperties.PEAKRESIDENCE;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new BuyerBook(), new BuyerBook(modelManager.getBuyerBook()));
        assertEquals(new PropertyBook(), new PropertyBook(modelManager.getPropertyBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setBuyerBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setBuyerBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setPersonBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setBuyerBookFilePath(null));
    }

    @Test
    public void setPersonBookFilePath_validPath_setsPersonBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setBuyerBookFilePath(path);
        assertEquals(path, modelManager.getBuyerBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBuyer(null));
    }

    @Test
    public void hasPerson_personNotInPersonBook_returnsFalse() {
        assertFalse(modelManager.hasBuyer(ALICE));
    }

    //    @Test
    //    public void hasPerson_personInAddressBook_returnsTrue() {
    //        modelManager.addPerson(ALICE);
    //        assertTrue(modelManager.hasPerson(ALICE));
    //    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredBuyerList().remove(0));
    }

    @Test
    public void setPropertyBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPropertyBookFilePath(null));
    }

    @Test
    public void setPropertyBookFilePath_validPath_setsPropertyBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPropertyBookFilePath(path);
        assertEquals(path, modelManager.getPropertyBookFilePath());
    }

    @Test
    public void hasProperty_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProperty(null));
    }

    @Test
    public void hasProperty_propertyNotInPropertyBook_returnsFalse() {
        assertFalse(modelManager.hasProperty(PEAKRESIDENCE));
    }

    @Test
    public void hasProperty_propertyInPropertyBook_returnsTrue() {
        modelManager.addProperty(PEAKRESIDENCE);
        assertTrue(modelManager.hasProperty(PEAKRESIDENCE));
    }

    @Test
    public void getFilteredPropertyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPropertyList().remove(0));
    }

    //    @Test
    //    public void equals() {
    //        BuyerBook personBook = new PersonModelBuilder().withPerson(ALICE).withPerson(BENSON).build();
    //        BuyerBook differentPersonBook = new BuyerBook();
    //        PropertyBook propertyBook = new PropertyModelBuilder().withProperty(PEAKRESIDENCE)
    //        .withProperty(HUT).build();
    //        PropertyBook differentPropertyBook = new PropertyBook();
    //        UserPrefs userPrefs = new UserPrefs();
    //
    //        // same values -> returns true
    //        modelManager = new ModelManager(personBook, propertyBook, userPrefs);
    //        ModelManager modelManagerCopy = new ModelManager(personBook, propertyBook, userPrefs);
    //        assertTrue(modelManager.equals(modelManagerCopy));
    //
    //        // same object -> returns true
    //        assertTrue(modelManager.equals(modelManager));
    //
    //        // null -> returns false
    //        assertFalse(modelManager.equals(null));
    //
    //        // different types -> returns false
    //        assertFalse(modelManager.equals(5));
    //
    //        // different personBook -> returns false
    //        assertFalse(modelManager.equals(new ModelManager(differentPersonBook, propertyBook, userPrefs)));
    //
    //        // different propertyBook -> returns false
    //        assertFalse(modelManager.equals(new ModelManager(personBook, differentPropertyBook, userPrefs)));
    //
    //        // different personBook and propertyBook -> returns false
    //        assertFalse(modelManager.equals(new ModelManager(differentPersonBook, differentPropertyBook, userPrefs)));
    //
    //        // different filteredPersonList -> returns false
    //        String[] keywordsForAlice = ALICE.getName().fullName.split("\\s+");
    //        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywordsForAlice)));
    //        assertFalse(modelManager.equals(new ModelManager(personBook, propertyBook, userPrefs)));
    //
    //        // resets filteredPersonList in modelManager for upcoming tests
    //        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    //
    //        // different filteredPropertyList -> returns false
    //        String[] keywordsForPeak = PEAKRESIDENCE.getPropertyName().fullName.split("\\s+");
    //        modelManager.updateFilteredPropertyList(
    //                new PropertyNameContainsKeywordsPredicate(Arrays.asList(keywordsForPeak)));
    //        assertFalse(modelManager.equals(new ModelManager(personBook, propertyBook, userPrefs)));
    //
    //        // resets filteredPropertyList in modelManager for upcoming tests
    //        modelManager.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    //
    //        // different userPrefs -> returns false
    //        UserPrefs differentUserPrefs = new UserPrefs();
    //        differentUserPrefs.setPersonBookFilePath(Paths.get("differentFilePath"));
    //        assertFalse(modelManager.equals(new ModelManager(personBook, propertyBook, differentUserPrefs)));
    //    }
}
