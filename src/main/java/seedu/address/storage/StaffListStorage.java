package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyStaffList;

/**
 * Represents a storage for {@link seedu.address.model.StaffList}.
 */
public interface StaffListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStaffListFilePath();

    /**
     * Returns StaffList data as a {@link ReadOnlyStaffList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyStaffList> readStaffList() throws DataConversionException, IOException;

    /**
     * @see #getStaffListFilePath()
     */
    Optional<ReadOnlyStaffList> readStaffList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStaffList} to the storage.
     * @param staffList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStaffList(ReadOnlyStaffList staffList) throws IOException;

    /**
     * @see #saveStaffList(ReadOnlyStaffList)
     */
    void saveStaffList(ReadOnlyStaffList staffList, Path filePath) throws IOException;

}
