package seedu.taassist.model.moduleclass;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.model.moduleclass.exceptions.DuplicateModuleClassException;
import seedu.taassist.model.moduleclass.exceptions.ModuleClassNotFoundException;

/**
 * A list of classes that enforces uniqueness between its elements and does not allow nulls.
 * A class is considered unique by comparing using {@code ModuleClass#isSameModuleClass(ModuleClass)}. As such,
 * adding of classes uses ModuleClass#isSameModuleClass(ModuleClass) for equality so as to ensure that the class being
 * added is unique in terms of identity in the UniqueModuleClassList. However, the removal of a class
 * uses ModuleClass#equals(Object) so as to ensure that the class with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see ModuleClass#isSameModuleClass(ModuleClass)
 */
public class UniqueModuleClassList implements Iterable<ModuleClass> {

    private final ObservableList<ModuleClass> internalList = FXCollections.observableArrayList();
    private final ObservableList<ModuleClass> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent class as the given argument.
     */
    public boolean contains(ModuleClass toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModuleClass);
    }

    /**
     * Adds a class to the list.
     * The class must not already exist in the list.
     */
    public void add(ModuleClass toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent class from the list.
     * The class must exist in the list.
     */
    public void remove(ModuleClass toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleClassNotFoundException();
        }
    }

    public void setModuleClasses(seedu.taassist.model.moduleclass.UniqueModuleClassList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code classes}.
     * {@code classes} must not contain duplicate classes.
     */
    public void setModuleClasses(List<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        if (!moduleClassesAreUnique(moduleClasses)) {
            throw new DuplicateModuleClassException();
        }

        internalList.setAll(moduleClasses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ModuleClass> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ModuleClass> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.taassist.model.moduleclass.UniqueModuleClassList // instanceof handles nulls
                && internalList.equals(((seedu.taassist.model.moduleclass.UniqueModuleClassList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code moduleClasses} contains only unique classes.
     */
    private boolean moduleClassesAreUnique(List<ModuleClass> moduleClasses) {
        for (int i = 0; i < moduleClasses.size() - 1; i++) {
            for (int j = i + 1; j < moduleClasses.size(); j++) {
                if (moduleClasses.get(i).isSameModuleClass(moduleClasses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

