package seedu.nutrigoals.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.commons.exceptions.IllegalValueException;
import seedu.nutrigoals.commons.util.JsonUtil;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.testutil.TypicalFoods;

public class JsonSerializableNutriGoalsTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableNutriGoalsTest");
    private static final Path TYPICAL_FOOD_FILE = TEST_DATA_FOLDER.resolve("typicalFoodNutriGoals.json");
    private static final Path INVALID_FOOD_FILE = TEST_DATA_FOLDER.resolve("invalidFoodNutriGoals.json");
    private static final Path DUPLICATE_FOOD_FILE = TEST_DATA_FOLDER.resolve("duplicateFoodNutriGoals.json");

    @Test
    public void toModelType_typicalFoodFile_success() throws Exception {
        JsonSerializableNutriGoals dataFromFile = JsonUtil.readJsonFile(TYPICAL_FOOD_FILE,
                JsonSerializableNutriGoals.class).get();
        NutriGoals nutriGoalsFromFile = dataFromFile.toModelType();
        NutriGoals typicalFoodNutriGoals = TypicalFoods.getTypicalNutriGoals();
        assertEquals(nutriGoalsFromFile, typicalFoodNutriGoals);
    }

    @Test
    public void toModelType_invalidFoodFile_throwsIllegalValueException() throws Exception {
        JsonSerializableNutriGoals dataFromFile = JsonUtil.readJsonFile(INVALID_FOOD_FILE,
                JsonSerializableNutriGoals.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFoods_throwsIllegalValueException() throws Exception {
        JsonSerializableNutriGoals dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FOOD_FILE,
                JsonSerializableNutriGoals.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableNutriGoals.MESSAGE_DUPLICATE_FOOD,
                dataFromFile::toModelType);
    }

}
