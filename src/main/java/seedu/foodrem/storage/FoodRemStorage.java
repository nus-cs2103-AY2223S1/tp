package seedu.foodrem.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.foodrem.commons.exceptions.DataConversionException;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.ReadOnlyFoodRem;

/**
 * Represents a storage for {@link FoodRem}.
 */
public interface FoodRemStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFoodRemFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyFoodRem}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFoodRem> readFoodRem() throws DataConversionException, IOException;

    /**
     * @see #getFoodRemFilePath()
     */
    Optional<ReadOnlyFoodRem> readFoodRem(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFoodRem} to the storage.
     *
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFoodRem(ReadOnlyFoodRem addressBook) throws IOException;

    /**
     * @see #saveFoodRem(ReadOnlyFoodRem)
     */
    void saveFoodRem(ReadOnlyFoodRem addressBook, Path filePath) throws IOException;

}
