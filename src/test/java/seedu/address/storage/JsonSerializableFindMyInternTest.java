package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FindMyIntern;
import seedu.address.testutil.TypicalInternships;

public class JsonSerializableFindMyInternTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFindMyInternTest");
    private static final Path TYPICAL_INTERNSHIPS_FILE = TEST_DATA_FOLDER.resolve("typicalFindMyIntern.json");
    private static final Path INVALID_INTERNSHIP_FILE = TEST_DATA_FOLDER.resolve("invalidFindMyIntern.json");
    private static final Path DUPLICATE_INTERNSHIP_FILE =
            TEST_DATA_FOLDER.resolve("duplicateFindMyIntern.json");

    @Test
    public void toModelType_typicalInternshipsFile_success() throws Exception {
        JsonSerializableFindMyIntern dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERNSHIPS_FILE,
                JsonSerializableFindMyIntern.class).get();
        FindMyIntern findMyInternFromFile = dataFromFile.toModelType();
        FindMyIntern typicalInternshipsFindMyIntern = TypicalInternships.getTypicalFindMyIntern();
        assertEquals(findMyInternFromFile, typicalInternshipsFindMyIntern);
    }

    @Test
    public void toModelType_invalidInternshipFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFindMyIntern dataFromFile = JsonUtil.readJsonFile(INVALID_INTERNSHIP_FILE,
                JsonSerializableFindMyIntern.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateInternships_throwsIllegalValueException() throws Exception {
        JsonSerializableFindMyIntern dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERNSHIP_FILE,
                JsonSerializableFindMyIntern.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFindMyIntern.MESSAGE_DUPLICATE_INTERNSHIP,
                dataFromFile::toModelType);
    }

}
