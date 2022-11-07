package nus.climods.model.module;

import java.util.Optional;

import javafx.collections.ObservableList;



/**
 * Unmodifiable view of a module list
 */
public interface ReadOnlyModuleList {

    /**
     * Returns an unmodifiable view of the module list.
     */
    ObservableList<Module> getModules();

    Optional<Module> getListModule(String moduleCode);

    boolean isEmpty();

    boolean hasModule(String moduleCode);

    /**
     * Get reference to module in module list
     *
     * @param moduleCode Module code to check for
     * @return Optional of corresponding Module in list if it exists, else empty Optional
     */
    Optional<Module> getModule(String moduleCode);
}
