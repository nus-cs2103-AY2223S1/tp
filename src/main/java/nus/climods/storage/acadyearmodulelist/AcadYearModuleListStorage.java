package nus.climods.storage.acadyearmodulelist;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.openapitools.client.model.ModuleCondensed;

import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.AcadYearModuleList;
import nus.climods.model.ReadOnlyAcadYearModuleList;



/**
 * Represents a storage for {@link AcadYearModuleList}.
 */
public interface AcadYearModuleListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAcadYearModuleListFilePath();

    /**
     * Returns user module list data as a {@link ReadOnlyAcadYearModuleList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAcadYearModuleList> readAcadYearModuleList() throws DataConversionException, IOException;

    /**
     * @see #getAcadYearModuleListFilePath()
     */
    Optional<ReadOnlyAcadYearModuleList> readAcadYearModuleList(Path filePath)
            throws DataConversionException, IOException;

    void saveAcadYearModuleList(List<ModuleCondensed> modules) throws IOException;

    void saveAcadYearModuleList(List<ModuleCondensed> modules, Path filePath) throws IOException;

}
