package seedu.modquik.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.modquik.commons.exceptions.DataConversionException;
import seedu.modquik.model.ModQuik;
import seedu.modquik.model.ReadOnlyModQuik;

/**
 * Represents a storage for {@link ModQuik}.
 */
public interface ModQuikStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getModQuikFilePath();

    /**
     * Returns ModQuik data as a {@link ReadOnlyModQuik}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModQuik> readModQuik() throws DataConversionException, IOException;

    /**
     * @see #getModQuikFilePath()
     */
    Optional<ReadOnlyModQuik> readModQuik(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyModQuik} to the storage.
     * @param modQuik cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveModQuik(ReadOnlyModQuik modQuik) throws IOException;

    /**
     * @see #saveModQuik(ReadOnlyModQuik)
     */
    void saveModQuik(ReadOnlyModQuik modQuik, Path filePath) throws IOException;

}
