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
import seedu.rc4hdb.storage.userprefs.UserPrefsStorage;

/**
 * API of the Storage component
 */
public interface Storage extends DataStorage, UserPrefsStorage {

    //================ File Path methods ==============================

    @Override
    Path getDataStorageFolderPath();

    @Override
    ObservableValue<Path> getObservableFolderPath();

    @Override
    void setDataStorageFolderPath(Path folderPath);

    //================ UserPrefs methods ==============================

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    //================ ResidentBook methods ===========================


    //================ VenueBook methods ===============================

    @Override
    Optional<ReadOnlyVenueBook> readVenueBook() throws DataConversionException, IOException;

    @Override
    void saveVenueBook(ReadOnlyVenueBook venueBook) throws IOException;

    @Override
    void deleteVenueBookFile(Path folderPath) throws IOException;

    @Override
    void createVenueBookFile(Path folderPath) throws IOException;

    //================ CsvResidentBookStorage methods ==================

    Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws IOException, DataConversionException;
}
