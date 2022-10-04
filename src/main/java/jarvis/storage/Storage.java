package jarvis.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import jarvis.commons.exceptions.DataConversionException;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.ReadOnlyUserPrefs;
import jarvis.model.UserPrefs;

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
    Optional<ReadOnlyStudentBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyStudentBook addressBook) throws IOException;

}
