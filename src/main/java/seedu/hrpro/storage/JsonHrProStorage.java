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
import seedu.hrpro.model.ReadOnlyHrPro;

/**
 * A class to access HrPro data stored as a json file on the hard disk.
 */
public class JsonHrProStorage implements HrProStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHrProStorage.class);

    private Path filePath;

    public JsonHrProStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHrProFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHrPro> readHrPro() throws DataConversionException {
        return readHrPro(filePath);
    }

    /**
     * Similar to {@link #readHrPro()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHrPro> readHrPro(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHrPro> jsonHrPro = JsonUtil.readJsonFile(
                filePath, JsonSerializableHrPro.class);
        if (!jsonHrPro.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHrPro.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHrPro(ReadOnlyHrPro hrPro) throws IOException {
        saveHrPro(hrPro, filePath);
    }

    /**
     * Similar to {@link #saveHrPro(ReadOnlyHrPro)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHrPro(ReadOnlyHrPro hrPro, Path filePath) throws IOException {
        requireNonNull(hrPro);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHrPro(hrPro), filePath);
    }

}
