package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.address.model.CommandHistory;
import seedu.address.model.ReadOnlyCommandHistory;

public class TextCommandHistoryStorageTest {

    @TempDir
    public Path testFolder;
    @Test
    public void readCommandHistory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCommandHistory(null));
    }

    private ReadOnlyCommandHistory readCommandHistory(String filePath) throws Exception {
        return new TextCommandHistoryStorage(Paths.get(filePath)).readCommandHistory();
    }

    @Test
    public void read_missingFile() throws Exception {
       assertEquals(readCommandHistory("NonExistentFile.txt"), new CommandHistory());
    }
    @Test
    public void readAndSaveCommandHistory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCommandHistory.txt");
        CommandHistory original = new CommandHistory();
        List<String> commandHistoryList = new ArrayList<>();
        commandHistoryList.add("list");
        commandHistoryList.add("clear");
        original.setCommandHistoryList(commandHistoryList);

        TextCommandHistoryStorage textCommandHistoryStorage = new TextCommandHistoryStorage(filePath);

        // Save in new file and read back
        textCommandHistoryStorage.saveCommandHistory(original, filePath);
        ReadOnlyCommandHistory readBack = textCommandHistoryStorage.readCommandHistory(filePath);
        assertEquals(original, new CommandHistory(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addToCommandHistory("aa l/Starbucks");
        original.addToCommandHistory("find benny");
        textCommandHistoryStorage.saveCommandHistory(original, filePath);
        readBack = textCommandHistoryStorage.readCommandHistory(filePath);
        assertEquals(original, new CommandHistory(readBack));

        // Save and read without specifying file path
        original.addToCommandHistory("List");
        textCommandHistoryStorage.saveCommandHistory(original); // file path not specified
        readBack = textCommandHistoryStorage.readCommandHistory(); // file path not specified
        assertEquals(original, new CommandHistory(readBack));

    }
    @Test
    public void saveCommandHistory_nullCommandHistory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCommandHistory(null, "SomeFile.txt"));
    }

    private void saveCommandHistory(ReadOnlyCommandHistory commandHistory, String filePath) {
        try {
            new TextCommandHistoryStorage(Paths.get(filePath))
                    .saveCommandHistory(commandHistory, Paths.get(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCommandHistory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCommandHistory(new CommandHistory(), null));
    }
}
