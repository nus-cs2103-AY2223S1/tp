package seedu.rc4hdb.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyUserPrefs;
import seedu.rc4hdb.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ResidentBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getResidentBookFilePath();

    @Override
    void setResidentBookFilePath(Path filePath);

    @Override
    Optional<ReadOnlyResidentBook> readResidentBook() throws DataConversionException, IOException;

    @Override
    void saveResidentBook(ReadOnlyResidentBook residentBook) throws IOException;

    @Override
    void deleteResidentBook(Path filePath) throws IOException;

}
