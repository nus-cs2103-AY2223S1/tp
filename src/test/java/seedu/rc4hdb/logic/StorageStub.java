package seedu.rc4hdb.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyUserPrefs;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.storage.Storage;

/**
 * A default ResidentBookStorage stub where all methods fail.
 */
public class StorageStub implements Storage {

    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook(Path filePath) throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook() throws DataConversionException, IOException {
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

    @Override
    public Path getResidentBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setResidentBookFilePath(Path filePath) {
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
}
