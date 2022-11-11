package seedu.intrack.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.intrack.commons.exceptions.DataConversionException;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.ReadOnlyUserPrefs;
import seedu.intrack.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends InTrackStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getInTrackFilePath();

    @Override
    Optional<ReadOnlyInTrack> readInTrack() throws DataConversionException, IOException;

    @Override
    void saveInTrack(ReadOnlyInTrack inTrack) throws IOException;

}
