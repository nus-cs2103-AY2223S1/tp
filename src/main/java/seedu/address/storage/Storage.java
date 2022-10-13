package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath(AddressBookCategories cat);

    @Override
    Optional<ReadOnlyAddressBook> readAllAddressBook()
            throws DataConversionException, IllegalValueException, IOException;

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook(AddressBookCategories cat)
            throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyAddressBook> readTutorAddressBook(Path filePath) throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyAddressBook> readStudentAddressBook(Path filePath) throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyAddressBook> readTuitionClassAddressBook(Path filePath)
            throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook, AddressBookCategories cat) throws IOException;

    @Override
    void saveAllAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
