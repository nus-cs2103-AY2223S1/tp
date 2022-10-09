package bookface.storage;

import static bookface.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import bookface.commons.exceptions.IllegalValueException;
import bookface.commons.util.JsonUtil;
import bookface.model.BookFace;
import bookface.testutil.TypicalBooks;
import bookface.testutil.TypicalPersons;

public class JsonSerializableBookFaceTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");

    private static final Path TYPICAL_BOOKS_FILE = TEST_DATA_FOLDER.resolve("typicalBooksAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");

    private static final Path INVALID_BOOK_FILE = TEST_DATA_FOLDER.resolve("invalidBookAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    private static final Path DUPLICATE_BOOK_FILE = TEST_DATA_FOLDER.resolve("duplicateBookAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();
        BookFace bookFaceFromFile = dataFromFile.toModelType();
        BookFace typicalPersonsBookFace = TypicalPersons.getTypicalBookFaceData();
        assertEquals(bookFaceFromFile, typicalPersonsBookFace);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalBooksFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_BOOKS_FILE,
                JsonSerializableAddressBook.class).get();
        BookFace bookFaceFromFile = dataFromFile.toModelType();
        BookFace typicalBooksBookFace = TypicalBooks.getTypicalBookFaceData();
        assertEquals(bookFaceFromFile, typicalBooksBookFace);
    }

    @Test
    public void toModelType_invalidBookFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_BOOK_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateBooks_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_BOOK_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_BOOK,
                dataFromFile::toModelType);
    }

}
