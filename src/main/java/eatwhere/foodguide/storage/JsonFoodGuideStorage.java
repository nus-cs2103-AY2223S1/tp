package eatwhere.foodguide.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import eatwhere.foodguide.commons.core.LogsCenter;
import eatwhere.foodguide.commons.exceptions.DataConversionException;
import eatwhere.foodguide.commons.exceptions.IllegalValueException;
import eatwhere.foodguide.commons.util.FileUtil;
import eatwhere.foodguide.commons.util.JsonUtil;
import eatwhere.foodguide.model.ReadOnlyAddressBook;

/**
 * A class to access FoodGuide data stored as a json file on the hard disk.
 */
public class JsonFoodGuideStorage implements FoodGuideStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFoodGuideStorage.class);

    private Path filePath;

    public JsonFoodGuideStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFoodGuideFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readFoodGuide() throws DataConversionException {
        return readFoodGuide(filePath);
    }

    /**
     * Similar to {@link #readFoodGuide()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readFoodGuide(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFoodGuide> jsonFoodGuide = JsonUtil.readJsonFile(
                filePath, JsonSerializableFoodGuide.class);
        if (!jsonFoodGuide.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFoodGuide.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFoodGuide(ReadOnlyAddressBook foodGuide) throws IOException {
        saveFoodGuide(foodGuide, filePath);
    }

    /**
     * Similar to {@link #saveFoodGuide(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFoodGuide(ReadOnlyAddressBook foodGuide, Path filePath) throws IOException {
        requireNonNull(foodGuide);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFoodGuide(foodGuide), filePath);
    }

}
