package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.NuScheduler;
import seedu.address.model.ReadOnlyNuScheduler;

/**
 * Represents a storage for {@link NuScheduler}.
 */
public interface NuSchedulerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getNuSchedulerFilePath();

    /**
     * Returns NuScheduler data as a {@link ReadOnlyNuScheduler}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyNuScheduler> readNuScheduler() throws DataConversionException, IOException;

    /**
     * @see #getNuSchedulerFilePath()
     */
    Optional<ReadOnlyNuScheduler> readNuScheduler(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyNuScheduler} to the storage.
     * @param nuScheduler cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNuScheduler(ReadOnlyNuScheduler nuScheduler) throws IOException;

    /**
     * @see #saveNuScheduler(ReadOnlyNuScheduler)
     */
    void saveNuScheduler(ReadOnlyNuScheduler nuScheduler, Path filePath) throws IOException;

}
