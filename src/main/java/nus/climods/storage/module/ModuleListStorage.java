package nus.climods.storage.module;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.module.Module;
import nus.climods.model.module.ReadOnlyModuleList;

/**
 * Represents a storage for {@link nus.climods.model.module.ModuleList}.
 */
public interface ModuleListStorage {

    /**
     * Returns the file path of the module list data file.
     */
    Path getModuleListFilePath();

    /**
     * Returns module list data as a ReadOnlyModuleList. Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModuleList> readModuleList(String academicYear) throws DataConversionException;

    Optional<ReadOnlyModuleList> readModuleList(String academicYear, Path filePath) throws DataConversionException;

    void saveModuleList(List<Module> modules) throws IOException;

    void saveModuleList(List<Module> modules, Path filePath) throws IOException;
}
