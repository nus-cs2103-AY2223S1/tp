package hobbylist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import hobbylist.commons.exceptions.IllegalValueException;
import hobbylist.commons.util.JsonUtil;
import hobbylist.model.HobbyList;
import hobbylist.testutil.Assert;
import hobbylist.testutil.TypicalActivities;

public class JsonSerializableDescriptionBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableHobbyListTest");
    private static final Path TYPICAL_ACTIVITIES_FILE = TEST_DATA_FOLDER.resolve("typicalActivityHobbyList.json");
    private static final Path INVALID_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve("invalidActivityHobbyList.json");
    private static final Path DUPLICATE_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve("duplicateActivityHobbyList.json");

    @Test
    public void toModelType_typicalActivityFile_success() throws Exception {
        JsonSerializableHobbyList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ACTIVITIES_FILE,
                JsonSerializableHobbyList.class).get();
        HobbyList hobbyListFromFile = dataFromFile.toModelType();
        HobbyList typicalActivitiesHobbyList = TypicalActivities.getTypicalHobbyList();
        assertEquals(hobbyListFromFile, typicalActivitiesHobbyList);
    }

    @Test
    public void toModelType_invalidActivityFile_throwsIllegalValueException() throws Exception {
        JsonSerializableHobbyList dataFromFile = JsonUtil.readJsonFile(INVALID_ACTIVITY_FILE,
                JsonSerializableHobbyList.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateActivity_throwsIllegalValueException() throws Exception {
        JsonSerializableHobbyList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ACTIVITY_FILE,
                JsonSerializableHobbyList.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableHobbyList.MESSAGE_DUPLICATE_ACTIVITY,
                dataFromFile::toModelType);
    }

}
