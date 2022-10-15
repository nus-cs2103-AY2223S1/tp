package soconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import soconnect.commons.exceptions.DataConversionException;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.ReadOnlyUserPrefs;
import soconnect.model.UserPrefs;

/**
 * API of the Storage component.
 */
public interface Storage extends SoConnectStorage, TodoListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getSoConnectFilePath();

    @Override
    Optional<ReadOnlySoConnect> readSoConnect() throws DataConversionException, IOException;

    @Override
    void saveSoConnect(ReadOnlySoConnect soConnect) throws IOException;

    @Override
    Path getTodoListFilePath();

    @Override
    Optional<ReadOnlyTodoList> readTodoList() throws DataConversionException, IOException;

    @Override
    void saveTodoList(ReadOnlyTodoList todoList) throws IOException;

}
