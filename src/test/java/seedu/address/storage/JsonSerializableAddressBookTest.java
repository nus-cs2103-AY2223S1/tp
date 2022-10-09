package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalItemsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidItemAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateItemAddressBook.json");

    @Test
    public void dummy_test() {
        assert true;
    }
    // TODO: Validation needs to be done first before invalid files are made
    //@Test
    //public void toModelType_typicalItemsFile_success() throws Exception {
    //    JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
    //        JsonSerializableAddressBook.class).get();
    //    AddressBook addressBookFromFile = dataFromFile.toModelType();
    //    AddressBook typicalItemsAddressBook = TypicalItems.getTypicalAddressBook();
    //    assertEquals(addressBookFromFile, typicalItemsAddressBook);
    //}
    //
    //@Test
    //public void toModelType_invalidItemFile_throwsIllegalValueException() throws Exception {
    //    JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
    //        JsonSerializableAddressBook.class).get();
    //    assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    //}
    //
    //@Test
    //public void toModelType_duplicateItems_throwsIllegalValueException() throws Exception {
    //    JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
    //        JsonSerializableAddressBook.class).get();
    //    assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_ITEMS,
    //        dataFromFile::toModelType);
    //}

}
