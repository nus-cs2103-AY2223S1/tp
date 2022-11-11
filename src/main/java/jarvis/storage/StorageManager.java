package jarvis.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import jarvis.commons.core.LogsCenter;
import jarvis.commons.exceptions.DataConversionException;
import jarvis.model.ReadOnlyLessonBook;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.ReadOnlyTaskBook;
import jarvis.model.ReadOnlyUserPrefs;
import jarvis.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private StudentBookStorage studentBookStorage;
    private TaskBookStorage taskBookStorage;
    private LessonBookStorage lessonBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code StudentBookStorage}, {@code TaskBookStorage}
     * {@code LessonBookStorage}, and {@code UserPrefStorage}.
     */
    public StorageManager(StudentBookStorage studentBookStorage, TaskBookStorage taskBookStorage,
                          LessonBookStorage lessonBookStorage, UserPrefsStorage userPrefsStorage) {
        this.studentBookStorage = studentBookStorage;
        this.taskBookStorage = taskBookStorage;
        this.lessonBookStorage = lessonBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ StudentBook methods ==============================

    @Override
    public Path getStudentBookFilePath() {
        return studentBookStorage.getStudentBookFilePath();
    }

    @Override
    public Optional<ReadOnlyStudentBook> readStudentBook() throws DataConversionException, IOException {
        return readStudentBook(studentBookStorage.getStudentBookFilePath());
    }

    @Override
    public Optional<ReadOnlyStudentBook> readStudentBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return studentBookStorage.readStudentBook(filePath);
    }

    @Override
    public void saveStudentBook(ReadOnlyStudentBook studentBook) throws IOException {
        saveStudentBook(studentBook, studentBookStorage.getStudentBookFilePath());
    }

    @Override
    public void saveStudentBook(ReadOnlyStudentBook studentBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        studentBookStorage.saveStudentBook(studentBook, filePath);
    }

    // ================ TaskBook methods ==============================

    @Override
    public Path getTaskBookFilePath() {
        return taskBookStorage.getTaskBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskBook() throws DataConversionException, IOException {
        return readTaskBook(taskBookStorage.getTaskBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskBookStorage.readTaskBook(filePath);
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskBook) throws IOException {
        saveTaskBook(taskBook, taskBookStorage.getTaskBookFilePath());
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskBookStorage.saveTaskBook(taskBook, filePath);
    }

    // ================ LessonBook methods ==============================

    @Override
    public Path getLessonBookFilePath() {
        return lessonBookStorage.getLessonBookFilePath();
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException, IOException {
        return readLessonBook(lessonBookStorage.getLessonBookFilePath());
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return lessonBookStorage.readLessonBook(filePath);
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException {
        saveLessonBook(lessonBook, lessonBookStorage.getLessonBookFilePath());
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        lessonBookStorage.saveLessonBook(lessonBook, filePath);
    }
}
