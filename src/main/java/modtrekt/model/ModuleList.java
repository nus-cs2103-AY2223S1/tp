package modtrekt.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.model.module.UniqueModuleList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameModule comparison)
 */
public class ModuleList implements ReadOnlyModuleList {

    private final UniqueModuleList modules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        modules = new UniqueModuleList();
    }

    public ModuleList() {}

    /**
     * Creates an ModuleList using the Modules in the {@code toBeCopied}
     */
    public ModuleList(ReadOnlyModuleList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code ModuleList} with {@code newData}.
     */
    public void resetData(ReadOnlyModuleList newData) {
        requireNonNull(newData);

        setModules(newData.getModuleList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Returns true if a module with the same code as {@code code} exists in the module list.
     */
    public boolean hasModuleWithModCode(ModCode code) {
        requireNonNull(code);
        return modules.containsCode(code);
    }

    /**
     * Returns a module with the same code as {@code code} from the module list.
     */
    public Module getModuleFromCode(ModCode code) {
        requireNonNull(code);
        return modules.getModuleFromCode(code);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addModule(Module p) {
        modules.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedModule} must not be the same as another existing person in the address book.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);

        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code ModuleList}.
     * {@code key} must exist in the address book.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return modules.asUnmodifiableObservableList().size() + " modules";
        // TODO: refine later
    }

    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleList // instanceof handles nulls
                && modules.equals(((ModuleList) other).modules));
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }
}
