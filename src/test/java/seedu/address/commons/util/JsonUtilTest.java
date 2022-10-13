package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.testutil.SerializableTestClass;
import seedu.address.testutil.TestUtil;


/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {

    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize.json");
    private static final Path TEST_PATH = Paths.get(TestUtil.SANDBOX_FOLDER.toString(), "test.json");

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, serializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = JsonUtil
                .deserializeObjectFromJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void jsonUtil_readJsonStringToObjectInstance_correctObject() {
        SerializableTestClass serializableTestClass = null;
        try {
            serializableTestClass = JsonUtil.fromJsonString(
                    SerializableTestClass.JSON_STRING_REPRESENTATION, SerializableTestClass.class);
        } catch (IOException e) {
            fail("Should not throw any exception");
        }
        if (serializableTestClass == null) {
            fail("Json file not read");
        }

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());

    }

    @Test
    public void jsonUtil_writeThenReadObjectToJson_correctObject() {
        File file;
        if ((file = TEST_PATH.toFile()).exists()) {
            file.delete();
        }
        SerializableTestClass s = new SerializableTestClass();
        s.setTestValues();
        try {
            JsonUtil.saveJsonFile(s, TEST_PATH);
        } catch (IOException e) {
            fail("Should not throw any exception");
        }
        Optional<SerializableTestClass> serializableTestClass = null;
        try {
            serializableTestClass = JsonUtil.readJsonFile(TEST_PATH, SerializableTestClass.class);
        } catch (DataConversionException e) {
            fail("" + e + "");
        }
        assertNotNull(serializableTestClass);
        SerializableTestClass test = serializableTestClass.orElse(null);
        assertNotNull(serializableTestClass);
        assertEquals(test.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(test.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(test.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void jsonUtil_readFromOtherFiles() {
        Path path = Paths.get(TestUtil.SANDBOX_FOLDER.toString(), "noFile.txt");
        try {
            JsonUtil.readJsonFile(path, SerializableTestClass.class);
        } catch (DataConversionException e) {
            return;
        }
        fail();
    }

    @Test
    public void jsonUtil_readFromNotExistFiles() {
        Path path = Paths.get(TestUtil.SANDBOX_FOLDER.toString(), "hahahahha");
        Optional<SerializableTestClass> serializableTestClass = null;
        try {
            serializableTestClass = JsonUtil.readJsonFile(path, SerializableTestClass.class);
        } catch (DataConversionException e) {
            fail("should not throw this exception");
        }
        assertEquals(serializableTestClass, Optional.empty());
    }
}
