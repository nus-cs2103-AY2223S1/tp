package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import org.openapitools.client.model.ModuleCondensed;

/**
 * All the module codes to allow for searching for modules.
 */
public class AcadYearModuleList implements ReadOnlyAcadYearModuleList {

    private final List<ModuleCondensed> condensedModules;

    public AcadYearModuleList() {
        this.condensedModules = new ArrayList<>();
    }

    public AcadYearModuleList(List<ModuleCondensed> acadYearModuleList) {
        this.condensedModules = acadYearModuleList;
    }

    @Override
    public String toString() {
        return condensedModules.size() + " modules";
    }

    public List<ModuleCondensed> getModuleList() {
        return this.condensedModules;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcadYearModuleList // instanceof handles nulls
                && this.condensedModules.equals(((AcadYearModuleList) other).condensedModules));
    }

    @Override
    public int hashCode() {
        return this.condensedModules.hashCode();
    }
}

