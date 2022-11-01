package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.NuScheduler;
import seedu.address.testutil.TypicalNuScheduler;

public class JsonSerializableNuSchedulerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableNuSchedulerTest");
    private static final Path TYPICAL_PROFILES_FILE = TEST_DATA_FOLDER.resolve("typicalProfilesNuScheduler.json");
    private static final Path INVALID_PROFILE_FILE = TEST_DATA_FOLDER.resolve("invalidNameNuScheduler.json");
    private static final Path SIMILAR_EMAIL_FILE = TEST_DATA_FOLDER.resolve("similarEmailNuScheduler.json");
    private static final Path SIMILAR_PHONE_FILE = TEST_DATA_FOLDER.resolve("similarPhoneNuScheduler.json");
    private static final Path SIMILAR_TELEGRAM_FILE = TEST_DATA_FOLDER.resolve("similarTelegramNuScheduler.json");

    @Test
    public void toModelType_typicalProfilesFile_success() throws Exception {
        JsonSerializableNuScheduler dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROFILES_FILE,
                JsonSerializableNuScheduler.class).get();
        NuScheduler nuSchedulerFromFile = dataFromFile.toModelType();
        NuScheduler typicalProfilesNuScheduler = TypicalNuScheduler.getTypicalNuScheduler();
        assertEquals(nuSchedulerFromFile, typicalProfilesNuScheduler);
    }

    @Test
    public void toModelType_invalidProfileFile_throwsIllegalValueException() throws Exception {
        JsonSerializableNuScheduler dataFromFile = JsonUtil.readJsonFile(INVALID_PROFILE_FILE,
                JsonSerializableNuScheduler.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_similarEmail_throwsIllegalValueException() throws Exception {
        JsonSerializableNuScheduler dataFromFile = JsonUtil.readJsonFile(SIMILAR_EMAIL_FILE,
                JsonSerializableNuScheduler.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableNuScheduler.MESSAGE_SIMILAR_EMAIL,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_similarPhone_throwsIllegalValueException() throws Exception {
        JsonSerializableNuScheduler dataFromFile = JsonUtil.readJsonFile(SIMILAR_PHONE_FILE,
                JsonSerializableNuScheduler.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableNuScheduler.MESSAGE_SIMILAR_PHONE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_similarTelegram_throwsIllegalValueException() throws Exception {
        JsonSerializableNuScheduler dataFromFile = JsonUtil.readJsonFile(SIMILAR_TELEGRAM_FILE,
                JsonSerializableNuScheduler.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableNuScheduler.MESSAGE_SIMILAR_TELEGRAM,
                dataFromFile::toModelType);
    }

}
