package seedu.pennyWise.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.pennyWise.commons.exceptions.DataConversionException;
import seedu.pennyWise.model.ReadOnlyPennyWise;
import seedu.pennyWise.model.ReadOnlyUserPrefs;
import seedu.pennyWise.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends PennyWiseStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPennyWiseFilePath();

    @Override
    Optional<ReadOnlyPennyWise> readPennyWise() throws DataConversionException, IOException;

    @Override
    void savePennyWise(ReadOnlyPennyWise pennyWise) throws IOException;

}
