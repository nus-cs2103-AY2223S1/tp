package seedu.hrpro.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.exceptions.IllegalValueException;
import seedu.hrpro.commons.util.JsonUtil;
import seedu.hrpro.model.HrPro;
import seedu.hrpro.testutil.TypicalHrPro;

public class JsonSerializableHrProTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableHrProTest");
    private static final Path TYPICAL_PROJECTS_FILE = TEST_DATA_FOLDER.resolve("typicalProjectsHrPro.json");
    private static final Path INVALID_PROJECT_FILE = TEST_DATA_FOLDER.resolve("invalidProjectHrPro.json");
    private static final Path DUPLICATE_PROJECT_FILE = TEST_DATA_FOLDER.resolve("duplicateProjectHrPro.json");

    @Test
    public void toModelType_typicalProjectsFile_success() throws Exception {
        JsonSerializableHrPro dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROJECTS_FILE,
                JsonSerializableHrPro.class).get();
        HrPro hrProFromFile = dataFromFile.toModelType();
        HrPro typicalProjectsHrPro = TypicalHrPro.getTypicalHrPro();
        assertTrue(hrProFromFile.equals(typicalProjectsHrPro));
    }

    @Test
    public void toModelType_invalidProjectFile_throwsIllegalValueException() throws Exception {
        JsonSerializableHrPro dataFromFile = JsonUtil.readJsonFile(INVALID_PROJECT_FILE,
                JsonSerializableHrPro.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProjects_throwsIllegalValueException() throws Exception {
        JsonSerializableHrPro dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROJECT_FILE,
                JsonSerializableHrPro.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableHrPro.MESSAGE_DUPLICATE_PROJECT,
                dataFromFile::toModelType);
    }

}
