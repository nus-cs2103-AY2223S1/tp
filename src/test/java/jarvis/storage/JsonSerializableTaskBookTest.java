package jarvis.storage;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import jarvis.commons.util.JsonUtil;
import jarvis.model.TaskBook;
import jarvis.testutil.TypicalTasks;

public class JsonSerializableTaskBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskBookTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksTaskBook.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTaskBook.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableTaskBook.class).get();
        TaskBook TaskBookFromFile = dataFromFile.toModelType();
        TaskBook typicalPersonsTaskBook = TypicalTasks.getTypicalTaskBook();
        assertEquals(TaskBookFromFile, typicalPersonsTaskBook);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalArgumentException() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTaskBook.class).get();
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }
}
