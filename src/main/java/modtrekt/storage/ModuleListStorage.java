package modtrekt.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import modtrekt.commons.exceptions.DataConversionException;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.module.Module;

/**
 * Represents a storage for {@link Module}.
 */
public interface ModuleListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getModuleListFilePath();

    /**
     * Returns ModuleList data as a {@link ReadOnlyModuleList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException;

    /**
     * @see #getModuleListFilePath()
     */
    Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyModuleList} to the storage.
     * @param moduleList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveModuleList(ReadOnlyModuleList moduleList) throws IOException;

    /**
     * @see #saveModuleList(ReadOnlyModuleList)
     */
    void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException;

}
