package bookface.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import bookface.commons.exceptions.DataConversionException;
import bookface.model.ReadOnlyBookFace;
import bookface.model.ReadOnlyUserPrefs;
import bookface.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends BookFaceStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getBookFaceFilePath();

    @Override
    Optional<ReadOnlyBookFace> readBookFace() throws DataConversionException, IOException;

    @Override
    void saveBookFace(ReadOnlyBookFace bookFace) throws IOException;

}
