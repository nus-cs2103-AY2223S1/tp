package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTruthTable;

/**
 * Represents a storage for {@link seedu.address.model.TruthTable}.
 */
public interface TruthTableStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTruthTableFilePath();

    /**
     * Returns TruthTable data as a {@link ReadOnlyTruthTable}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTruthTable> readTruthTable() throws DataConversionException, IOException;

    /**
     * @see #getTruthTableFilePath()
     */
    Optional<ReadOnlyTruthTable> readTruthTable(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTruthTable} to the storage.
     *
     * @param truthTable cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTruthTable(ReadOnlyTruthTable truthTable) throws IOException;

    /**
     * @see #saveTruthTable(ReadOnlyTruthTable)
     */
    void saveTruthTable(ReadOnlyTruthTable truthTable, Path filePath) throws IOException;

}
