package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface TaskBookStorage {
    Path getTaskBookFilePath();

    Optional<ReadOnlyTaskBook> readTaskBook() throws DataConversionException, IOException;

    Optional<ReadOnlyTaskBook> readTaskBook(Path filePath) throws DataConversionException, IOException;

    void saveTaskBook(ReadOnlyTaskBook taskBook) throws IOException;

    void saveTaskBook(ReadOnlyTaskBook taskBook, Path filePath) throws IOException;
}
