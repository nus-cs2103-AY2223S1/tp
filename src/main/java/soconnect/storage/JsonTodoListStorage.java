package soconnect.storage;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import soconnect.commons.core.LogsCenter;
import soconnect.commons.exceptions.DataConversionException;
import soconnect.commons.exceptions.IllegalValueException;
import soconnect.commons.util.FileUtil;
import soconnect.commons.util.JsonUtil;
import soconnect.model.ReadOnlyTodoList;

/**
 * A class to access TodoList data stored as a json file on the hard disk.
 */
public class JsonTodoListStorage implements TodoListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTodoListStorage.class);

    private final Path filePath;

    public JsonTodoListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTodoListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTodoList> readTodoList() throws DataConversionException {
        return readTodoList(filePath);
    }

    /**
     * Similar to {@link #readTodoList()}.
     *
     * @param filePath Location of the data. Cannot be null.
     * @throws DataConversionException If the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyTodoList> readTodoList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTodoList> jsonTodoList = JsonUtil.readJsonFile(
            filePath, JsonSerializableTodoList.class);
        if (!jsonTodoList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTodoList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTodoList(ReadOnlyTodoList todoList) throws IOException {
        saveTodoList(todoList, filePath);
    }

    /**
     * Similar to {@link #saveTodoList(ReadOnlyTodoList)}.
     *
     * @param filePath Location of the data. Cannot be null.
     */
    @Override
    public void saveTodoList(ReadOnlyTodoList todoList, Path filePath) throws IOException {
        requireAllNonNull(todoList, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTodoList(todoList), filePath);
    }

}
