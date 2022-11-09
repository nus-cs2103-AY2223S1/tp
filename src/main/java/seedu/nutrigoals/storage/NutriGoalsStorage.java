package seedu.nutrigoals.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.nutrigoals.commons.exceptions.DataConversionException;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;

/**
 * Represents a storage for {@link NutriGoals}.
 */
public interface NutriGoalsStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getNutriGoalsFilePath();

    /**
     * Returns NutriGoals data as a {@link ReadOnlyNutriGoals}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyNutriGoals> readNutriGoals() throws DataConversionException, IOException;

    /**
     * @see #getNutriGoalsFilePath()
     */
    Optional<ReadOnlyNutriGoals> readNutriGoals(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyNutriGoals} to the storage.
     * @param nutriGoals cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNutriGoals(ReadOnlyNutriGoals nutriGoals) throws IOException;

    /**
     * @see #saveNutriGoals(ReadOnlyNutriGoals)
     */
    void saveNutriGoals(ReadOnlyNutriGoals nutriGoals, Path filePath) throws IOException;

}
