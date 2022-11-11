package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTaskPanel;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonTaskPanelStorage implements TaskPanelStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskPanelStorage.class);

    private Path filePath;

    public JsonTaskPanelStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskPanelFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskPanel> readTaskPanel() throws DataConversionException {
        return readTaskPanel(filePath);
    }

    /**
     * Similar to {@link #readTaskPanel()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaskPanel> readTaskPanel(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskPanel> jsonTaskPanel = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskPanel.class);
        if (jsonTaskPanel.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskPanel.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskPanel(ReadOnlyTaskPanel taskPanel) throws IOException {
        saveTaskPanel(taskPanel, filePath);
    }

    /**
     * Similar to {@link #saveTaskPanel(ReadOnlyTaskPanel)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskPanel(ReadOnlyTaskPanel taskPanel, Path filePath) throws IOException {
        requireNonNull(taskPanel);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskPanel(taskPanel), filePath);
    }

}
