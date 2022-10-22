package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.CommandHistory;
import seedu.address.model.ReadOnlyCommandHistory;

public interface CommandHistoryStorage {

    /**
     * Returns the file path of the CommandHistory data file.
     */
    Path getCommandHistoryFilePath();

    /**
     * Returns UserPrefs data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws IOException if there was any problem when reading from the storage.
     */
    ReadOnlyCommandHistory readCommandHistory();

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyUserPrefs} to the storage.
     * @param commandHistoryList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCommandHistory(ReadOnlyCommandHistory commandHistory);

}
