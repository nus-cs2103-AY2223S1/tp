package seedu.travelr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.travelr.commons.exceptions.DataConversionException;
import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.ReadOnlyUserPrefs;
import seedu.travelr.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TravelrStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTravelrFilePath();

    @Override
    Optional<ReadOnlyTravelr> readTravelr() throws DataConversionException, IOException;

    @Override
    void saveTravelr(ReadOnlyTravelr travelr) throws IOException;

}
