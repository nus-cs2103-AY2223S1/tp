package seedu.studmap.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.studmap.commons.exceptions.DataConversionException;
import seedu.studmap.model.ReadOnlyAddressBook;
import seedu.studmap.model.ReadOnlyUserPrefs;
import seedu.studmap.model.UserPrefs;

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
