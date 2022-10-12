package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_FUEL;
import static seedu.address.testutil.TypicalTasks.TASK_GARLIC;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;

public class JsonTaskStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskList(null));
    }

    private java.util.Optional<ReadOnlyTaskList> readTaskList(String filePath) throws Exception {
        return new JsonTaskStorage(Paths.get(filePath)).readTaskList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTaskList("notJsonFormatTaskList.json"));
    }

    @Test
    public void readAndSaveTaskList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaskList.json");
        TaskList original = getTypicalTaskList();
        JsonTaskStorage jsonTaskStorage = new JsonTaskStorage(filePath);

        // Save in new file and read back
        jsonTaskStorage.saveTaskList(original, filePath);
        ReadOnlyTaskList readBack = jsonTaskStorage.readTaskList(filePath).get();
        assertEquals(original, new TaskList(readBack));

        // Modify data, overwrite existing file, and read back
        original.addTask(TASK_GARLIC);
        // Unique tasks are not enforced, hence only way is to fetch from last added index
        Index lastAddedTaskIndex = Index.fromZeroBased(original.getTaskList().size() - 1);
        original.deleteTask(lastAddedTaskIndex);
        jsonTaskStorage.saveTaskList(original, filePath);
        readBack = jsonTaskStorage.readTaskList(filePath).get();
        assertEquals(original, new TaskList(readBack));

        // Save and read without specifying file path
        original.addTask(TASK_FUEL);
        jsonTaskStorage.saveTaskList(original); // file path not specified
        readBack = jsonTaskStorage.readTaskList().get(); // file path not specified
        assertEquals(original, new TaskList(readBack));
    }

    @Test
    public void saveTaskList_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskList(null, "SomeFile.json"));
    }

    private void saveTaskList(ReadOnlyTaskList taskList, String filePath) {
        try {
            new JsonTaskStorage(Paths.get(filePath))
                    .saveTaskList(taskList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskList(new TaskList(), null));
    }
}
