package seedu.trackascholar.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.trackascholar.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.commons.exceptions.IllegalValueException;
import seedu.trackascholar.commons.util.JsonUtil;
import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.testutil.TypicalApplicants;

public class JsonSerializableTrackAScholarTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableTrackAScholarTest");
    private static final Path TYPICAL_APPLICANTS_FILE =
            TEST_DATA_FOLDER.resolve("typicalApplicantsTrackAScholar.json");
    private static final Path INVALID_APPLICANT_FILE =
            TEST_DATA_FOLDER.resolve("invalidApplicantTrackAScholar.json");
    private static final Path DUPLICATE_APPLICANT_FILE =
            TEST_DATA_FOLDER.resolve("duplicateApplicantTrackAScholar.json");

    @Test
    public void toModelType_typicalApplicantsFile_success() throws Exception {
        JsonSerializableTrackAScholar dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPLICANTS_FILE,
                JsonSerializableTrackAScholar.class).get();
        TrackAScholar trackAScholarFromFile = dataFromFile.toModelType();
        TrackAScholar typicalApplicantsTrackAScholar = TypicalApplicants.getTypicalTrackAScholar();
        assertEquals(trackAScholarFromFile, typicalApplicantsTrackAScholar);
    }

    @Test
    public void toModelType_invalidApplicantFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackAScholar dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICANT_FILE,
                JsonSerializableTrackAScholar.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplicants_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackAScholar dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICANT_FILE,
                JsonSerializableTrackAScholar.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTrackAScholar.MESSAGE_DUPLICATE_APPLICANT,
                dataFromFile::toModelType);
    }

}
