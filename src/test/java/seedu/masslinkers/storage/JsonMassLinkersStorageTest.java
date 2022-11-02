package seedu.masslinkers.storage;
//@author jonasgwt
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalStudents.ALICE;
import static seedu.masslinkers.testutil.TypicalStudents.HOON;
import static seedu.masslinkers.testutil.TypicalStudents.IDA;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.masslinkers.commons.exceptions.DataConversionException;
import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.model.ReadOnlyMassLinkers;

public class JsonMassLinkersStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMassLinkersStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMassLinkers_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMassLinkers(null));
    }

    private java.util.Optional<ReadOnlyMassLinkers> readMassLinkers(String filePath) throws Exception {
        return new JsonMassLinkersStorage(Paths.get(filePath)).readMassLinkers(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMassLinkers("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMassLinkers("notJsonFormatMassLinkers.json"));
    }

    @Test
    public void readMassLinkers_invalidStudentMassLinkers_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMassLinkers("invalidStudentMassLinkers.json"));
    }

    @Test
    public void readMassLinkers_invalidAndValidStudentMassLinkers_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMassLinkers("invalidAndValidStudentMassLinkers.json"));
    }

    @Test
    public void readAndSaveMassLinkers_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMassLinkers.json");
        MassLinkers original = getTypicalMassLinkers();
        JsonMassLinkersStorage jsonMassLinkersStorage = new JsonMassLinkersStorage(filePath);

        // Save in new file and read back
        jsonMassLinkersStorage.saveMassLinkers(original, filePath);
        ReadOnlyMassLinkers readBack = jsonMassLinkersStorage.readMassLinkers(filePath).get();
        assertEquals(original, new MassLinkers(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonMassLinkersStorage.saveMassLinkers(original, filePath);
        readBack = jsonMassLinkersStorage.readMassLinkers(filePath).get();
        assertEquals(original, new MassLinkers(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonMassLinkersStorage.saveMassLinkers(original); // file path not specified
        readBack = jsonMassLinkersStorage.readMassLinkers().get(); // file path not specified
        assertEquals(original, new MassLinkers(readBack));

    }

    @Test
    public void saveMassLinkers_nullMassLinkers_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMassLinkers(null, "SomeFile.json"));
    }

    /**
     * Saves {@code massLinkers} at the specified {@code filePath}.
     */
    private void saveMassLinkers(ReadOnlyMassLinkers massLinkers, String filePath) {
        try {
            new JsonMassLinkersStorage(Paths.get(filePath))
                    .saveMassLinkers(massLinkers, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMassLinkers_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMassLinkers(new MassLinkers(), null));
    }
}
