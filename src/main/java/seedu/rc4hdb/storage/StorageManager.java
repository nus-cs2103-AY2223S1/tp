package seedu.rc4hdb.storage;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.beans.value.ObservableValue;
import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyUserPrefs;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.storage.csv.CsvFileManager;
import seedu.rc4hdb.storage.userprefs.UserPrefsStorage;

/**
 * Manages storage of RC4HDB data in local storage.
 */
public class StorageManager implements Storage {

    private DataStorage dataStorage;
    private UserPrefsStorage userPrefsStorage;
    private CsvFileManager csvManager = new CsvFileManager();

    /**
     * Creates a {@code StorageManager} with the given {@code DataStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(DataStorage dataStorage, UserPrefsStorage userPrefsStorage) {
        this.dataStorage = dataStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    //================ File Path methods ==============================

    @Override
    public Path getDataStorageFolderPath() {
        return dataStorage.getDataStorageFolderPath();
    }

    @Override
    public ObservableValue<Path> getObservableFolderPath() {
        return dataStorage.getObservableFolderPath();
    }

    @Override
    public void setDataStorageFolderPath(Path folderPath) {
        requireNonNull(folderPath);
        dataStorage.setDataStorageFolderPath(folderPath);
    }

    //================ UserPrefs methods ==============================

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
        requireNonNull(userPrefs);
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    //================ DataStorage methods ==============================

    /**
     * @see DataStorage#deleteDataFolder(Path)
     */
    public void deleteDataFolder(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        dataStorage.deleteDataFolder(folderPath);
    }

    /**
     * @see DataStorage#createDataFolder(Path)
     */
    public void createDataFolder(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        dataStorage.createDataFolder(folderPath);
    }

    //================ ResidentBook methods =============================

    /**
     * @see DataStorage#readResidentBook()
     */
    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook() throws DataConversionException, IOException {
        return dataStorage.readResidentBook();
    }

    /**
     * @see DataStorage#readResidentBook(Path)
     */
    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook(Path folderPath)
            throws DataConversionException, IOException {
        requireNonNull(folderPath);
        return dataStorage.readResidentBook(folderPath);
    }

    /**
     * @see DataStorage#saveResidentBook(ReadOnlyResidentBook)
     */
    @Override
    public void saveResidentBook(ReadOnlyResidentBook residentBook) throws IOException {
        requireNonNull(residentBook);
        dataStorage.saveResidentBook(residentBook);
    }

    /**
     * @see DataStorage#saveResidentBook(ReadOnlyResidentBook, Path)
     */
    @Override
    public void saveResidentBook(ReadOnlyResidentBook residentBook, Path folderPath) throws IOException {
        requireAllNonNull(residentBook, folderPath);
        dataStorage.saveResidentBook(residentBook, folderPath);
    }

    /**
     * @see DataStorage#deleteResidentBookFile(Path)
     */
    @Override
    public void deleteResidentBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        dataStorage.deleteResidentBookFile(folderPath);
    }

    /**
     * @see DataStorage#createResidentBookFile(Path)
     */
    @Override
    public void createResidentBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        dataStorage.createResidentBookFile(folderPath);
    }

    //================ VenueBook methods ================================

    /**
     * @see DataStorage#readVenueBook()
     */
    @Override
    public Optional<ReadOnlyVenueBook> readVenueBook() throws DataConversionException, IOException {
        return dataStorage.readVenueBook();
    }

    /**
     * @see DataStorage#readVenueBook(Path)
     */
    @Override
    public Optional<ReadOnlyVenueBook> readVenueBook(Path folderPath) throws DataConversionException, IOException {
        return dataStorage.readVenueBook(folderPath);
    }

    /**
     * @see DataStorage#saveVenueBook(ReadOnlyVenueBook)
     */
    @Override
    public void saveVenueBook(ReadOnlyVenueBook venueBook) throws IOException {
        requireNonNull(venueBook);
        dataStorage.saveVenueBook(venueBook);
    }

    /**
     * @see DataStorage#saveVenueBook(ReadOnlyVenueBook, Path)
     */
    @Override
    public void saveVenueBook(ReadOnlyVenueBook venueBook, Path folderPath) throws IOException {
        requireAllNonNull(venueBook, folderPath);
        dataStorage.saveVenueBook(venueBook, folderPath);
    }

    /**
     * @see DataStorage#deleteVenueBookFile(Path)
     */
    @Override
    public void deleteVenueBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        dataStorage.deleteVenueBookFile(folderPath);
    }

    /**
     * @see DataStorage#createVenueBookFile(Path)
     */
    @Override
    public void createVenueBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        dataStorage.createVenueBookFile(folderPath);
    }

    //================ CsvResidentBookStorage methods ===================

    @Override
    public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws IOException, DataConversionException {
        return csvManager.readCsvFile(filePath);
    }

    //================ End of CsvResidentBookStorage methods ============

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof StorageManager)) {
            return false;
        }

        // state check
        StorageManager other = (StorageManager) obj;
        return dataStorage.equals(other.dataStorage)
                && userPrefsStorage.equals(other.userPrefsStorage);
    }

}
