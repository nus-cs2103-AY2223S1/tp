package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    /**
     * Replaces addressBookStorage data with the data in {@code addressBookStorage}.
     */
    void setAddressBookStorage(AddressBookStorage addressBookStorage);

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

    /**
     * Exports the given {@link ReadOnlyAddressBook} as a JSON file to the storage at filePathString.
     * @param displayedListAddressBook cannot be null.
     * @param filePathString cannot be null.
     * @throws IOException if there is any problem writing to the file.
     */
    void exportDisplayedListAddressBook(ReadOnlyAddressBook displayedListAddressBook, String filePathString)
            throws IOException;
}
