package seedu.uninurse.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.uninurse.commons.exceptions.DataConversionException;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.ReadOnlyUserPrefs;
import seedu.uninurse.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends UninurseBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getUninurseBookFilePath();

    @Override
    Optional<ReadOnlyUninurseBook> readUninurseBook() throws DataConversionException, IOException;

    @Override
    void saveUninurseBook(ReadOnlyUninurseBook uninurseBook) throws IOException;

}
