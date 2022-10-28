package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.IDA;
import static seedu.address.testutil.TypicalStudents.getTypicalTeachersPet;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTeachersPet;
import seedu.address.model.TeachersPet;

public class JsonTeachersPetStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTeachersPetStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTeachersPet_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTeachersPet(null));
    }

    private java.util.Optional<ReadOnlyTeachersPet> readTeachersPet(String filePath) throws Exception {
        return new JsonTeachersPetStorage(Paths.get(filePath)).readTeachersPet(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTeachersPet("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTeachersPet("notJsonFormatTeachersPet.json"));
    }

    @Test
    public void readTeachersPet_invalidPersonTeachersPet_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTeachersPet("invalidPersonTeachersPet.json"));
    }

    @Test
    public void readTeachersPet_invalidAndValidPersonTeachersPet_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTeachersPet("invalidAndValidPersonTeachersPet.json"));
    }

    @Test
    public void readAndSaveTeachersPet_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTeachersPet.json");
        TeachersPet original = getTypicalTeachersPet();
        JsonTeachersPetStorage jsonTeachersPetStorage = new JsonTeachersPetStorage(filePath);

        // Save in new file and read back
        jsonTeachersPetStorage.saveTeachersPet(original, filePath);
        ReadOnlyTeachersPet readBack = jsonTeachersPetStorage.readTeachersPet(filePath).get();
        assertEquals(original, new TeachersPet(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonTeachersPetStorage.saveTeachersPet(original, filePath);
        readBack = jsonTeachersPetStorage.readTeachersPet(filePath).get();
        assertEquals(original, new TeachersPet(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonTeachersPetStorage.saveTeachersPet(original); // file path not specified
        readBack = jsonTeachersPetStorage.readTeachersPet().get(); // file path not specified
        assertEquals(original, new TeachersPet(readBack));

    }

    @Test
    public void saveTeachersPet_nullTeachersPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeachersPet(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveTeachersPet(ReadOnlyTeachersPet teachersPet, String filePath) {
        try {
            new JsonTeachersPetStorage(Paths.get(filePath))
                    .saveTeachersPet(teachersPet, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTeachersPet_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeachersPet(new TeachersPet(), null));
    }
}
