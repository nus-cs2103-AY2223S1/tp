package seedu.foodrem.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.util.JsonUtil;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.testutil.TypicalFoodRem;

public class JsonSerializableFoodRemTest {
    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableFoodRemTest");
    private static final Path TYPICAL_ITEMS_FILE = TEST_DATA_FOLDER
            .resolve("typicalItemsFoodRem.json");
    private static final Path INVALID_ITEM_FILE = TEST_DATA_FOLDER
            .resolve("invalidItemFoodRem.json");
    private static final Path DUPLICATE_ITEM_FILE = TEST_DATA_FOLDER
            .resolve("duplicateItemFoodRem.json");
    private static final Path DUPLICATE_TAG_FILE = TEST_DATA_FOLDER
            .resolve("duplicateTagFoodRem.json");
    private static final Path INVALID_TAG_FILE = TEST_DATA_FOLDER
            .resolve("invalidTagFoodRem.json");
    private static final Path TYPICAL_FOODREM_FILE = TEST_DATA_FOLDER
            .resolve("typicalFoodRem.json");

    @Test
    public void toModelType_typicalItemsFile_success() throws Exception {
        JsonSerializableFoodRem dataFromFile = JsonUtil.readJsonFile(TYPICAL_ITEMS_FILE,
                JsonSerializableFoodRem.class).get();
        FoodRem foodRemFromFile = dataFromFile.toModelType();
        FoodRem typicalItemsFoodRem = TypicalFoodRem.getFoodRemWithTypicalItems();
        assertEquals(foodRemFromFile, typicalItemsFoodRem);
    }

    @Test
    public void toModelType_invalidItemFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodRem dataFromFile = JsonUtil.readJsonFile(INVALID_ITEM_FILE,
                JsonSerializableFoodRem.class).get();
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateItems_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodRem dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ITEM_FILE,
                JsonSerializableFoodRem.class).get();
        assertThrows(IllegalArgumentException.class, JsonSerializableFoodRem.MESSAGE_DUPLICATE_ITEMS,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidTagFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodRem dataFromFile = JsonUtil.readJsonFile(INVALID_TAG_FILE,
                JsonSerializableFoodRem.class).get();
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalFoodRemFile_success() throws Exception {
        JsonSerializableFoodRem dataFromFile = JsonUtil.readJsonFile(TYPICAL_FOODREM_FILE,
                JsonSerializableFoodRem.class).get();
        FoodRem foodRemFromFile = dataFromFile.toModelType();
        FoodRem typicalFoodRem = TypicalFoodRem.getTypicalFoodRem();
        assertEquals(foodRemFromFile, typicalFoodRem);
    }
}
