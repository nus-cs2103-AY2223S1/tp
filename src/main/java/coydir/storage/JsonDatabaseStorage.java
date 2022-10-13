package coydir.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import coydir.commons.core.LogsCenter;
import coydir.commons.exceptions.DataConversionException;
import coydir.commons.exceptions.IllegalValueException;
import coydir.commons.util.FileUtil;
import coydir.commons.util.JsonUtil;
import coydir.model.ReadOnlyDatabase;
import coydir.model.person.EmployeeId;

/**
 * A class to access database data stored as a json file on the hard disk.
 */
public class JsonDatabaseStorage implements DatabaseStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDatabaseStorage.class);

    private Path filePath;

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

        Optional<JsonSerializableDatabase> jsonDatabase = JsonUtil.readJsonFile(
                filePath, JsonSerializableDatabase.class);
        if (!jsonDatabase.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDatabase.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
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
        JsonUtil.saveJsonFile(new JsonSerializableDatabase(database, EmployeeId.getCount()), filePath);
    }

}
