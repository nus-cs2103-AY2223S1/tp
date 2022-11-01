package tuthub.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tuthub.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import tuthub.commons.exceptions.IllegalValueException;
import tuthub.commons.util.JsonUtil;
import tuthub.model.Tuthub;
import tuthub.testutil.TypicalTutors;

public class JsonSerializableTuthubTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTuthubTest");
    private static final Path TYPICAL_TUTORS_FILE = TEST_DATA_FOLDER.resolve("typicalTutorsTuthub.json");
    private static final Path INVALID_TUTOR_FILE = TEST_DATA_FOLDER.resolve("invalidTutorTuthub.json");
    private static final Path DUPLICATE_TUTOR_FILE = TEST_DATA_FOLDER.resolve("duplicateTutorTuthub.json");

    @Test
    public void toModelType_typicalTutorsFile_success() throws Exception {
        JsonSerializableTuthub dataFromFile = JsonUtil.readJsonFile(TYPICAL_TUTORS_FILE,
                JsonSerializableTuthub.class).get();
        Tuthub tuthubFromFile = dataFromFile.toModelType();
        Tuthub typicalTutorsTuthub = TypicalTutors.getTypicalTuthub();
        for (int i = 0; i < tuthubFromFile.getTutorList().size(); i++) {
            System.out.println(tuthubFromFile.getTutorList().get(i));
            System.out.println(typicalTutorsTuthub.getTutorList().get(i));
        }
        assertEquals(tuthubFromFile, typicalTutorsTuthub);
    }

    @Test
    public void toModelType_invalidTutorFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTuthub dataFromFile = JsonUtil.readJsonFile(INVALID_TUTOR_FILE,
                JsonSerializableTuthub.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTutors_throwsIllegalValueException() throws Exception {
        JsonSerializableTuthub dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TUTOR_FILE,
                JsonSerializableTuthub.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTuthub.MESSAGE_DUPLICATE_TUTOR,
                dataFromFile::toModelType);
    }

}
