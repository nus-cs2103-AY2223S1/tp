package seedu.realtime.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.realtime.commons.exceptions.DataConversionException;
import seedu.realtime.model.ReadOnlyRealTime;
import seedu.realtime.model.ReadOnlyUserPrefs;
import seedu.realtime.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends RealTimeStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getRealTimeFilePath();

    @Override
    Optional<ReadOnlyRealTime> readRealTime() throws DataConversionException, IOException;

    @Override
    void saveRealTime(ReadOnlyRealTime realTime) throws IOException;

}
