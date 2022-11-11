package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskPanel;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private TaskPanelStorage taskPanelStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}, {@code TaskPanelStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, TaskPanelStorage taskPanelStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.taskPanelStorage = taskPanelStorage;
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
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ TaskPanel methods ==============================

    @Override
    public Path getTaskPanelFilePath() {
        return taskPanelStorage.getTaskPanelFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskPanel> readTaskPanel() throws DataConversionException, IOException {
        return readTaskPanel(taskPanelStorage.getTaskPanelFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskPanel> readTaskPanel(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read task data from file: " + filePath);
        return taskPanelStorage.readTaskPanel(filePath);
    }

    @Override
    public void saveTaskPanel(ReadOnlyTaskPanel taskPanel) throws IOException {
        saveTaskPanel(taskPanel, taskPanelStorage.getTaskPanelFilePath());
    }

    @Override
    public void saveTaskPanel(ReadOnlyTaskPanel taskPanel, Path filePath) throws IOException {
        logger.fine("Attempting to write to task data file: " + filePath);
        taskPanelStorage.saveTaskPanel(taskPanel, filePath);
    }

}
