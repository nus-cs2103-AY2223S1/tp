package nus.climods.model.module;

import static java.util.Objects.requireNonNull;
import static nus.climods.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nus.climods.model.module.exceptions.DuplicateUserModuleException;
import nus.climods.model.module.exceptions.UserModuleNotFoundException;

/**
 * A list of modules that enforces uniqueness between its elements and does not allow nulls. A module is considered
 * unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of modules uses
 * Person#isSamePerson(Person) for equality so as to ensure that the module being added or updated is unique in terms of
 * identity in the UniquePersonList. However, the removal of a module uses Person#equals(Object) so as to ensure that
 * the module with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see UserModule#isSameUserModule(UserModule)
 */
public class UniqueUserModuleList implements Iterable<UserModule> {

    private final ObservableList<UserModule> internalList = FXCollections.observableArrayList();
    private final ObservableList<UserModule> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent module as the given argument.
     */
    public boolean contains(UserModule toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameUserModule);
    }

    /**
     * Adds a module to the list. The module must not already exist in the list.
     */
    public void add(UserModule toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateUserModuleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the module {@code target} in the list with {@code editedUserModule}. {@code target} must exist in the
     * list.
     * The module identity of {@code editedUserModule} must not be the same as another existing module in the list.
     */
    public void setUserModule(UserModule target, UserModule editedUserModule) {
        requireAllNonNull(target, editedUserModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new UserModuleNotFoundException();
        }

        if (!target.isSameUserModule(editedUserModule) && contains(editedUserModule)) {
            throw new DuplicateUserModuleException();
        }

        internalList.set(index, editedUserModule);
    }

    /**
     * Removes the equivalent module from the list. The module must exist in the list.
     */
    public void remove(UserModule toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new UserModuleNotFoundException();
        }
    }

    public void setUserModules(nus.climods.model.module.UniqueUserModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code modules}. {@code modules} must not contain duplicate modules.
     */
    public void setUserModules(List<UserModule> modules) {
        requireAllNonNull(modules);
        if (!modulesAreUnique(modules)) {
            throw new DuplicateUserModuleException();
        }

        internalList.setAll(modules);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<UserModule> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<UserModule> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof nus.climods.model.module.UniqueUserModuleList // instanceof handles nulls
                && internalList.equals(((nus.climods.model.module.UniqueUserModuleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code modules} contains only unique modules.
     */
    private boolean modulesAreUnique(List<UserModule> modules) {
        for (int i = 0; i < modules.size() - 1; i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                if (modules.get(i).isSameUserModule(modules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
