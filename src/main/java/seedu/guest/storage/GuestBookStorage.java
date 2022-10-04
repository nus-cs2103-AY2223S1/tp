package seedu.guest.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.guest.commons.exceptions.DataConversionException;
import seedu.guest.model.GuestBook;
import seedu.guest.model.ReadOnlyGuestBook;

/**
 * Represents a storage for {@link GuestBook}.
 */
public interface GuestBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getGuestBookFilePath();

    /**
     * Returns GuestBook data as a {@link ReadOnlyGuestBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyGuestBook> readGuestBook() throws DataConversionException, IOException;

    /**
     * @see #getGuestBookFilePath()
     */
    Optional<ReadOnlyGuestBook> readGuestBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyGuestBook} to the storage.
     * @param guestBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGuestBook(ReadOnlyGuestBook guestBook) throws IOException;

    /**
     * @see #saveGuestBook(ReadOnlyGuestBook)
     */
    void saveGuestBook(ReadOnlyGuestBook guestBook, Path filePath) throws IOException;

}
