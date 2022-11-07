package soconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static soconnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import soconnect.commons.exceptions.IllegalValueException;
import soconnect.commons.util.JsonUtil;
import soconnect.model.SoConnect;
import soconnect.testutil.TypicalPersons;

public class JsonSerializableSoConnectTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSoConnectTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsSoConnect.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonSoConnect.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonSoConnect.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableSoConnect dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableSoConnect.class).get();
        SoConnect soConnectFromFile = dataFromFile.toModelType();
        SoConnect typicalPersonsSoConnect = TypicalPersons.getTypicalSoConnect();
        assertEquals(soConnectFromFile, typicalPersonsSoConnect);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSoConnect dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableSoConnect.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableSoConnect dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableSoConnect.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSoConnect.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
