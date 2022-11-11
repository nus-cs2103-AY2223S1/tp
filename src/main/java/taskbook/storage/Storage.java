package taskbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import taskbook.commons.exceptions.DataConversionException;
import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.ReadOnlyUserPrefs;
import taskbook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskBookFilePath();

    @Override
    Optional<ReadOnlyTaskBook> readTaskBook() throws DataConversionException, IOException;

    @Override
    void saveTaskBook(ReadOnlyTaskBook taskBook) throws IOException;

}
