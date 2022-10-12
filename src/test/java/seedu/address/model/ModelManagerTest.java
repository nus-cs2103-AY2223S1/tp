package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalProperties.HUT;
import static seedu.address.testutil.TypicalProperties.PEAKRESIDENCE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.address.testutil.PersonModelBuilder;
import seedu.address.testutil.PropertyModelBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PersonModel(), new PersonModel(modelManager.getPersonModel()));
        assertEquals(new PropertyModel(), new PropertyModel(modelManager.getPropertyModel()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPersonBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPersonBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPersonModelFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPersonModelFilePath(path);
        assertEquals(path, modelManager.getPersonModelFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void setPropertyModelFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPropertyModelFilePath(null));
    }

    @Test
    public void setPropertyModelFilePath_validPath_setsPropertyModelFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPropertyModelFilePath(path);
        assertEquals(path, modelManager.getPropertyModelFilePath());
    }

    @Test
    public void hasProperty_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProperty(null));
    }

    @Test
    public void hasProperty_propertyNotInPropertyModel_returnsFalse() {
        assertFalse(modelManager.hasProperty(PEAKRESIDENCE));
    }

    @Test
    public void hasProperty_propertyInPropertyModel_returnsTrue() {
        modelManager.addProperty(PEAKRESIDENCE);
        assertTrue(modelManager.hasProperty(PEAKRESIDENCE));
    }

    @Test
    public void getFilteredPropertyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPropertyList().remove(0));
    }

    @Test
    public void equals() {
        PersonModel personModel = new PersonModelBuilder().withPerson(ALICE).withPerson(BENSON).build();
        PersonModel differentPersonModel = new PersonModel();
        PropertyModel propertyModel = new PropertyModelBuilder().withProperty(PEAKRESIDENCE).withProperty(HUT).build();
        PropertyModel differentPropertyModel = new PropertyModel();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(personModel, propertyModel, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(personModel, propertyModel, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different personModel -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPersonModel, propertyModel, userPrefs)));

        // different propertyModel -> returns false
        assertFalse(modelManager.equals(new ModelManager(personModel, differentPropertyModel, userPrefs)));

        // different personModel and propertyModel -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPersonModel, differentPropertyModel, userPrefs)));

        // different filteredPersonList -> returns false
        String[] keywordsForAlice = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywordsForAlice)));
        assertFalse(modelManager.equals(new ModelManager(personModel, propertyModel, userPrefs)));

        // resets filteredPersonList in modelManager for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different filteredPropertyList -> returns false
        String[] keywordsForPeak = PEAKRESIDENCE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPropertyList(
                new PropertyNameContainsKeywordsPredicate(Arrays.asList(keywordsForPeak)));
        assertFalse(modelManager.equals(new ModelManager(personModel, propertyModel, userPrefs)));

        // resets filteredPropertyList in modelManager for upcoming tests
        modelManager.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPersonBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(personModel, propertyModel, differentUserPrefs)));
    }
}
