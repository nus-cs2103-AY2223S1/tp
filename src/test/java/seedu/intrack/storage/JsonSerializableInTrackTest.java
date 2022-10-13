package seedu.intrack.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intrack.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.commons.util.JsonUtil;
import seedu.intrack.model.InTrack;
import seedu.intrack.testutil.TypicalInternships;

public class JsonSerializableInTrackTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableInTrackTest");
    private static final Path TYPICAL_INTERNSHIPS_FILE = TEST_DATA_FOLDER.resolve("typicalInternshipsInTrack.json");
    private static final Path INVALID_INTERNSHIP_FILE = TEST_DATA_FOLDER.resolve("invalidInternshipInTrack.json");
    private static final Path DUPLICATE_INTERNSHIP_FILE =
            TEST_DATA_FOLDER.resolve("duplicateInternshipInTrack.json");

    @Test
    public void toModelType_typicalInternshipsFile_success() throws Exception {
        JsonSerializableInTrack dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERNSHIPS_FILE,
                JsonSerializableInTrack.class).get();
        InTrack inTrackFromFile = dataFromFile.toModelType();
        InTrack typicalInternshipsInTrack = TypicalInternships.getTypicalInTrack();
        assertEquals(inTrackFromFile, typicalInternshipsInTrack);
    }

    @Test
    public void toModelType_invalidInternshipFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInTrack dataFromFile = JsonUtil.readJsonFile(INVALID_INTERNSHIP_FILE,
                JsonSerializableInTrack.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateInternships_throwsIllegalValueException() throws Exception {
        JsonSerializableInTrack dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERNSHIP_FILE,
                JsonSerializableInTrack.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInTrack.MESSAGE_DUPLICATE_INTERNSHIP,
                dataFromFile::toModelType);
    }

}
