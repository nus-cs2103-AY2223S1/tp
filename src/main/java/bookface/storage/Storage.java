package bookface.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import bookface.commons.exceptions.DataConversionException;
import bookface.model.ReadOnlyBookFace;
import bookface.model.ReadOnlyUserPrefs;
import bookface.model.UserPrefs;

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
    Optional<ReadOnlyBookFace> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyBookFace addressBook) throws IOException;

}
