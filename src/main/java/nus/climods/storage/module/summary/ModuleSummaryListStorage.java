package nus.climods.storage.module.summary;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.openapitools.client.model.ModuleCondensed;

import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.module.ModuleSummaryList;
import nus.climods.model.module.ReadOnlyModuleSummaryList;


/**
 * Represents a storage for {@link ModuleSummaryList}.
 */
public interface ModuleSummaryListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getModuleSummaryListFilePath();

    /**
     * Returns user module list data as a {@link ReadOnlyModuleSummaryList}. Returns {@code Optional.empty()} if storage
     * file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModuleSummaryList> readModuleSummaryList() throws DataConversionException, IOException;

    /**
     * @see #getModuleSummaryListFilePath()
     */
    Optional<ReadOnlyModuleSummaryList> readModuleSummaryList(Path filePath)
            throws DataConversionException, IOException;

    void saveModuleSummaryList(List<ModuleCondensed> modules) throws IOException;

    void saveModuleSummaryList(List<ModuleCondensed> modules, Path filePath) throws IOException;

}
