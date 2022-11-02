package seedu.masslinkers.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.masslinkers.commons.core.LogsCenter;
import seedu.masslinkers.commons.exceptions.DataConversionException;
import seedu.masslinkers.commons.exceptions.IllegalValueException;
import seedu.masslinkers.commons.util.FileUtil;
import seedu.masslinkers.commons.util.JsonUtil;
import seedu.masslinkers.model.ReadOnlyMassLinkers;
//@@author
/**
 * A class to access MassLinkers data stored as a json file on the hard disk.
 */
public class JsonMassLinkersStorage implements MassLinkersStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMassLinkersStorage.class);

    private Path filePath;

    public JsonMassLinkersStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMassLinkersFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMassLinkers> readMassLinkers() throws DataConversionException {
        return readMassLinkers(filePath);
    }

    /**
     * Similar to {@link #readMassLinkers()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMassLinkers> readMassLinkers(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMassLinkers> jsonMassLinkers = JsonUtil.readJsonFile(
                filePath, JsonSerializableMassLinkers.class);
        if (!jsonMassLinkers.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMassLinkers.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMassLinkers(ReadOnlyMassLinkers massLinkers) throws IOException {
        saveMassLinkers(massLinkers, filePath);
    }

    /**
     * Similar to {@link #saveMassLinkers(ReadOnlyMassLinkers)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMassLinkers(ReadOnlyMassLinkers massLinkers, Path filePath) throws IOException {
        requireNonNull(massLinkers);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMassLinkers(massLinkers), filePath);
    }

}
