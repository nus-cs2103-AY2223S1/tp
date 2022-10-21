package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.CommandHistory;

/**
 * A class to access CommandHistory data stored as a json file on the hard disk.
 */
public class JsonCommandHistoryStorage {

    private Path filePath;

    public JsonCommandHistoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCommandHistoryFilePath() {
        return filePath;
    }

    /**
     * Reads the json file for the command history.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<CommandHistory> readCommandHistory(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<CommandHistory> commandHistory = JsonUtil.readJsonFile(filePath, CommandHistory.class);
        if (commandHistory.isEmpty()) {
            return Optional.empty();
        }
        return commandHistory;
    }

    /**
     * Saves the command history into a json file.
     *
     * @param commandHistory The commandHistory of idENTify.
     * @param filePath       location of the data. Cannot be null.
     */
    public void saveCommandHistory(CommandHistory commandHistory, Path filePath) throws IOException {
        requireNonNull(commandHistory);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(commandHistory, filePath);
    }
}
