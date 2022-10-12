package jeryl.fyp.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import jeryl.fyp.commons.exceptions.DataConversionException;
import jeryl.fyp.model.ReadOnlyAddressBook;
import jeryl.fyp.model.ReadOnlyUserPrefs;
import jeryl.fyp.model.UserPrefs;

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
