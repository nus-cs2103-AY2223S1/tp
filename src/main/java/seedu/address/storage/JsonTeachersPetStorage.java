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
import seedu.address.model.ReadOnlyTeachersPet;

/**
 * A class to access TeachersPet data stored as a json file on the hard disk.
 */
public class JsonTeachersPetStorage implements TeachersPetStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTeachersPetStorage.class);

    private Path filePath;

    public JsonTeachersPetStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTeachersPetFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTeachersPet> readTeachersPet() throws DataConversionException {
        return readTeachersPet(filePath);
    }

    /**
     * Similar to {@link #readTeachersPet()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTeachersPet> readTeachersPet(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTeachersPet> jsonTeachersPet = JsonUtil.readJsonFile(
                filePath, JsonSerializableTeachersPet.class);
        if (!jsonTeachersPet.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTeachersPet.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTeachersPet(ReadOnlyTeachersPet teachersPet) throws IOException {
        saveTeachersPet(teachersPet, filePath);
    }

    /**
     * Similar to {@link #saveTeachersPet(ReadOnlyTeachersPet)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTeachersPet(ReadOnlyTeachersPet teachersPet, Path filePath) throws IOException {
        requireNonNull(teachersPet);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTeachersPet(teachersPet), filePath);
    }

}
