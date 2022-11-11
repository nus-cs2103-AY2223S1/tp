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
import seedu.address.model.ReadOnlyFindMyIntern;

/**
 * A class to access FindMyIntern data stored as a json file on the hard disk.
 */
public class JsonFindMyInternStorage implements FindMyInternStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFindMyInternStorage.class);

    private Path filePath;

    public JsonFindMyInternStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFindMyInternFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFindMyIntern> readFindMyIntern() throws DataConversionException {
        return readFindMyIntern(filePath);
    }

    /**
     * Similar to {@link #readFindMyIntern()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFindMyIntern> readFindMyIntern(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFindMyIntern> jsonFindMyIntern = JsonUtil.readJsonFile(
                filePath, JsonSerializableFindMyIntern.class);
        if (!jsonFindMyIntern.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFindMyIntern.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFindMyIntern(ReadOnlyFindMyIntern findMyIntern) throws IOException {
        saveFindMyIntern(findMyIntern, filePath);
    }

    /**
     * Similar to {@link #saveFindMyIntern(ReadOnlyFindMyIntern)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFindMyIntern(ReadOnlyFindMyIntern findMyIntern, Path filePath) throws IOException {
        requireNonNull(findMyIntern);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFindMyIntern(findMyIntern), filePath);
    }

}
