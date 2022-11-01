package jarvis.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import jarvis.commons.exceptions.DataConversionException;
import jarvis.model.ReadOnlyLessonBook;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.ReadOnlyTaskBook;
import jarvis.model.ReadOnlyUserPrefs;
import jarvis.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends StudentBookStorage, TaskBookStorage, LessonBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStudentBookFilePath();

    @Override
    Optional<ReadOnlyStudentBook> readStudentBook() throws DataConversionException, IOException;

    @Override
    void saveStudentBook(ReadOnlyStudentBook studentBook) throws IOException;

    @Override
    Path getTaskBookFilePath();

    @Override
    Optional<ReadOnlyTaskBook> readTaskBook() throws DataConversionException, IOException;

    @Override
    void saveTaskBook(ReadOnlyTaskBook taskBook) throws IOException;

    @Override
    Path getLessonBookFilePath();

    @Override
    Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException, IOException;

    @Override
    void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException;
}
