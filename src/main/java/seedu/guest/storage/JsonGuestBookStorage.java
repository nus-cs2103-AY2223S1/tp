package seedu.guest.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.guest.commons.core.LogsCenter;
import seedu.guest.commons.exceptions.DataConversionException;
import seedu.guest.commons.exceptions.IllegalValueException;
import seedu.guest.commons.util.FileUtil;
import seedu.guest.commons.util.JsonUtil;
import seedu.guest.model.ReadOnlyGuestBook;

/**
 * A class to access GuestBook data stored as a json file on the hard disk.
 */
public class JsonGuestBookStorage implements GuestBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonGuestBookStorage.class);

    private Path filePath;

    public JsonGuestBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGuestBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGuestBook> readGuestBook() throws DataConversionException {
        return readGuestBook(filePath);
    }

    /**
     * Similar to {@link #readGuestBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGuestBook> readGuestBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGuestBook> jsonGuestBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableGuestBook.class);
        if (!jsonGuestBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGuestBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveGuestBook(ReadOnlyGuestBook guestBook) throws IOException {
        saveGuestBook(guestBook, filePath);
    }

    /**
     * Similar to {@link #saveGuestBook(ReadOnlyGuestBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGuestBook(ReadOnlyGuestBook guestBook, Path filePath) throws IOException {
        requireNonNull(guestBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGuestBook(guestBook), filePath);
    }

}
