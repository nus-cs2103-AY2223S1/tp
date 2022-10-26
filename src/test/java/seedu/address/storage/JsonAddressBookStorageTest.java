package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.STUDENT1;
import static seedu.address.testutil.TypicalStudents.STUDENT3;
import static seedu.address.testutil.TypicalStudents.STUDENT4;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTutorAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTutorAddressBook(null));
    }

    @Test
    public void readStudentAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readStudentAddressBook(null));
    }

    @Test
    public void readTuitionClassAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTuitionClassAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAddressBook> readTutorAddressBook(String filePath) throws Exception {
        return new JsonAddressBookStorage(Paths.get(filePath), Paths.get("someStudent"),
                Paths.get("someTuitionClass")).readTutorAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private java.util.Optional<ReadOnlyAddressBook> readStudentAddressBook(String filePath) throws Exception {
        return new JsonAddressBookStorage(Paths.get("someTutor"), Paths.get(filePath),
                Paths.get("someTuitionClass")).readStudentAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private java.util.Optional<ReadOnlyAddressBook> readTuitionClassAddressBook(String filePath) throws Exception {
        return new JsonAddressBookStorage(Paths.get("someTutor"), Paths.get("someStudent"),
                Paths.get(filePath)).readTuitionClassAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readTutor_missingFile_emptyResult() throws Exception {
        assertFalse(readTutorAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void readStudent_missingFile_emptyResult() throws Exception {
        assertFalse(readStudentAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void readTuitionClass_missingFile_emptyResult() throws Exception {
        assertFalse(readTuitionClassAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void readTutor_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTutorAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readStudent_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readStudentAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readTuitionClass_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, ()
                -> readTuitionClassAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readTutorAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTutorAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readStudentAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readStudentAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readTuitionClassAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readTuitionClassAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readTutorAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readTutorAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readStudentAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readStudentAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readTuitionClassAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readTuitionClassAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path tutorFilePath = testFolder.resolve("TempTutorAddressBook.json");
        Path studentFilePath = testFolder.resolve("TempStudentAddressBook.json");
        Path tuitionClassFilePath = testFolder.resolve("TempTuitionClassAddressBook.json");
        AddressBook original = getTypicalStudentsAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(tutorFilePath, studentFilePath,
                tuitionClassFilePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAllAddressBook(original);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readAllAddressBook().get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(STUDENT3);
        original.removePerson(STUDENT1);
        jsonAddressBookStorage.saveAllAddressBook(original);
        readBack = jsonAddressBookStorage.readAllAddressBook().get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addPerson(STUDENT4);
        jsonAddressBookStorage.saveAllAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readAllAddressBook().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));

    }

    @Test
    public void saveTutorAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json",
                AddressBookStorage.AddressBookCategories.TUTORS));
    }

    @Test
    public void saveStudentAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json",
                AddressBookStorage.AddressBookCategories.STUDENTS));
    }

    @Test
    public void saveTuitionClassAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json",
                AddressBookStorage.AddressBookCategories.TUITIONCLASSES));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath,
                                 AddressBookStorage.AddressBookCategories cat) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath), Paths.get(filePath), Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath), cat);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTutorAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null,
                AddressBookStorage.AddressBookCategories.TUTORS));
    }

    @Test
    public void saveStudentAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null,
                AddressBookStorage.AddressBookCategories.STUDENTS));
    }

    @Test
    public void saveTuitionClassAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null,
                AddressBookStorage.AddressBookCategories.TUITIONCLASSES));
    }
}
