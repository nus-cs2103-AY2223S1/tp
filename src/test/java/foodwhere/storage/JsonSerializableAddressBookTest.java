package foodwhere.storage;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.commons.util.JsonUtil;
import foodwhere.model.AddressBook;
import foodwhere.testutil.TypicalStalls;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_STALLS_FILE = TEST_DATA_FOLDER.resolve("typicalStallsAddressBook.json");
    private static final Path INVALID_STALL_FILE = TEST_DATA_FOLDER.resolve("invalidStallAddressBook.json");
    private static final Path DUPLICATE_STALL_FILE = TEST_DATA_FOLDER.resolve("duplicateStallAddressBook.json");

    public void toModelType_typicalStallsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STALLS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalStallsAddressBook = TypicalStalls.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalStallsAddressBook);
    }

    @Test
    public void toModelType_invalidStallFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_STALL_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStalls_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STALL_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_STALL,
                dataFromFile::toModelType);
    }

}
