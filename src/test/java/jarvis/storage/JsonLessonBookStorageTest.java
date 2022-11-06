package jarvis.storage;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalLessons.MC_2;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static jarvis.testutil.TypicalLessons.getTypicalLessonBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import jarvis.commons.exceptions.DataConversionException;
import jarvis.model.LessonBook;
import jarvis.model.ReadOnlyLessonBook;

public class JsonLessonBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonLessonBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readLessonBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readLessonBook(null));
    }

    private java.util.Optional<ReadOnlyLessonBook> readLessonBook(String filePath) throws Exception {
        return new JsonLessonBookStorage(Paths.get(filePath)).readLessonBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLessonBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readLessonBook("notJsonFormatLessonBook.json"));
    }

    @Test
    public void readLessonBook_invalidLessonLessonBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLessonBook("invalidLessonLessonBook.json"));
    }

    @Test
    public void readLessonBook_invalidAndValidLessonLessonBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLessonBook("invalidAndValidLessonLessonBook.json"));
    }

    @Test
    public void readAndSaveLessonBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempLessonBook.json");
        LessonBook original = getTypicalLessonBook();
        JsonLessonBookStorage jsonLessonBookStorage = new JsonLessonBookStorage(filePath);

        // Save in new file and read back
        jsonLessonBookStorage.saveLessonBook(original, filePath);
        ReadOnlyLessonBook readBack = jsonLessonBookStorage.readLessonBook(filePath).get();
        assertEquals(original, new LessonBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeLesson(CONSULT_1);
        original.removeLesson(STUDIO_1);
        original.addLesson(MC_2);
        jsonLessonBookStorage.saveLessonBook(original, filePath);
        readBack = jsonLessonBookStorage.readLessonBook(filePath).get();
        assertEquals(original, new LessonBook(readBack));

        // Save and read without specifying file path
        original.removeLesson(MC_1);
        jsonLessonBookStorage.saveLessonBook(original); // file path not specified
        readBack = jsonLessonBookStorage.readLessonBook().get(); // file path not specified
        assertEquals(original, new LessonBook(readBack));

    }

    @Test
    public void saveLessonBook_nullLessonBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLessonBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code LessonBook} at the specified {@code filePath}.
     */
    private void saveLessonBook(ReadOnlyLessonBook lessonBook, String filePath) {
        try {
            new JsonLessonBookStorage(Paths.get(filePath))
                    .saveLessonBook(lessonBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLessonBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLessonBook(new LessonBook(), null));
    }
}
