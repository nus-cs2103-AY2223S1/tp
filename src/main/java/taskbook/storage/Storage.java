package taskbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import taskbook.commons.exceptions.DataConversionException;
import taskbook.model.ReadOnlyAddressBook;
import taskbook.model.ReadOnlyUserPrefs;
import taskbook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
