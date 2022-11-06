package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ArchivedTaskBook;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableArchivedTaskBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonSerializableArchivedTaskBookTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksArchivedTaskBook.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTasksArchivedTaskBook.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskArchivedTaskBook.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableArchivedTaskBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableArchivedTaskBook.class).get();
        ArchivedTaskBook archivedTaskBookFromFile = dataFromFile.toModelType();
        ArchivedTaskBook typicalPersonsArchivedTaskBook = TypicalPersons.getTypicalArchivedTaskBook();
        assertEquals(archivedTaskBookFromFile, typicalPersonsArchivedTaskBook);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableArchivedTaskBook dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableArchivedTaskBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableArchivedTaskBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableArchivedTaskBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableArchivedTaskBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }
}
