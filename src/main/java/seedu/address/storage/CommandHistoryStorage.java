package seedu.address.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import seedu.address.model.ReadOnlyCommandHistory;

/**
 * Represents a storage for {@link seedu.address.model.CommandHistory}.
 */
public interface CommandHistoryStorage {

    /**
     * Returns CommandHistory data as a {@link ReadOnlyCommandHistory}.
     * Returns ReadOnlyCommandHistory with empty command history list if storage file is not found.
     */
    ReadOnlyCommandHistory readCommandHistory(Path filePath) throws FileNotFoundException;

    ReadOnlyCommandHistory readCommandHistory() throws FileNotFoundException;
    /**
     * Saves the given {@link seedu.address.model.ReadOnlyCommandHistory} to the storage.
     * @param commandHistory cannot be null.
     */
    void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException;

    void saveCommandHistory(ReadOnlyCommandHistory commandHistory, Path filePath) throws IOException;
    Path getCommandHistoryFilePath();
}
