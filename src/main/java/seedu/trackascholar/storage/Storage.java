package seedu.trackascholar.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.trackascholar.commons.exceptions.DataConversionException;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.ReadOnlyUserPrefs;
import seedu.trackascholar.model.UserPrefs;

/**
 * API of the Storage component.
 */
public interface Storage extends TrackAScholarStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTrackAScholarFilePath();

    @Override
    Optional<ReadOnlyTrackAScholar> readTrackAScholar() throws DataConversionException, IOException;

    @Override
    void saveTrackAScholar(ReadOnlyTrackAScholar trackAScholar) throws IOException;

}
