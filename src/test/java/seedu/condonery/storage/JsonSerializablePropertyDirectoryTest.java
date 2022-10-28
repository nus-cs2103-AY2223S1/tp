package seedu.condonery.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.condonery.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.exceptions.IllegalValueException;
import seedu.condonery.commons.util.JsonUtil;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.testutil.TypicalProperties;

public class JsonSerializablePropertyDirectoryTest {

    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonSerializablePropertyDirectoryTest");
    private static final Path TYPICAL_PROPERTY_FILE = TEST_DATA_FOLDER.resolve("typicalPropertyDirectory.json");
    private static final Path INVALID_PROPERTY_FILE = TEST_DATA_FOLDER.resolve("invalidPropertyDirectory.json");
    private static final Path DUPLICATE_PROPERTY_FILE = TEST_DATA_FOLDER.resolve("duplicatePropertyDirectory.json");

    @Test
    public void toModelType_typicalPropertyFile_success() throws Exception {
        JsonSerializablePropertyDirectory dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROPERTY_FILE,
            JsonSerializablePropertyDirectory.class).get();
        PropertyDirectory propertyDirectoryFromFile = dataFromFile.toModelType();
        PropertyDirectory typicalPropertyDirectory = TypicalProperties.getTypicalPropertyDirectory();
        assertEquals(propertyDirectoryFromFile, typicalPropertyDirectory);
    }

    @Test
    public void toModelType_invalidPropertyFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePropertyDirectory dataFromFile = JsonUtil.readJsonFile(INVALID_PROPERTY_FILE,
            JsonSerializablePropertyDirectory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProperties_throwsIllegalValueException() throws Exception {
        JsonSerializablePropertyDirectory dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROPERTY_FILE,
            JsonSerializablePropertyDirectory.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePropertyDirectory.MESSAGE_DUPLICATE_PROPERTY,
            dataFromFile::toModelType);
    }

}
