package nus.climods.storage.module.user;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.storage.exceptions.StorageException;

/**
 * Represents a storage for {@link nus.climods.model.module.UniqueUserModuleList}
 */
public interface UserModuleListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUserModuleListFilePath();

    /**
     * Returns user module list data as a {@link nus.climods.model.module.UniqueUserModuleList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     */
    Optional<UniqueUserModuleList> readUserModuleList() throws DataConversionException;

    /**
     * @see #getUserModuleListFilePath()
     */
    Optional<UniqueUserModuleList> readUserModuleList(Path filePath) throws DataConversionException;

    /**
     * Saves the given {@link UniqueUserModuleList} to the storage.
     *
     * @param userModuleList cannot be null.
     */
    void saveUserModuleList(UniqueUserModuleList userModuleList) throws StorageException;

    /**
     * @see #saveUserModuleList(UniqueUserModuleList)
     */
    void saveUserModuleList(UniqueUserModuleList userModuleList, Path filePath) throws IOException;
}
