package seedu.nutrigoals.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.nutrigoals.commons.exceptions.DataConversionException;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.ReadOnlyUserPrefs;
import seedu.nutrigoals.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends NutriGoalsStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getNutriGoalsFilePath();

    @Override
    Optional<ReadOnlyNutriGoals> readNutriGoals() throws DataConversionException, IOException;

    @Override
    void saveNutriGoals(ReadOnlyNutriGoals nutriGoals) throws IOException;

}
