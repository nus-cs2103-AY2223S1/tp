package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalTeammates;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_TEAMMATES_FILE = TEST_DATA_FOLDER.resolve("typicalTeammatesAddressBook.json");
    private static final Path INVALID_TEAMMATE_FILE = TEST_DATA_FOLDER.resolve("invalidTeammateAddressBook.json");
    private static final Path DUPLICATE_TEAMMATE_FILE = TEST_DATA_FOLDER.resolve("duplicateTeammateAddressBook.json");

    @Test
    public void toModelType_typicalTeammatesFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TEAMMATES_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalTeammatesAddressBook = TypicalTeammates.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalTeammatesAddressBook);
    }

    @Test
    public void toModelType_invalidTeammateFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_TEAMMATE_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTeammates_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TEAMMATE_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_TEAMMATE,
                dataFromFile::toModelType);
    }

}
