package seedu.boba.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.boba.commons.exceptions.DataConversionException;
import seedu.boba.model.BobaBot;
import seedu.boba.model.ReadOnlyBobaBot;

/**
 * Represents a storage for {@link BobaBot}.
 */
public interface BobaBotStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBobaBotFilePath();

    /**
     * Returns BobaBot data as a {@link ReadOnlyBobaBot}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBobaBot> readBobaBot() throws DataConversionException, IOException;

    /**
     * @see #getBobaBotFilePath()
     */
    Optional<ReadOnlyBobaBot> readBobaBot(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBobaBot} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBobaBot(ReadOnlyBobaBot addressBook) throws IOException;

    /**
     * @see #saveBobaBot(ReadOnlyBobaBot)
     */
    void saveBobaBot(ReadOnlyBobaBot addressBook, Path filePath) throws IOException;

}
