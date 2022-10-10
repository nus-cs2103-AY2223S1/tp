package seedu.rc4hdb.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.commons.util.JsonUtil;
import seedu.rc4hdb.model.ResidentBook;

public class JsonSerializableResidentBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableResidentBookTest");
    private static final Path TYPICAL_RESIDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalResidentsResidentBook.json");
    private static final Path INVALID_RESIDENT_FILE = TEST_DATA_FOLDER.resolve("invalidResidentResidentBook.json");
    private static final Path DUPLICATE_RESIDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateResidentResidentBook.json");

    @Test
    public void toModelType_typicalResidentsFile_success() throws Exception {
        JsonSerializableResidentBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_RESIDENTS_FILE,
                JsonSerializableResidentBook.class).get();
        ResidentBook residentBookFromFile = dataFromFile.toModelType();
        ResidentBook typicalResidentsResidentBook = getTypicalResidentBook();
        assertEquals(residentBookFromFile, typicalResidentsResidentBook);
    }

    @Test
    public void toModelType_invalidResidentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableResidentBook dataFromFile = JsonUtil.readJsonFile(INVALID_RESIDENT_FILE,
                JsonSerializableResidentBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateResidents_throwsIllegalValueException() throws Exception {
        JsonSerializableResidentBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_RESIDENT_FILE,
                JsonSerializableResidentBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableResidentBook.MESSAGE_DUPLICATE_RESIDENT,
                dataFromFile::toModelType);
    }

}
