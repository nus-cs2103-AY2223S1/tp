package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }
    @Test
    public void setAllAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAllAddressBookFilePath(null));
    }

    @Test
    public void addAddressBook_void_addsAddressBook() {
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.addAddressBook());
    }
    @Test
    public void addAddressBook_void_addsDefaultName() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAllAddressBookFilePath(new Path[]{});
        userPrefs.addAddressBook();
        assertEquals(userPrefs.getAllAddressBookFilePath()[0], new UserPrefs().getAddressBookFilePath());
    }
    @Test
    public void addAddressBook_void_maximumAddressBookReached() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAllAddressBookFilePath(getFullAddressBookPath());
        assertFalse(userPrefs.addAddressBook());
    }

    private Path[] getFullAddressBookPath() {
        final int maxBooksAllowed = 5;
        Path[] fullPath = new Path[maxBooksAllowed];
        for (int i = 0; i < maxBooksAllowed; i++) {
            fullPath[i] = Paths.get("data", i + ".json");
        }
        return fullPath;
    }
    @Test
    public void getNextAddressBook_void_incrementsIndex() {
        UserPrefs userPrefs = new UserPrefs();
        int defaultIndex = userPrefs.getStoredIndex();
        userPrefs.setAllAddressBookFilePath(getFullAddressBookPath());
        userPrefs.getNextAddressBookPath();
        assertEquals(defaultIndex + 1, userPrefs.getStoredIndex());
    }

    @Test
    public void getNextAddressBook_void_getsNextAddressBook() {
        UserPrefs userPrefs = new UserPrefs();
        int defaultIndex = userPrefs.getStoredIndex();
        userPrefs.setAllAddressBookFilePath(getFullAddressBookPath());
        Path correctNextBook = getFullAddressBookPath()[defaultIndex + 1];
        Path nextBook = userPrefs.getNextAddressBookPath();
        assertEquals(nextBook, correctNextBook);
    }

    @Test
    public void renameAddressBook_void_renamesAddressBook() throws IOException {
        UserPrefs userPrefs = new UserPrefs();
        String testDirString = "data";

        int range = 1000;
        int min = 1000;
        int randomNumber = (int) (Math.random() * range) + min;
        String testFileString = System.currentTimeMillis() + "" + randomNumber + ".json";

        randomNumber = (int) (Math.random() * range) + min;
        String testFileRenamedString = System.currentTimeMillis() + "" + randomNumber + ".json";;

        Path testPath = Paths.get(testDirString, testFileString);
        File testFile = testPath.toFile();
        testFile.createNewFile();
        testFile.deleteOnExit();

        Path[] testAllPath = { testPath };
        userPrefs.setAllAddressBookFilePath(testAllPath);
        userPrefs.setAddressBookFilePath(testPath);

        userPrefs.renameFile(testFileRenamedString);

        Path renamedPath = Paths.get(testDirString, testFileRenamedString + ".json");
        File renamedFile = renamedPath.toFile();
        renamedFile.deleteOnExit();

        assertTrue(FileUtil.isFileExists(renamedPath));
        assertEquals(userPrefs.getAllAddressBookFilePath()[0], renamedPath);
    }
}
