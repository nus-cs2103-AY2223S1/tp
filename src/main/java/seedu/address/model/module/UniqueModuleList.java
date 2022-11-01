package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of module that enforces uniqueness between its elements and does not allow nulls.
 * A module is considered unique by comparing using {@code Module#isSameModule(Module)}. As such, adding and updating of
 * modules uses Module#isSameModule(Module) for equality so as to ensure that the module being added or updated is
 * unique in terms of identity in the UniqueModuleList. However, the removal of a module uses Module#equals(Object) so
 * as to ensure that the module with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Module#isSameModule(Module)
 */
public class UniqueModuleList implements Iterable<Module> {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent module as the given argument.
     */
    public boolean contains(Module toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModule);
    }

    /**
     * Adds a module to the list.
     * The module must not already exist in the list.
     */
    public void add(Module toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        }
        internalList.add(toAdd);
        Collections.sort(internalList);
    }

    /**
     * Replaces the module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the list.
     * The module identity of {@code editedModule} must not be the same as another existing module in the list.
     */
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleNotFoundException();
        }

        if (!target.isSameModule(editedModule) && contains(editedModule)) {
            throw new DuplicateModuleException();
        }

        internalList.set(index, editedModule);
        Collections.sort(internalList);
    }

    /**
     * Returns the {@code Module} with the same {@code ModuleCode} as the
     * {@code Module} supplied as the argument.
     * @param module {@code Module} with the {@code ModuleCode} of the
     *               {@code Module} we would like to retrieve.
     * @throws ModuleNotFoundException when there does not exist a {@code Module}
     *                                 with the same {@code ModuleCode} as the
     *                                 supplied argument.
     */
    public Module getModule(Module module) throws ModuleNotFoundException {
        if (contains(module)) {
            // There should only be one existing module with the same module code.
            assert internalList.stream().filter(module::isSameModule).count() == 1;
            return internalUnmodifiableList.stream()
                                           .filter(module::isSameModule)
                                           .findFirst().get();
        } else {
            throw new ModuleNotFoundException();
        }
    }

    /**
     * Removes the equivalent module from the list.
     * The module must exist in the list.
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    public void setModules(UniqueModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        Collections.sort(internalList);
    }

    /**
     * Replaces the contents of this list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!modulesAreUnique(modules)) {
            throw new DuplicateModuleException();
        }

        internalList.setAll(modules);
        Collections.sort(internalList);
    }

    /**
     * Replaces the person {@code target} in every module with {@code editedPerson}
     * if {@code target} exists in the module.
     *
     * {@code target} must exist in the address book person list.
     * The person identity of {@code editedPerson} must not be the same as another existing person
     * in the address book person list.
     *
     * @param target The person to be replaced.
     * @param editedPerson The person to replace {@code target}.
     */
    public void setPersonInModules(Person target, Person editedPerson) {
        for (int i = 0; i < internalList.size(); i++) {
            Module module = internalList.get(i);
            if (module.containsPerson(target)) {
                module.setPerson(target, editedPerson);
            }
        }
    }

    /**
     * Removes the provided person object from every module's set of persons if it exists.
     *
     * @param person The person to be removed from every module's set of persons.
     */
    public void removePersonFromModules(Person person) {
        for (int i = 0; i < internalList.size(); i++) {
            Module module = internalList.get(i);
            if (module.containsPerson(person)) {
                module.removePerson(person);
            }
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Module> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleList // instanceof handles nulls
                && internalList.equals(((UniqueModuleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code module} contains only unique modules.
     */
    private boolean modulesAreUnique(List<Module> modules) {
        for (int i = 0; i < modules.size() - 1; i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                if (modules.get(i).isSameModule(modules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}



