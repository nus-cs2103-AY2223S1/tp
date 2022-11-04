package seedu.hrpro.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.hrpro.commons.core.LogsCenter;
import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.commons.exceptions.IllegalValueException;
import seedu.hrpro.commons.util.FileUtil;
import seedu.hrpro.commons.util.JsonUtil;
import seedu.hrpro.model.ReadOnlyHRPro;

/**
 * A class to access HRPro data stored as a json file on the hard disk.
 */
public class JsonHRProStorage implements HRProStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHRProStorage.class);

    private Path filePath;

    public JsonHRProStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHRProFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHRPro> readHRPro() throws DataConversionException {
        return readHRPro(filePath);
    }

    /**
     * Similar to {@link #readHRPro()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHRPro> readHRPro(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHRPro> jsonHRPro = JsonUtil.readJsonFile(
                filePath, JsonSerializableHRPro.class);
        if (!jsonHRPro.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHRPro.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHRPro(ReadOnlyHRPro hrPro) throws IOException {
        saveHRPro(hrPro, filePath);
    }

    /**
     * Similar to {@link #saveHRPro(ReadOnlyHRPro)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHRPro(ReadOnlyHRPro hrPro, Path filePath) throws IOException {
        requireNonNull(hrPro);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHRPro(hrPro), filePath);
    }

}
