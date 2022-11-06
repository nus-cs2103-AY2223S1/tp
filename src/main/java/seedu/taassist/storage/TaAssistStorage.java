package seedu.taassist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.taassist.commons.exceptions.DataConversionException;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;

/**
 * Represents a storage for {@link TaAssist}.
 */
public interface TaAssistStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaAssistFilePath();

    /**
     * Returns TaAssist data as a {@link ReadOnlyTaAssist}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException If the data in storage is not in the expected format.
     * @throws IOException If there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaAssist> readTaAssist() throws DataConversionException, IOException;

    /**
     * @see #getTaAssistFilePath()
     */
    Optional<ReadOnlyTaAssist> readTaAssist(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaAssist} to the storage.
     *
     * @param taAssist Cannot be null.
     * @throws IOException If there was any problem writing to the file.
     */
    void saveTaAssist(ReadOnlyTaAssist taAssist) throws IOException;

    /**
     * @see #saveTaAssist(ReadOnlyTaAssist)
     */
    void saveTaAssist(ReadOnlyTaAssist taAssist, Path filePath) throws IOException;

}
