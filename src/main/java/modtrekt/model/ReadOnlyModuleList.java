package modtrekt.model;

import javafx.collections.ObservableList;
import modtrekt.model.module.Module;

/**
 * Unmodifiable view of a module list
 */
public interface ReadOnlyModuleList {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Module> getModuleList();


}
