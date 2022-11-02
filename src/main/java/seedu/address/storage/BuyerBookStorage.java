package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.BuyerBook;
import seedu.address.model.ReadOnlyBuyerBook;

/**
 * Represents a storage for {@link BuyerBook}.
 */
public interface BuyerBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBuyerBookFilePath();

    /**
     * Returns BuyerBook data as a {@link ReadOnlyBuyerBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBuyerBook> readBuyerBook() throws DataConversionException, IOException;

    /**
     * @see #getBuyerBookFilePath()
     */
    Optional<ReadOnlyBuyerBook> readBuyerBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBuyerBook} to the storage.
     * @param buyerBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBuyerBook(ReadOnlyBuyerBook buyerBook) throws IOException;

    /**
     * @see #saveBuyerBook(ReadOnlyBuyerBook)
     */
    void saveBuyerBook(ReadOnlyBuyerBook buyerBook, Path filePath) throws IOException;

}
