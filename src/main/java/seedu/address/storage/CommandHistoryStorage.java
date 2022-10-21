package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface CommandHistoryStorage {

    /**
     * Returns the file path of the CommandHistory data file.
     */
    Path getUserCommandHistoryPath();

    /**
     * Returns UserPrefs data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<List<String>> readCommandHistory() throws IOException;

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyUserPrefs} to the storage.
     * @param commandHistoryList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCommandHistory(List commandHistoryList) throws IOException;

}
