package swift.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static swift.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import swift.commons.exceptions.IllegalValueException;
import swift.commons.util.JsonUtil;
import swift.model.AddressBook;
import swift.testutil.TypicalBridges;
import swift.testutil.TypicalPersons;
import swift.testutil.TypicalTasks;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksAddressBook.json");
    private static final Path INVALID_TASKS_FILE = TEST_DATA_FOLDER.resolve("invalidTasksAddressBook.json");
    private static final Path DUPLICATE_TASKS_FILE = TEST_DATA_FOLDER.resolve("duplicateTasksAddressBook.json");

    private static final Path TYPICAL_BRIDGES_FILE = TEST_DATA_FOLDER.resolve("typicalBridgesAddressBook.json");
    private static final Path INVALID_BRIDGES_FILE = TEST_DATA_FOLDER.resolve("invalidBridgesAddressBook.json");
    private static final Path DUPLICATE_BRIDGES_FILE = TEST_DATA_FOLDER.resolve("duplicateBridgesAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
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
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalTasksAddressBook = TypicalTasks.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalTasksAddressBook);
    }

    @Test
    public void toModelType_invalidTasksFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_TASKS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASKS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalBridgesFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_BRIDGES_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalBridgesAddressBook = TypicalBridges.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalBridgesAddressBook);
    }

    @Test
    public void toModelType_invalidBridgesFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_BRIDGES_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateBridges_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_BRIDGES_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_BRIDGE,
                dataFromFile::toModelType);
    }
}
