package soconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import soconnect.commons.exceptions.DataConversionException;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.tag.Tag;

/**
 * Represents a storage for {@link soconnect.model.TodoList}.
 */
public interface TodoListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTodoListFilePath();

    /**
     * Returns {@code TodoList} data as a {@link ReadOnlyTodoList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param readOnlySoConnect The list of existing {@code Tag}s in {@code SoConnect}.
     * @throws DataConversionException If the data in storage is not in the expected format.
     * @throws IOException If there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTodoList> readTodoList(ReadOnlySoConnect readOnlySoConnect) throws DataConversionException, IOException;

    /**
     * Returns {@code TodoList} data as a {@link ReadOnlyTodoList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param readOnlySoConnect The list of existing {@code Tag}s in {@code SoConnect}.
     * @param filePath The path of the {@code TodoList} data file.
     * @throws DataConversionException If the data in storage is not in the expected format.
     * @throws IOException If there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTodoList> readTodoList(ReadOnlySoConnect readOnlySoConnect, Path filePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyTodoList} to the storage.
     *
     * @param todoList Cannot be null.
     * @throws IOException If there was any problem writing to the file.
     */
    void saveTodoList(ReadOnlyTodoList todoList) throws IOException;

    /**
     * @see #saveTodoList(ReadOnlyTodoList)
     */
    void saveTodoList(ReadOnlyTodoList todoList, Path filePath) throws IOException;

}
