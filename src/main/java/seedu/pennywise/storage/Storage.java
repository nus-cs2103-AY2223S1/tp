package seedu.pennywise.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.pennywise.commons.exceptions.DataConversionException;
import seedu.pennywise.model.ReadOnlyPennyWise;
import seedu.pennywise.model.ReadOnlyUserPrefs;
import seedu.pennywise.model.UserPrefs;

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
