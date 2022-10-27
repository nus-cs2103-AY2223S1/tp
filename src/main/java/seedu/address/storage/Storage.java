package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

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
     * Exports the given {@link ObservableList} as a JSON file to the storage at filePathString.
     * @param displayedList cannot be null.
     * @param filePathString cannot be null.
     * @throws IOException if there is any problem writing to the file.
     */
    void exportDisplayedList(ObservableList<Person> displayedList, String filePathString) throws IOException;
}
