package seedu.boba.storage;

import org.junit.jupiter.api.Test;
import seedu.boba.commons.exceptions.IllegalValueException;
import seedu.boba.commons.util.JsonUtil;
import seedu.boba.model.BobaBot;
import seedu.boba.testutil.TypicalCustomers;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.boba.testutil.Assert.assertThrows;

public class JsonSerializableBobaBotTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableBobaBotTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalCustomersBobaBot.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidCustomerBobaBot.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateCustomerBobaBot.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableBobaBot dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableBobaBot.class).get();
        BobaBot bobaBotFromFile = dataFromFile.toModelType();
        BobaBot typicalPersonsBobaBot = TypicalCustomers.getTypicalBobaBot();
        assertEquals(bobaBotFromFile, typicalPersonsBobaBot);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableBobaBot dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableBobaBot.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableBobaBot dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableBobaBot.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableBobaBot.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
