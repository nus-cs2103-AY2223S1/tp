package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.MyInsuRec;
import seedu.address.testutil.TypicalClients;

public class JsonSerializableMyInsuRecTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMyInsuRecTest");
    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientsMyInsuRec.json");
    private static final Path INVALID_CLIENT_FILE = TEST_DATA_FOLDER.resolve("invalidClientMyInsuRec.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClientMyInsuRec.json");
    private static final Path DUPLICATE_MEETING_FILE = TEST_DATA_FOLDER.resolve("duplicateMeetingMyInsuRec.json");
    private static final Path DUPLICATE_PRODUCT_FILE = TEST_DATA_FOLDER.resolve("duplicateProductMyInsuRec.json");

    @Test
    public void toModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableMyInsuRec dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableMyInsuRec.class).get();
        MyInsuRec myInsuRecFromFile = dataFromFile.toModelType();
        MyInsuRec typicalClientMyInsuRec = TypicalClients.getTypicalMyInsuRec();
        assertEquals(myInsuRecFromFile, typicalClientMyInsuRec);
    }

    @Test
    public void toModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMyInsuRec dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENT_FILE,
                JsonSerializableMyInsuRec.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableMyInsuRec dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
                JsonSerializableMyInsuRec.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMyInsuRec.MESSAGE_DUPLICATE_CLIENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMeetings_throwsIllegalValueException() throws Exception {
        JsonSerializableMyInsuRec dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEETING_FILE,
                JsonSerializableMyInsuRec.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMyInsuRec.MESSAGE_DUPLICATE_MEETING,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProducts_throwsIllegalValueException() throws Exception {
        JsonSerializableMyInsuRec dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PRODUCT_FILE,
                JsonSerializableMyInsuRec.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMyInsuRec.MESSAGE_DUPLICATE_PRODUCT,
                dataFromFile::toModelType);
    }

}
