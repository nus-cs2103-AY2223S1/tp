package seedu.hrpro.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.exceptions.IllegalValueException;
import seedu.hrpro.commons.util.JsonUtil;
import seedu.hrpro.model.HRPro;
import seedu.hrpro.testutil.TypicalHRPro;

public class JsonSerializableHRProTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableHRProTest");
    private static final Path TYPICAL_PROJECTS_FILE = TEST_DATA_FOLDER.resolve("typicalProjectsHRPro.json");
    private static final Path INVALID_PROJECT_FILE = TEST_DATA_FOLDER.resolve("invalidProjectHRPro.json");
    private static final Path DUPLICATE_PROJECT_FILE = TEST_DATA_FOLDER.resolve("duplicateProjectHRPro.json");

    @Test
    public void toModelType_typicalProjectsFile_success() throws Exception {
        JsonSerializableHRPro dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROJECTS_FILE,
                JsonSerializableHRPro.class).get();
        HRPro hrProFromFile = dataFromFile.toModelType();
        HRPro typicalProjectsHRPro = TypicalHRPro.getTypicalHRPro();
        assertTrue(hrProFromFile.equals(typicalProjectsHRPro));
    }

    @Test
    public void toModelType_invalidProjectFile_throwsIllegalValueException() throws Exception {
        JsonSerializableHRPro dataFromFile = JsonUtil.readJsonFile(INVALID_PROJECT_FILE,
                JsonSerializableHRPro.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProjects_throwsIllegalValueException() throws Exception {
        JsonSerializableHRPro dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROJECT_FILE,
                JsonSerializableHRPro.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableHRPro.MESSAGE_DUPLICATE_PROJECT,
                dataFromFile::toModelType);
    }

}
