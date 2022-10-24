package seedu.rc4hdb.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.beans.value.ObservableValue;
import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.storage.residentbook.ResidentBookStorage;
import seedu.rc4hdb.storage.venuebook.VenueBookStorage;

/**
 * Represents a storage for {@link seedu.rc4hdb.model.ResidentBook} and {@link seedu.rc4hdb.model.VenueBook}.
 */
public interface DataStorage extends ResidentBookStorage, VenueBookStorage {

    //================= File path methods ==========================

    /**
     * Returns the file path of the data file.
     */
    Path getDataStorageFilePath();

    /**
     * Returns the folder path wrapped by ObservableValue.
     */
    ObservableValue<Path> getObservableFolderPath();

    /**
     * Sets the file path to the {@code folderPath}.
     */
    void setDataStorageFilePath(Path folderPath);

    //================= Data storage methods =========================

    /**
     * Deletes the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws IOException if there was any problem deleting the directory.
     */
    void deleteDataFile(Path folderPath) throws IOException;

    /**
     * Creates an empty resident and venue data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws IOException if there was any problem creating the file.
     */
    void createDataFile(Path folderPath) throws IOException;

    //================= Resident book methods =======================

    /**
     * Reads the current resident data file.
     */
    Optional<ReadOnlyResidentBook> readResidentBook() throws DataConversionException, IOException;


    /**
     * Saves the data from {@code residentBook} onto the current resident data file.
     */
    void saveResidentBook(ReadOnlyResidentBook residentBook) throws IOException;

    //================= Venue book methods ==========================

    /**
     * Reads the current venue data file.
     */
    Optional<ReadOnlyVenueBook> readVenueBook() throws DataConversionException, IOException;

    /**
     * Saves the data from {@code venueBook} onto the current venue data file.
     */
    void saveVenueBook(ReadOnlyVenueBook venueBook) throws IOException;

}
