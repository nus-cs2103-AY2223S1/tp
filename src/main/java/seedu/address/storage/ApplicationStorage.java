package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyApplicationBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface ApplicationStorage extends ApplicationBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getApplicationBookFilePath();

    @Override
    Optional<ReadOnlyApplicationBook> readApplicationBook() throws DataConversionException, IOException;

    @Override
    void saveApplicationBook(ReadOnlyApplicationBook addressBook) throws IOException;

}
