package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskPanel;

/**
 * Represents a storage for {@link seedu.address.model.TaskPanel}.
 */
public interface TaskPanelStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskPanelFilePath();

    /**
     * Returns TaskPanel data as a {@link ReadOnlyTaskPanel}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskPanel> readTaskPanel() throws DataConversionException, IOException;

    /**
     * @see #getTaskPanelFilePath()
     */
    Optional<ReadOnlyTaskPanel> readTaskPanel(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaskPanel} to the storage.
     * @param taskPanel cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskPanel(ReadOnlyTaskPanel taskPanel) throws IOException;

    /**
     * @see #saveTaskPanel(ReadOnlyTaskPanel)
     */
    void saveTaskPanel(ReadOnlyTaskPanel taskPanel, Path filePath) throws IOException;
}
