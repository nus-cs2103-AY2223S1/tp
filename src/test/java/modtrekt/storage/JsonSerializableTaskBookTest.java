package modtrekt.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSerializableTaskBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    //    @Test
    //    public void toModelType_typicalPersonsFile_success() throws Exception {
    //        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
    //                JsonSerializableTaskBook.class).get();
    //        TaskBook addressBookFromFile = dataFromFile.toModelType();
    //        TaskBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
    //        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    //   }

    //    @Test
    //    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
    //        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
    //                JsonSerializableTaskBook.class).get();
    //        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    //   }

}
