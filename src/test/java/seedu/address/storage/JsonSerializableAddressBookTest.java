package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalProfiles;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PROFILES_FILE = TEST_DATA_FOLDER.resolve("typicalProfilesAddressBook.json");
    private static final Path INVALID_PROFILE_FILE = TEST_DATA_FOLDER.resolve("invalidProfileAddressBook.json");
    private static final Path DUPLICATE_PROFILE_FILE = TEST_DATA_FOLDER.resolve("duplicateProfileAddressBook.json");

    @Test
    public void toModelType_typicalProfilesFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROFILES_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalProfilesAddressBook = TypicalProfiles.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalProfilesAddressBook);
    }

    @Test
    public void toModelType_invalidProfileFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PROFILE_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProfiles_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROFILE_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PROFILE,
                dataFromFile::toModelType);
    }

}
