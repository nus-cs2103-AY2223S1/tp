package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_FOUR;
import static seedu.address.testutil.TypicalTasks.TASK_ONE;
import static seedu.address.testutil.TypicalTasks.TASK_THREE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskPanel;
import seedu.address.model.TaskPanel;

public class JsonTaskPanelStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskPanelStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskPanel_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskPanel(null));
    }

    private java.util.Optional<ReadOnlyTaskPanel> readTaskPanel(String filePath) throws Exception {
        return new JsonTaskPanelStorage(Paths.get(filePath)).readTaskPanel(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskPanel("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTaskPanel("notJsonFormatTaskPanel.json"));
    }

    @Test
    public void readTaskPanel_invalidTeammateTaskPanel_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskPanel("invalidTaskTaskPanel.json"));
    }

    @Test
    public void readTaskPanel_invalidAndValidTeammateTaskPanel_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskPanel("invalidAndValidTaskTaskPanel.json"));
    }

    @Test
    public void readAndSaveTaskPanel_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaskPanel.json");
        TaskPanel original = getTypicalTaskPanel();
        JsonTaskPanelStorage jsonTaskPanelStorage = new JsonTaskPanelStorage(filePath);

        // Save in new file and read back
        jsonTaskPanelStorage.saveTaskPanel(original, filePath);
        ReadOnlyTaskPanel readBack = jsonTaskPanelStorage.readTaskPanel(filePath).get();
        assertEquals(original, new TaskPanel(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(TASK_THREE);
        original.removeTask(TASK_ONE);
        jsonTaskPanelStorage.saveTaskPanel(original, filePath);
        readBack = jsonTaskPanelStorage.readTaskPanel(filePath).get();
        assertEquals(original, new TaskPanel(readBack));

        // Save and read without specifying file path
        original.addTask(TASK_FOUR);
        jsonTaskPanelStorage.saveTaskPanel(original); // file path not specified
        readBack = jsonTaskPanelStorage.readTaskPanel().get(); // file path not specified
        assertEquals(original, new TaskPanel(readBack));

    }

    @Test
    public void saveTaskPanel_nullTaskPanel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskPanel(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskPanel} at the specified {@code filePath}.
     */
    private void saveTaskPanel(ReadOnlyTaskPanel taskPanel, String filePath) {
        try {
            new JsonTaskPanelStorage(Paths.get(filePath))
                .saveTaskPanel(taskPanel, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskPanel_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskPanel(new TaskPanel(), null));
    }
}
