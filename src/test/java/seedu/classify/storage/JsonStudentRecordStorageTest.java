package seedu.classify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.classify.testutil.Assert.assertThrows;
import static seedu.classify.testutil.TypicalStudents.ALICE;
import static seedu.classify.testutil.TypicalStudents.HOON;
import static seedu.classify.testutil.TypicalStudents.IDA;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.classify.commons.exceptions.DataConversionException;
import seedu.classify.model.ReadOnlyStudentRecord;
import seedu.classify.model.StudentRecord;

public class JsonStudentRecordStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonStudentRecordStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readStudentRecord_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readStudentRecord(null));
    }

    private java.util.Optional<ReadOnlyStudentRecord> readStudentRecord(String filePath) throws Exception {
        return new JsonStudentRecordStorage(Paths.get(filePath))
                .readStudentRecord(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readStudentRecord("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readStudentRecord("notJsonFormatStudentRecord.json"));
    }

    @Test
    public void readStudentRecord_invalidStudentRecord_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStudentRecord("invalidStudentRecord.json"));
    }

    @Test
    public void readStudentRecord_invalidAndValidPersonStudentRecord_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStudentRecord("invalidAndValidStudentRecord.json"));
    }

    @Test
    public void readAndSaveStudentRecord_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempStudentRecord.json");
        StudentRecord original = getTypicalStudentRecord();
        JsonStudentRecordStorage jsonStudentRecordStorage = new JsonStudentRecordStorage(filePath);

        // Save in new file and read back
        jsonStudentRecordStorage.saveStudentRecord(original, filePath);
        ReadOnlyStudentRecord readBack = jsonStudentRecordStorage.readStudentRecord(filePath).get();
        assertEquals(original, new StudentRecord(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removePerson(ALICE);
        jsonStudentRecordStorage.saveStudentRecord(original, filePath);
        readBack = jsonStudentRecordStorage.readStudentRecord(filePath).get();
        assertEquals(original, new StudentRecord(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonStudentRecordStorage.saveStudentRecord(original); // file path not specified
        readBack = jsonStudentRecordStorage.readStudentRecord().get(); // file path not specified
        assertEquals(original, new StudentRecord(readBack));

    }

    @Test
    public void saveStudentRecord_nullStudentRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStudentRecord(null, "SomeFile.json"));
    }

    /**
     * Saves {@code studentRecord} at the specified {@code filePath}.
     */
    private void saveStudentRecord(ReadOnlyStudentRecord studentRecord, String filePath) {
        try {
            new JsonStudentRecordStorage(Paths.get(filePath))
                    .saveStudentRecord(studentRecord, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveStudentRecord_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStudentRecord(new StudentRecord(), null));
    }
}
