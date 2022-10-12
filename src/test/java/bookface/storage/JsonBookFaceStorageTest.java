package bookface.storage;

import static bookface.testutil.Assert.assertThrows;
import static bookface.testutil.TypicalPersons.ALICE;
import static bookface.testutil.TypicalPersons.HOON;
import static bookface.testutil.TypicalPersons.IDA;
import static bookface.testutil.TypicalPersons.getTypicalBookFaceData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import bookface.commons.exceptions.DataConversionException;
import bookface.model.BookFace;
import bookface.model.ReadOnlyBookFace;

public class JsonBookFaceStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonBookFaceStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readBookFace_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readBookFace(null));
    }

    private java.util.Optional<ReadOnlyBookFace> readBookFace(String filePath) throws Exception {
        return new JsonBookFaceStorage(Paths.get(filePath)).readBookFace(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readBookFace("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readBookFace("notJsonFormatBookFace.json"));
    }

    @Test
    public void readBookFace_invalidPersonBookFace_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBookFace("invalidPersonBookFace.json"));
    }

    @Test
    public void readBookFace_invalidAndValidPersonBookFace_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBookFace("invalidAndValidPersonBookFace.json"));
    }

    @Test
    public void readAndSaveBookFace_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempBookFace.json");
        BookFace original = getTypicalBookFaceData();
        JsonBookFaceStorage jsonBookFaceStorage = new JsonBookFaceStorage(filePath);

        // Save in new file and read back
        jsonBookFaceStorage.saveBookFace(original, filePath);
        ReadOnlyBookFace readBack = jsonBookFaceStorage.readBookFace(filePath).get();
        assertEquals(original, new BookFace(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonBookFaceStorage.saveBookFace(original, filePath);
        readBack = jsonBookFaceStorage.readBookFace(filePath).get();
        assertEquals(original, new BookFace(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonBookFaceStorage.saveBookFace(original); // file path not specified
        readBack = jsonBookFaceStorage.readBookFace().get(); // file path not specified
        assertEquals(original, new BookFace(readBack));

    }

    @Test
    public void saveBookFace_nullBookFace_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBookFace(null, "SomeFile.json"));
    }

    /**
     * Saves {@code bookFace} at the specified {@code filePath}.
     */
    private void saveBookFace(ReadOnlyBookFace bookFace, String filePath) {
        try {
            new JsonBookFaceStorage(Paths.get(filePath))
                    .saveBookFace(bookFace, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveBookFace_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBookFace(new BookFace(), null));
    }
}
