package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ArchivedTaskList;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableArchivedTaskListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonSerializableArchivedTaskListTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksArchivedTaskList.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTasksArchivedTaskList.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskArchivedTaskList.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableArchivedTaskList dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableArchivedTaskList.class).get();
        ArchivedTaskList archivedTaskListFromFile = dataFromFile.toModelType();
        ArchivedTaskList typicalPersonsArchivedTaskList = TypicalPersons.getTypicalArchivedTaskList();
        assertEquals(archivedTaskListFromFile, typicalPersonsArchivedTaskList);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableArchivedTaskList dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableArchivedTaskList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableArchivedTaskList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableArchivedTaskList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableArchivedTaskList.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }
}
