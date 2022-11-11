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
import seedu.address.model.ReadOnlyTruthTable;

/**
 * A class to access TruthTable data stored as a json file on the hard disk.
 */
public class JsonTruthTableStorage implements TruthTableStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTruthTableStorage.class);

    private Path filePath;

    public JsonTruthTableStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTruthTableFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTruthTable> readTruthTable() throws DataConversionException {
        return readTruthTable(filePath);
    }

    /**
     * Similar to {@link #readTruthTable()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTruthTable> readTruthTable(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTruthTable> jsonTruthTable = JsonUtil.readJsonFile(
                filePath, JsonSerializableTruthTable.class);
        if (!jsonTruthTable.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTruthTable.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTruthTable(ReadOnlyTruthTable truthTable) throws IOException {
        saveTruthTable(truthTable, filePath);
    }

    /**
     * Similar to {@link #saveTruthTable(ReadOnlyTruthTable)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTruthTable(ReadOnlyTruthTable truthTable, Path filePath) throws IOException {
        requireNonNull(truthTable);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTruthTable(truthTable), filePath);
    }

}
