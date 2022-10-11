package nus.climods.model.module;

import javafx.collections.ObservableList;

public interface ReadOnlyModuleList {

    /**
     * Returns an unmodifiable view of the module list.
     */
    ObservableList<Module> getModules();
}
