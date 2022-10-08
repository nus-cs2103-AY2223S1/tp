package modtrekt.storage;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import modtrekt.commons.exceptions.IllegalValueException;
import modtrekt.commons.util.JsonUtil;
import modtrekt.model.TaskBook;
import modtrekt.testutil.TypicalTasks;

public class JsonSerializableTaskBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskBookTest");
    private static final Path TYPICAL_TASK_FILE = TEST_DATA_FOLDER.resolve("typicalTasksTaskBook.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTaskBook.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASK_FILE,
                JsonSerializableTaskBook.class).get();
        TaskBook taskBookFromFile = dataFromFile.toModelType();
        TaskBook typicalTaskBook = TypicalTasks.getTypicalTaskBook();
        assertEquals(taskBookFromFile, typicalTaskBook);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTaskBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
