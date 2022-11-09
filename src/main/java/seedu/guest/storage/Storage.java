package seedu.guest.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.guest.commons.exceptions.DataConversionException;
import seedu.guest.model.ReadOnlyGuestBook;
import seedu.guest.model.ReadOnlyUserPrefs;
import seedu.guest.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends GuestBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getGuestBookFilePath();

    @Override
    Optional<ReadOnlyGuestBook> readGuestBook() throws DataConversionException, IOException;

    @Override
    void saveGuestBook(ReadOnlyGuestBook addressBook) throws IOException;

}
