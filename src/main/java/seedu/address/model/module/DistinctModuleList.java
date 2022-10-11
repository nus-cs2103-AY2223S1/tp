package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

/**
 * This class represents a list which contains Module objects which are distinct from
 * each other. Module Objects are distinct from each other when they have different module
 * codes.
 */
public class DistinctModuleList implements Iterable<Module> {

    private final ObservableList<Module> moduleList = FXCollections.observableArrayList();
    private final ObservableList<Module> unmodifiableModuleList = FXCollections
            .unmodifiableObservableList(moduleList);

    /**
     * Checks whether the moduleList contains a Module with the same module code.
     *
     * @param module The Module object being compared against.
     * @return true if the Module object has the same module code as one of the
     *         Module objects in the list; else return false
     */
    public boolean containsModule(Module module) {
        requireNonNull(module);
        return moduleList.stream().anyMatch(module::isSameModuleCode);
    }

    /**
     * Adds modules to the moduleList.
     *
     * @param moduleAdded The module which is being added.
     */
    public void addModule(Module moduleAdded) {
        requireNonNull(moduleAdded);
        if (containsModule(moduleAdded)) {
            throw new DuplicateModuleException();
        }
        moduleList.add(moduleAdded);
    }

    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        moduleList.setAll(modules);
    }

    /**
     * Removes the equivalent module from the module list.
     * The module must exist in the list.
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!moduleList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    @Override
    public Iterator<Module> iterator() {
        return moduleList.iterator();
    }

    @Override
    public int hashCode() {
        return moduleList.hashCode();
    }

    @Override
    public boolean equals(Object otherMod) {
        return otherMod == this
                || (otherMod instanceof DistinctModuleList
                && moduleList.equals(((DistinctModuleList) otherMod).moduleList));
    }

    public ObservableList<Module> getUnmodifiableModuleList() {
        return unmodifiableModuleList;
    }
}
