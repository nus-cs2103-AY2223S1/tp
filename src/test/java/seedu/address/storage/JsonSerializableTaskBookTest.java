package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TaskBook;
import seedu.address.testutil.TypicalAssignments;
import seedu.address.testutil.TypicalDeadlines;
import seedu.address.testutil.TypicalToDos;

public class JsonSerializableTaskBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableTaskBookTest");
    private static final Path TYPICAL_TODOS_FILE = TEST_DATA_FOLDER.resolve("typicalTaskBookWithToDos.json");
    private static final Path TYPICAL_DEADLINES_FILE = TEST_DATA_FOLDER.resolve("typicalTaskBookWithDeadlines.json");
    private static final Path TYPICAL_ASSIGNMENTS_FILE = TEST_DATA_FOLDER
            .resolve("typicalTaskBookWithAssignments.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskBook.json");

    @Test
    public void toModelType_typicalToDosFile_success() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TODOS_FILE,
                JsonSerializableTaskBook.class).get();
        TaskBook taskBookFromFile = dataFromFile.toModelType();
        TaskBook typicalTaskBook = TypicalToDos.getTypicalTaskBookWithToDos();
        assertEquals(taskBookFromFile, typicalTaskBook);
    }

    @Test
    public void toModelType_typicalDeadlinesFile_success() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_DEADLINES_FILE,
                JsonSerializableTaskBook.class).get();
        TaskBook taskBookFromFile = dataFromFile.toModelType();
        TaskBook typicalTaskBook = TypicalDeadlines.getTypicalTaskBookWithDeadlines();
        assertEquals(taskBookFromFile, typicalTaskBook);
    }

    @Test
    public void toModelType_typicalAssignmentsFile_success() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_ASSIGNMENTS_FILE,
                JsonSerializableTaskBook.class).get();
        TaskBook taskBookFromFile = dataFromFile.toModelType();
        TaskBook typicalTaskBook = TypicalAssignments.getTypicalTaskBookWithAssignments();
        assertEquals(taskBookFromFile, typicalTaskBook);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTaskBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
