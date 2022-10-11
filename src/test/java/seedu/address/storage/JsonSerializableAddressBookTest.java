package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalInternships;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_INTERNSHIPS_FILE = TEST_DATA_FOLDER.resolve("typicalInternshipsAddressBook.json");
    private static final Path INVALID_INTERNSHIP_FILE = TEST_DATA_FOLDER.resolve("invalidInternshipAddressBook.json");
    private static final Path DUPLICATE_INTERNSHIP_FILE =
            TEST_DATA_FOLDER.resolve("duplicateInternshipAddressBook.json");

    @Test
    public void toModelType_typicalInternshipsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERNSHIPS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalInternshipsAddressBook = TypicalInternships.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalInternshipsAddressBook);
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void toModelType_invalidInternshipFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_INTERNSHIP_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
    */

    @Test
    public void toModelType_duplicateInternships_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERNSHIP_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_INTERNSHIP,
                dataFromFile::toModelType);
    }

}
