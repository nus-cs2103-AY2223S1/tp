package eatwhere.foodguide.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import eatwhere.foodguide.commons.exceptions.DataConversionException;
import eatwhere.foodguide.model.ReadOnlyAddressBook;
import eatwhere.foodguide.model.ReadOnlyUserPrefs;
import eatwhere.foodguide.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FoodGuideStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFoodGuideFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readFoodGuide() throws DataConversionException, IOException;

    @Override
    void saveFoodGuide(ReadOnlyAddressBook foodGuide) throws IOException;

}
