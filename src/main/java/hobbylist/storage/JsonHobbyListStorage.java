package hobbylist.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import hobbylist.commons.core.LogsCenter;
import hobbylist.commons.exceptions.DataConversionException;
import hobbylist.commons.exceptions.IllegalValueException;
import hobbylist.commons.util.FileUtil;
import hobbylist.commons.util.JsonUtil;
import hobbylist.model.ReadOnlyHobbyList;

/**
 * A class to access HobbyList data stored as a json file on the hard disk.
 */
public class JsonHobbyListStorage implements HobbyListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHobbyListStorage.class);

    private Path filePath;

    public JsonHobbyListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHobbyListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHobbyList> readHobbyList() throws DataConversionException {
        return readHobbyList(filePath);
    }

    /**
     * Similar to {@link #readHobbyList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHobbyList> readHobbyList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHobbyList> jsonHobbyList = JsonUtil.readJsonFile(
                filePath, JsonSerializableHobbyList.class);
        if (!jsonHobbyList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHobbyList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHobbyList(ReadOnlyHobbyList hobbyList) throws IOException {
        saveHobbyList(hobbyList, filePath);
    }

    /**
     * Similar to {@link #saveHobbyList(ReadOnlyHobbyList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHobbyList(ReadOnlyHobbyList hobbyList, Path filePath) throws IOException {
        requireNonNull(hobbyList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHobbyList(hobbyList), filePath);
    }

}
