package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.JeeqTracker;
import seedu.address.testutil.TypicalCompanies;

public class JsonSerializableJeeqTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableJeeqTrackerTest");
    private static final Path TYPICAL_COMPANIES_FILE = TEST_DATA_FOLDER.resolve("typicalCompaniesJeeqTracker.json");
    private static final Path INVALID_COMPANY_FILE = TEST_DATA_FOLDER.resolve("invalidCompanyJeeqTracker.json");
    private static final Path DUPLICATE_COMPANY_FILE = TEST_DATA_FOLDER.resolve("duplicateCompanyJeeqTracker.json");

    @Test
    public void toModelType_typicalCompaniesFile_success() throws Exception {
        JsonSerializableJeeqTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_COMPANIES_FILE,
                JsonSerializableJeeqTracker.class).get();
        JeeqTracker jeeqTrackerFromFile = dataFromFile.toModelType();
        JeeqTracker typicalCompaniesJeeqTracker = TypicalCompanies.getTypicalJeeqTracker();
        assertEquals(jeeqTrackerFromFile, typicalCompaniesJeeqTracker);
    }

    @Test
    public void toModelType_invalidCompanyFile_throwsIllegalValueException() throws Exception {
        JsonSerializableJeeqTracker dataFromFile = JsonUtil.readJsonFile(INVALID_COMPANY_FILE,
                JsonSerializableJeeqTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCompanies_throwsIllegalValueException() throws Exception {
        JsonSerializableJeeqTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_COMPANY_FILE,
                JsonSerializableJeeqTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableJeeqTracker.MESSAGE_DUPLICATE_CLIENT,
                dataFromFile::toModelType);
    }

}
