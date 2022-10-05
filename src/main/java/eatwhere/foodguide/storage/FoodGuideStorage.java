package eatwhere.foodguide.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import eatwhere.foodguide.commons.exceptions.DataConversionException;
import eatwhere.foodguide.model.AddressBook;
import eatwhere.foodguide.model.ReadOnlyAddressBook;

/**
 * Represents a storage for {@link AddressBook}.
 */
public interface FoodGuideStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFoodGuideFilePath();

    /**
     * Returns FoodGuide data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readFoodGuide() throws DataConversionException, IOException;

    /**
     * @see #getFoodGuideFilePath()
     */
    Optional<ReadOnlyAddressBook> readFoodGuide(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param foodGuide cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFoodGuide(ReadOnlyAddressBook foodGuide) throws IOException;

    /**
     * @see #saveFoodGuide(ReadOnlyAddressBook)
     */
    void saveFoodGuide(ReadOnlyAddressBook foodGuide, Path filePath) throws IOException;

}
