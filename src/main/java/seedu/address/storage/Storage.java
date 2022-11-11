package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBuyerBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends BuyerBookStorage, UserPrefsStorage, PropertyBookStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getBuyerBookFilePath();

    @Override
    Optional<ReadOnlyBuyerBook> readBuyerBook() throws DataConversionException, IOException;

    @Override
    void saveBuyerBook(ReadOnlyBuyerBook buyerBook) throws IOException;

}
