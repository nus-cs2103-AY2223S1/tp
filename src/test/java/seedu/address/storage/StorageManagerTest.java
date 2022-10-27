package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.commons.core.GuiSettings.LIGHT_THEME_STRING;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;


public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        Path tutorPath = getTempFilePath("nothing.json");
        Path studentPath = getTempFilePath("null.json");
        Path tuitionClassPath = getTempFilePath("zilch.json");
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(tutorPath, studentPath,
                tuitionClassPath);
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(
                300, 600, 4, 6, LIGHT_THEME_STRING));
        original.setTutorAddressBookFilePath(getTempFilePath("something"));
        original.setStudentAddressBookFilePath(getTempFilePath("another thing"));
        original.setTuitionClassAddressBookFilePath(getTempFilePath("some other thing"));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        AddressBook original = getTypicalAddressBook();
        storageManager.saveAllAddressBook(original);
        ReadOnlyAddressBook retrieved = storageManager.readAllAddressBook().get();
        assertEquals(original, new AddressBook(retrieved));
    }

    @Test
    public void getTutorAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath(AddressBookStorage.AddressBookCategories.TUTORS));
    }

    @Test
    public void getStudentAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath(AddressBookStorage.AddressBookCategories.STUDENTS));
    }

    @Test
    public void getTuitionClassAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath(AddressBookStorage.AddressBookCategories.TUITIONCLASSES));
    }

}
