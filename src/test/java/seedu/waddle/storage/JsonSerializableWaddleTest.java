package seedu.waddle.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.commons.util.JsonUtil;
import seedu.waddle.model.Waddle;
import seedu.waddle.testutil.TypicalItineraries;

public class JsonSerializableWaddleTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableWaddleTest");
    private static final Path TYPICAL_ITINERARIES_FILE = TEST_DATA_FOLDER.resolve("typicalItinerariesWaddle.json");
    private static final Path INVALID_ITINERARY_FILE = TEST_DATA_FOLDER.resolve("invalidItineraryWaddle.json");
    private static final Path DUPLICATE_ITINERARY_FILE = TEST_DATA_FOLDER.resolve("duplicateItineraryWaddle.json");

    @Test
    public void toModelType_typicalItinerariesFile_success() throws Exception {
        JsonSerializableWaddle dataFromFile = JsonUtil.readJsonFile(TYPICAL_ITINERARIES_FILE,
                JsonSerializableWaddle.class).get();
        Waddle waddleFromFile = dataFromFile.toModelType();
        Waddle typicalItinerariesWaddle = TypicalItineraries.getTypicalWaddle();
        assertEquals(waddleFromFile, typicalItinerariesWaddle);
    }

    @Test
    public void toModelType_invalidItineraryFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWaddle dataFromFile = JsonUtil.readJsonFile(INVALID_ITINERARY_FILE,
                JsonSerializableWaddle.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateItinerary_throwsIllegalValueException() throws Exception {
        JsonSerializableWaddle dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ITINERARY_FILE,
                JsonSerializableWaddle.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWaddle.MESSAGE_DUPLICATE_ITINERARY,
                dataFromFile::toModelType);
    }

}
