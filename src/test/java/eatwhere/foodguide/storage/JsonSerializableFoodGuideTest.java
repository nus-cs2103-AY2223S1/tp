package eatwhere.foodguide.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.commons.exceptions.IllegalValueException;
import eatwhere.foodguide.commons.util.JsonUtil;
import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.TypicalEateries;

public class JsonSerializableFoodGuideTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFoodGuideTest");
    private static final Path TYPICAL_FOODGUIDE_FILE = TEST_DATA_FOLDER.resolve("typicalEateriesFoodGuide.json");
    private static final Path INVALID_FOODGUIDE_FILE = TEST_DATA_FOLDER.resolve("invalidEateryFoodGuide.json");
    private static final Path DUPLICATE_FOODGUIDE_FILE = TEST_DATA_FOLDER.resolve("duplicateEateryFoodGuide.json");

    @Test
    public void toModelType_typicalFoodGuideFile_success() throws Exception {
        JsonSerializableFoodGuide dataFromFile = JsonUtil.readJsonFile(TYPICAL_FOODGUIDE_FILE,
                JsonSerializableFoodGuide.class).get();
        FoodGuide foodGuideFromFile = dataFromFile.toModelType();
        FoodGuide typicalFoodGuide = TypicalEateries.getTypicalFoodGuide();
        assertEquals(foodGuideFromFile, typicalFoodGuide);
    }

    @Test
    public void toModelType_invalidFoodGuideFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodGuide dataFromFile = JsonUtil.readJsonFile(INVALID_FOODGUIDE_FILE,
                JsonSerializableFoodGuide.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFoodGuide_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodGuide dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FOODGUIDE_FILE,
                JsonSerializableFoodGuide.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableFoodGuide.MESSAGE_DUPLICATE_EATERY,
                dataFromFile::toModelType);
    }

}
