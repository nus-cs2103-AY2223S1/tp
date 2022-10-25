package seedu.rc4hdb.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.beans.value.ObservableValue;
import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyUserPrefs;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.UserPrefs;

/**
 * A default storage stub where all methods fail.
 */
public class StorageStub implements Storage {

    //================ User Prefs Storage Methods ==========================

    @Override
    public Path getUserPrefsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    //================ File Path Methods ===================================

    @Override
    public ObservableValue<Path> getObservableFolderPath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getDataStorageFolderPath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setDataStorageFolderPath(Path folderPath) {
        throw new AssertionError("This method should not be called.");
    }

    //================ Data Storage Methods ================================

    @Override
    public void deleteDataFolder(Path folderPath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void createDataFolder(Path folderPath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    //================= Resident Book Storage methods =======================

    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook() throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook(Path filePath) throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveResidentBook(ReadOnlyResidentBook residentBook, Path filePath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveResidentBook(ReadOnlyResidentBook residentBook) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteResidentBookFile(Path filePath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void createResidentBookFile(Path filePath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    //================= Venue Book Storage methods ==========================

    @Override
    public Optional<ReadOnlyVenueBook> readVenueBook() throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyVenueBook> readVenueBook(Path folderPath) throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveVenueBook(ReadOnlyVenueBook venueBook) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveVenueBook(ReadOnlyVenueBook venueBook, Path folderPath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteVenueBookFile(Path folderPath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void createVenueBookFile(Path folderPath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    //================= CSV File Manager methods ============================

    @Override
    public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws IOException, DataConversionException {
        throw new AssertionError("This method should not be called.");
    }

}
