package seedu.waddle.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.waddle.commons.exceptions.DataConversionException;
import seedu.waddle.model.ReadOnlyAddressBook;
import seedu.waddle.model.ReadOnlyUserPrefs;
import seedu.waddle.model.UserPrefs;

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
