package eatwhere.foodguide.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import eatwhere.foodguide.commons.exceptions.DataConversionException;
import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.ReadOnlyFoodGuide;

/**
 * Represents a storage for {@link FoodGuide}.
 */
public interface FoodGuideStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFoodGuideFilePath();

    /**
     * Returns FoodGuide data as a {@link ReadOnlyFoodGuide}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFoodGuide> readFoodGuide() throws DataConversionException, IOException;

    /**
     * @see #getFoodGuideFilePath()
     */
    Optional<ReadOnlyFoodGuide> readFoodGuide(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFoodGuide} to the storage.
     * @param foodGuide cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFoodGuide(ReadOnlyFoodGuide foodGuide) throws IOException;

    /**
     * @see #saveFoodGuide(ReadOnlyFoodGuide)
     */
    void saveFoodGuide(ReadOnlyFoodGuide foodGuide, Path filePath) throws IOException;

}
