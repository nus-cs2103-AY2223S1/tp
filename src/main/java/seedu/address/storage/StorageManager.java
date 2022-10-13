package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBook, UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBook;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath(AddressBookCategories cat) {
        return addressBookStorage.getAddressBookFilePath(cat);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAllAddressBook()
            throws DataConversionException, IllegalValueException, IOException {
        return addressBookStorage.readAllAddressBook();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(AddressBookCategories cat)
            throws DataConversionException, IOException {
        switch (cat) {
        case TUTORS:
            return readTutorAddressBook(addressBookStorage.getAddressBookFilePath(cat));
        case STUDENTS:
            return readStudentAddressBook(addressBookStorage.getAddressBookFilePath(cat));
        case TUITIONCLASSES:
            return readTuitionClassAddressBook(addressBookStorage.getAddressBookFilePath(cat));
        default:
            return Optional.empty();
        }
    }

    @Override
    public Optional<ReadOnlyAddressBook> readTutorAddressBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readTutorAddressBook(filePath);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readStudentAddressBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readStudentAddressBook(filePath);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readTuitionClassAddressBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readTuitionClassAddressBook(filePath);
    }

    @Override
    public void saveAllAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        addressBookStorage.saveAllAddressBook(addressBook);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, AddressBookCategories cat) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath(cat), cat);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath, AddressBookCategories cat)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath, cat);
    }

}
