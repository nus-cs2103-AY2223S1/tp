package seedu.modquik.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modquik.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.modquik.commons.exceptions.IllegalValueException;
import seedu.modquik.commons.util.JsonUtil;
import seedu.modquik.model.ModQuik;
import seedu.modquik.testutil.TypicalPersons;

public class JsonSerializableModQuikTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableModQuik dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableModQuik.class).get();
        ModQuik modQuikFromFile = dataFromFile.toModelType();
        ModQuik typicalPersonsModQuik = TypicalPersons.getTypicalModQuik();
        assertEquals(modQuikFromFile, typicalPersonsModQuik);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableModQuik dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableModQuik.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableModQuik dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableModQuik.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableModQuik.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
