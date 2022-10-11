package nus.climods.model.module;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a module list
 */
public interface ReadOnlyModuleList {

    /**
     * Returns an unmodifiable view of the module list.
     */
    ObservableList<Module> getModules();
}
