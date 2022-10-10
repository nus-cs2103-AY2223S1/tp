package seedu.waddle.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.waddle.commons.exceptions.DataConversionException;
import seedu.waddle.model.ReadOnlyUserPrefs;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends WaddleStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getWaddleFilePath();

    @Override
    Optional<ReadOnlyWaddle> readWaddle() throws DataConversionException, IOException;

    @Override
    void saveWaddle(ReadOnlyWaddle addressBook) throws IOException;

}
