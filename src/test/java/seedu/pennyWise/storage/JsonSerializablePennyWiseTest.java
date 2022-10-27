package seedu.pennyWise.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pennyWise.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.pennyWise.commons.exceptions.IllegalValueException;
import seedu.pennyWise.commons.util.JsonUtil;
import seedu.pennyWise.model.PennyWise;
import seedu.pennyWise.testutil.TypicalEntry;
public class JsonSerializablePennyWiseTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePennyWiseTest");
    private static final Path TYPICAL_ENTRIES_FILE = TEST_DATA_FOLDER.resolve("typicalEntriesPennyWise.json");
    private static final Path INVALID_ENTRY_FILE = TEST_DATA_FOLDER.resolve("invalidEntryPennyWise.json");
    private static final Path DUPLICATE_ENTRY_FILE = TEST_DATA_FOLDER.resolve("duplicateEntryPennyWise.json");

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
    public void toModelType_duplicateEntries_throwsIllegalValueException() throws Exception {
        JsonSerializablePennyWise dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ENTRY_FILE,
                JsonSerializablePennyWise.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePennyWise.MESSAGE_DUPLICATE_ENTRY,
                dataFromFile::toModelType);
    }
}
