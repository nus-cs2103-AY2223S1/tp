package gim.storage;

import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import gim.commons.exceptions.IllegalValueException;
import gim.commons.util.JsonUtil;
import gim.model.ExerciseTracker;
import gim.testutil.TypicalExercises;

public class JsonSerializableExerciseTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableExerciseTrackerTest");
    private static final Path TYPICAL_EXERCISES_FILE = TEST_DATA_FOLDER.resolve("typicalExercisesExerciseTracker.json");
    private static final Path INVALID_EXERCISE_FILE = TEST_DATA_FOLDER.resolve("invalidExerciseExerciseTracker.json");
    private static final Path DUPLICATE_EXERCISE_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateExerciseExerciseTracker.json");

    @Test
    public void toModelType_typicalExercisesFile_success() throws Exception {
        JsonSerializableExerciseTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_EXERCISES_FILE,
                JsonSerializableExerciseTracker.class).get();
        ExerciseTracker exerciseTrackerFromFile = dataFromFile.toModelType();
        ExerciseTracker typicalExercisesExerciseTracker = TypicalExercises.getTypicalExerciseTracker();
        assertEquals(exerciseTrackerFromFile, typicalExercisesExerciseTracker);
    }

    @Test
    public void toModelType_invalidExerciseFile_throwsIllegalValueException() throws Exception {
        JsonSerializableExerciseTracker dataFromFile = JsonUtil.readJsonFile(INVALID_EXERCISE_FILE,
                JsonSerializableExerciseTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    //    @Test
    //    public void toModelType_duplicateExercises_throwsIllegalValueException() throws Exception {
    //        JsonSerializableExerciseTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EXERCISE_FILE,
    //                JsonSerializableExerciseTracker.class).get();
    //        assertThrows(IllegalValueException.class, JsonSerializableExerciseTracker.MESSAGE_DUPLICATE_EXERCISE,
    //                dataFromFile::toModelType);
    //    }

}
