package nus.climods.model.module;

import java.util.List;

import org.openapitools.client.model.ModuleCondensed;

/**
 * Unmodifiable view of Acad Year Module List received via API call.
 */
public interface ReadOnlyModuleSummaryList {

    List<ModuleCondensed> getModuleList();
}
