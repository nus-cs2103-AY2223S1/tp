package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTeachersPet;

/**
 * Represents a storage for {@link seedu.address.model.TeachersPet}.
 */
public interface TeachersPetStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTeachersPetFilePath();

    /**
     * Returns TeachersPet data as a {@link ReadOnlyTeachersPet}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTeachersPet> readTeachersPet() throws DataConversionException, IOException;

    /**
     * @see #getTeachersPetFilePath()
     */
    Optional<ReadOnlyTeachersPet> readTeachersPet(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTeachersPet} to the storage.
     * @param teachersPet cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTeachersPet(ReadOnlyTeachersPet teachersPet) throws IOException;

    /**
     * @see #saveTeachersPet(ReadOnlyTeachersPet)
     */
    void saveTeachersPet(ReadOnlyTeachersPet teachersPet, Path filePath) throws IOException;

}
