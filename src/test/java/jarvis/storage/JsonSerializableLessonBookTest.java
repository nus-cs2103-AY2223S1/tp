package jarvis.storage;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.commons.util.JsonUtil;
import jarvis.model.LessonBook;
import jarvis.model.exceptions.LessonClashException;
import jarvis.testutil.TypicalLessons;

public class JsonSerializableLessonBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLessonBookTest");
    private static final Path TYPICAL_LESSONS_FILE = TEST_DATA_FOLDER.resolve("typicalLessonsLessonBook.json");
    private static final Path INVALID_LESSON_FILE = TEST_DATA_FOLDER.resolve("invalidLessonLessonBook.json");
    private static final Path CLASHING_LESSON_FILE = TEST_DATA_FOLDER.resolve("clashingLessonLessonBook.json");

    @Test
    public void toModelType_typicalLessonsFile_success() throws Exception {
        JsonSerializableLessonBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_LESSONS_FILE,
                JsonSerializableLessonBook.class).get();
        LessonBook LessonBookFromFile = dataFromFile.toModelType();
        LessonBook typicalPersonsLessonBook = TypicalLessons.getTypicalLessonBook();
        assertEquals(LessonBookFromFile, typicalPersonsLessonBook);
    }

    @Test
    public void toModelType_invalidLessonFile_throwsIllegalArgumentException() throws Exception {
        JsonSerializableLessonBook dataFromFile = JsonUtil.readJsonFile(INVALID_LESSON_FILE,
                JsonSerializableLessonBook.class).get();
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_clashingLessons_throwsLessonClashException() throws Exception {
        JsonSerializableLessonBook dataFromFile = JsonUtil.readJsonFile(CLASHING_LESSON_FILE,
                JsonSerializableLessonBook.class).get();
        assertThrows(LessonClashException.class, LessonClashException.MESSAGE,
                dataFromFile::toModelType);
    }

}
