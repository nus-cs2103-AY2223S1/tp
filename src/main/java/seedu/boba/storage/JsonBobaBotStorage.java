package seedu.boba.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.boba.commons.core.LogsCenter;
import seedu.boba.commons.exceptions.DataConversionException;
import seedu.boba.commons.exceptions.IllegalValueException;
import seedu.boba.commons.util.FileUtil;
import seedu.boba.commons.util.JsonUtil;
import seedu.boba.model.ReadOnlyBobaBot;

/**
 * A class to access BobaBot data stored as a json file on the hard disk.
 */
public class JsonBobaBotStorage implements BobaBotStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBobaBotStorage.class);

    private Path filePath;

    public JsonBobaBotStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBobaBotFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBobaBot> readBobaBot() throws DataConversionException {
        return readBobaBot(filePath);
    }

    /**
     * Similar to {@link #readBobaBot()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBobaBot> readBobaBot(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBobaBot> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableBobaBot.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBobaBot(ReadOnlyBobaBot addressBook) throws IOException {
        saveBobaBot(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveBobaBot(ReadOnlyBobaBot)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBobaBot(ReadOnlyBobaBot addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBobaBot(addressBook), filePath);
    }

}
