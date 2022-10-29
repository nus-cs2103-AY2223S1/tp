package seedu.pennywise.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pennywise.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.pennywise.commons.exceptions.IllegalValueException;
import seedu.pennywise.commons.util.JsonUtil;
import seedu.pennywise.model.PennyWise;
import seedu.pennywise.testutil.TypicalEntry;
public class JsonSerializablePennyWiseTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePennyWiseTest");
    private static final Path TYPICAL_ENTRIES_FILE = TEST_DATA_FOLDER.resolve("typicalEntriesPennyWise.json");
    private static final Path INVALID_ENTRY_FILE = TEST_DATA_FOLDER.resolve("invalidEntryPennyWise.json");
    private static final Path DUPLICATE_EXPENDITURE_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateExpenditurePennyWise.json"
    );
    private static final Path DUPLICATE_INCOME_FILE = TEST_DATA_FOLDER.resolve("duplicateIncomePennyWise.json");

    @Test
    public void toModelType_typicalEntriesFile_success() throws Exception {
        JsonSerializablePennyWise dataFromFile = JsonUtil.readJsonFile(TYPICAL_ENTRIES_FILE,
                JsonSerializablePennyWise.class).get();
        PennyWise pennyWiseFromFile = dataFromFile.toModelType();
        PennyWise typicalEntryPennyWise = TypicalEntry.getTypicalPennyWise();
        assertEquals(pennyWiseFromFile, typicalEntryPennyWise);
    }
    @Test
    public void toModelType_invalidEntryFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePennyWise dataFromFile = JsonUtil.readJsonFile(INVALID_ENTRY_FILE,
                JsonSerializablePennyWise.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
    @Test
    public void toModelType_duplicateExpenditures_throwsIllegalValueException() throws Exception {
        JsonSerializablePennyWise dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EXPENDITURE_FILE,
                JsonSerializablePennyWise.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePennyWise.MESSAGE_DUPLICATE_EXPENDITURE,
                dataFromFile::toModelType);
    }
    @Test
    public void toModelType_duplicateIncome_throwsIllegalValueException() throws Exception {
        JsonSerializablePennyWise dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INCOME_FILE,
                JsonSerializablePennyWise.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePennyWise.MESSAGE_DUPLICATE_INCOME,
                dataFromFile::toModelType);
    }
}
