package jarvis.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import jarvis.commons.exceptions.DataConversionException;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.ReadOnlyTaskBook;
import jarvis.model.ReadOnlyUserPrefs;
import jarvis.model.UserPrefs;
import jarvis.storage.student.StudentBookStorage;
import jarvis.storage.task.TaskBookStorage;

/**
 * API of the Storage component
 */
public interface Storage extends StudentBookStorage, TaskBookStorage, UserPrefsStorage {

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
}
