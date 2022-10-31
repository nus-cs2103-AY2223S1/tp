package seedu.watson.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.watson.commons.core.LogsCenter;
import seedu.watson.commons.exceptions.DataConversionException;
import seedu.watson.commons.util.FileUtil;
import seedu.watson.commons.util.JsonUtil;
import seedu.watson.model.ReadOnlyDatabase;
import seedu.watson.model.util.SampleDataUtil;

/**
 * A class to access Database data stored as a json file on the hard disk.
 */
public class JsonDatabaseStorage implements DatabaseStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDatabaseStorage.class);

    private final Path filePath;

    public JsonDatabaseStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDatabaseFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDatabase> readDatabase() throws DataConversionException {
        return readDatabase(filePath);
    }

    /**
     * Similar to {@link #readDatabase()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDatabase> readDatabase(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDatabase> jsonAddressBook = JsonUtil.readJsonFile(
            filePath, JsonSerializableDatabase.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (Exception ex) {
            logger.info("There was an issue parsing the JSON file. Using sample data instead.");
            return Optional.of(SampleDataUtil.getSampleAddressBook());
        }
    }

    @Override
    public void saveDatabase(ReadOnlyDatabase database) throws IOException {
        saveDatabase(database, filePath);
    }

    /**
     * Similar to {@link #saveDatabase(ReadOnlyDatabase)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDatabase(ReadOnlyDatabase database, Path filePath) throws IOException {
        requireNonNull(database);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDatabase(database), filePath);
    }

}
