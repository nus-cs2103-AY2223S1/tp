package hobbylist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import hobbylist.commons.exceptions.DataConversionException;
import hobbylist.model.HobbyList;
import hobbylist.model.ReadOnlyHobbyList;

/**
 * Represents a storage for {@link HobbyList}.
 */
public interface HobbyListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHobbyListFilePath();

    /**
     * Returns HobbyList data as a {@link ReadOnlyHobbyList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHobbyList> readHobbyList() throws DataConversionException, IOException;

    /**
     * @see #getHobbyListFilePath()
     */
    Optional<ReadOnlyHobbyList> readHobbyList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHobbyList} to the storage.
     * @param hobbyList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHobbyList(ReadOnlyHobbyList hobbyList) throws IOException;

    /**
     * @see #saveHobbyList(ReadOnlyHobbyList)
     */
    void saveHobbyList(ReadOnlyHobbyList hobbyList, Path filePath) throws IOException;

}
