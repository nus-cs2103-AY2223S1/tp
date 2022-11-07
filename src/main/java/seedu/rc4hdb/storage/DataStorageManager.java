package seedu.rc4hdb.storage;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.storage.residentbook.JsonResidentBookStorage;
import seedu.rc4hdb.storage.residentbook.ResidentBookStorage;
import seedu.rc4hdb.storage.venuebook.JsonVenueBookStorage;
import seedu.rc4hdb.storage.venuebook.VenueBookStorage;
import seedu.rc4hdb.ui.ObservableItem;

/**
 * Manages storage of Resident and Venue data in local storage.
 */
public class DataStorageManager implements DataStorage {

    public static final String MESSAGE_FOLDER_ALREADY_EXIST = "%s sub data folder already exists. "
            + "Delete the old folder before trying again.";

    public static final String MESSAGE_FOLDER_DOES_NOT_EXIST = "%s sub data folder does not exist.";

    private static final Logger logger = LogsCenter.getLogger(DataStorageManager.class);

    private ObservableItem<Path> folderPath;
    private ResidentBookStorage residentBookStorage = new JsonResidentBookStorage();
    private VenueBookStorage venueBookStorage = new JsonVenueBookStorage();

    public DataStorageManager(Path folderPath) {
        this.folderPath = new ObservableItem<>(folderPath);
    }

    //================= File path methods ==========================

    @Override
    public Path getDataStorageFolderPath() {
        return folderPath.getValue();
    }

    @Override
    public ObservableValue<Path> getObservableFolderPath() {
        return folderPath;
    }

    @Override
    public void setDataStorageFolderPath(Path folderPath) {
        requireNonNull(folderPath);
        logger.info(String.format("Updating current working folder to: %s", folderPath));
        this.folderPath.setValue(folderPath);
    }

    //================= Data Storage Methods ========================

    /**
     * Deletes the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws IOException if there was any problem deleting the directory.
     */
    @Override
    public void deleteDataFolder(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        logger.info(String.format("Attempting to delete folder: %s", folderPath));
        if (!FileUtil.isFolderExists(folderPath)) {
            logger.warning(String.format("Folder does not exist: %s", folderPath));
            throw new NoSuchFileException(String.format(MESSAGE_FOLDER_DOES_NOT_EXIST, folderPath.getFileName()));
        }
        try {
            residentBookStorage.deleteResidentBookFile(folderPath);
        } catch (IOException e) {
            logger.info(String.format("Resident data file not found in %s folder", folderPath));
        }
        try {
            venueBookStorage.deleteVenueBookFile(folderPath);
        } catch (IOException e) {
            logger.info(String.format("Venue data file not found in %s folder", folderPath));
        }
        FileUtil.deleteFile(folderPath);
    }

    /**
     * Creates an empty resident and venue data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws IOException if there was any problem creating the files.
     */
    @Override
    public void createDataFolder(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        logger.info(String.format("Attempting to create folder: %s", folderPath));
        if (FileUtil.isFolderExists(folderPath)) {
            logger.warning(String.format("Folder already exists: %s", folderPath));
            throw new FileAlreadyExistsException(
                    String.format(MESSAGE_FOLDER_ALREADY_EXIST, folderPath.getFileName()));
        }
        residentBookStorage.createResidentBookFile(folderPath);
        venueBookStorage.createVenueBookFile(folderPath);
    }

    //================= Resident book methods =======================

    /**
     * Reads the current resident data file.
     */
    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook() throws DataConversionException, IOException {
        return residentBookStorage.readResidentBook(folderPath.getValue());
    }

    /**
     * Reads other resident data files.
     * @see ResidentBookStorage#readResidentBook(Path)
     */
    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook(Path folderPath)
            throws DataConversionException, IOException {
        requireNonNull(folderPath);
        return residentBookStorage.readResidentBook(folderPath);
    }

    /**
     * Saves the data from {@code residentBook} onto the current resident data file.
     */
    @Override
    public void saveResidentBook(ReadOnlyResidentBook residentBook) throws IOException {
        requireNonNull(residentBook);
        residentBookStorage.saveResidentBook(residentBook, folderPath.getValue());
    }

    /**
     * Saves the data from {@code residentBook} onto other resident data files.
     * @see ResidentBookStorage#saveResidentBook(ReadOnlyResidentBook, Path)
     */
    @Override
    public void saveResidentBook(ReadOnlyResidentBook residentBook, Path folderPath) throws IOException {
        requireAllNonNull(residentBook, folderPath);
        residentBookStorage.saveResidentBook(residentBook, folderPath);
    }

    /**
     * @see ResidentBookStorage#deleteResidentBookFile(Path)
     */
    @Override
    public void deleteResidentBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        residentBookStorage.deleteResidentBookFile(folderPath);
    }

    /**
     * @see ResidentBookStorage#createResidentBookFile(Path)
     */
    @Override
    public void createResidentBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        residentBookStorage.createResidentBookFile(folderPath);
    }

    //================= Venue book methods ==========================

    /**
     * Reads the current venue data file.
     */
    @Override
    public Optional<ReadOnlyVenueBook> readVenueBook() throws DataConversionException, IOException {
        return venueBookStorage.readVenueBook(folderPath.getValue());
    }

    /**
     * Reads other venue data files.
     * @see VenueBookStorage#readVenueBook(Path)
     */
    @Override
    public Optional<ReadOnlyVenueBook> readVenueBook(Path folderPath) throws DataConversionException, IOException {
        requireNonNull(folderPath);
        return venueBookStorage.readVenueBook(folderPath);
    }

    /**
     * Saves the data from {@code venueBook} onto the current venue data file.
     */
    @Override
    public void saveVenueBook(ReadOnlyVenueBook venueBook) throws IOException {
        requireNonNull(venueBook);
        venueBookStorage.saveVenueBook(venueBook, folderPath.getValue());
    }

    /**
     * Saves the data from {@code venueBook} onto other venue data files.
     * @see VenueBookStorage#saveVenueBook(ReadOnlyVenueBook, Path)
     */
    @Override
    public void saveVenueBook(ReadOnlyVenueBook venueBook, Path folderPath) throws IOException {
        requireAllNonNull(venueBook, folderPath);
        venueBookStorage.saveVenueBook(venueBook, folderPath);
    }

    /**
     * @see VenueBookStorage#deleteVenueBookFile(Path)
     */
    @Override
    public void deleteVenueBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        venueBookStorage.deleteVenueBookFile(folderPath);
    }

    /**
     * @see VenueBookStorage#createVenueBookFile(Path)
     */
    @Override
    public void createVenueBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        venueBookStorage.createVenueBookFile(folderPath);
    }

    //================= End of venue book methods ===================

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof DataStorageManager)) {
            return false;
        }

        // state check
        DataStorageManager other = (DataStorageManager) obj;
        return folderPath.equals(other.folderPath);
    }

}
