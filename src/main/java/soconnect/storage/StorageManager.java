package soconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import soconnect.commons.core.LogsCenter;
import soconnect.commons.exceptions.DataConversionException;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.ReadOnlyUserPrefs;
import soconnect.model.UserPrefs;

/**
 * Manages storage of SoConnect data in local storage, including data of the todo list.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final SoConnectStorage soConnectStorage;
    private final TodoListStorage todoListStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SoConnectStorage}, {@code SoConnectStorage},
     * and {@code UserPrefStorage}.
     */
    public StorageManager(SoConnectStorage soConnectStorage, TodoListStorage todoListStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.soConnectStorage = soConnectStorage;
        this.todoListStorage = todoListStorage;
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

    // ================ SoConnect methods ==============================

    @Override
    public Path getSoConnectFilePath() {
        return soConnectStorage.getSoConnectFilePath();
    }

    @Override
    public Optional<ReadOnlySoConnect> readSoConnect() throws DataConversionException, IOException {
        return readSoConnect(soConnectStorage.getSoConnectFilePath());
    }

    @Override
    public Optional<ReadOnlySoConnect> readSoConnect(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return soConnectStorage.readSoConnect(filePath);
    }

    @Override
    public void saveSoConnect(ReadOnlySoConnect soConnect) throws IOException {
        saveSoConnect(soConnect, soConnectStorage.getSoConnectFilePath());
    }

    @Override
    public void saveSoConnect(ReadOnlySoConnect soConnect, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        soConnectStorage.saveSoConnect(soConnect, filePath);
    }

    // ================ TodoList methods ==============================

    @Override
    public Path getTodoListFilePath() {
        return todoListStorage.getTodoListFilePath();
    }

    @Override
    public Optional<ReadOnlyTodoList> readTodoList() throws DataConversionException, IOException {
        return readTodoList(todoListStorage.getTodoListFilePath());
    }

    @Override
    public Optional<ReadOnlyTodoList> readTodoList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return todoListStorage.readTodoList(filePath);
    }

    @Override
    public void saveTodoList(ReadOnlyTodoList todoList) throws IOException {
        saveTodoList(todoList, todoListStorage.getTodoListFilePath());
    }

    @Override
    public void saveTodoList(ReadOnlyTodoList todoList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        todoListStorage.saveTodoList(todoList, filePath);
    }

}
