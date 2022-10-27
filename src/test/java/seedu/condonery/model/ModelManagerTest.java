package seedu.condonery.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalProperties.OASIS;
import static seedu.condonery.testutil.TypicalProperties.PINNACLE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.model.client.ClientDirectory;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.condonery.testutil.PropertyDirectoryBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(
            new PropertyDirectory(),
            new PropertyDirectory(
                modelManager.getPropertyDirectory(),
                modelManager.getUserPrefs().getUserImageDirectoryPath()
            )
        );
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPropertyDirectoryFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPropertyDirectoryFilePath(Paths.get("new/address/book/file/path"));
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
    public void setPropertyDirectoryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPropertyDirectoryFilePath(null));
    }

    @Test
    public void setPropertyDirectoryFilePath_validPath_setsPropertyDirectoryFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPropertyDirectoryFilePath(path);
        assertEquals(path, modelManager.getPropertyDirectoryFilePath());
    }

    @Test
    public void hasProperty_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProperty(null));
    }

    @Test
    public void hasProperty_propertyNotInPropertyDirectory_returnsFalse() {
        assertFalse(modelManager.hasProperty(PINNACLE));
    }

    @Test
    public void hasProperty_propertyInPropertyDirectory_returnsTrue() {
        modelManager.addProperty(PINNACLE);
        assertTrue(modelManager.hasProperty(PINNACLE));
    }

    @Test
    public void getFilteredPropertyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPropertyList().remove(0));
    }

    @Test
    public void equals() {
        PropertyDirectory propertyDirectory =
            new PropertyDirectoryBuilder().withProperty(PINNACLE).withProperty(OASIS).build();
        ClientDirectory clientDirectory =
            new ClientDirectory(); // to create new ClientDirectoryBuilder()
        PropertyDirectory differentPropertyDirectory = new PropertyDirectory();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(propertyDirectory, clientDirectory, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(propertyDirectory, clientDirectory, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different propertyDirectory -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPropertyDirectory, clientDirectory, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = PINNACLE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPropertyList(new PropertyNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(propertyDirectory, clientDirectory, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPropertyDirectoryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(propertyDirectory, clientDirectory, differentUserPrefs)));
    }
}
