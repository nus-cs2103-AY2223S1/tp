package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.EditModuleCommand;

/**
 * Module class represents a Module being taken.
 */
public class Module implements Comparable<Module> {
    private final ModuleCode moduleCode;

    /**
     * Constructor of the Module class.
     * Module code must be present.
     *
     * @param moduleCode The module code of the module.
     */
    public Module(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Checks whether two modules have the same data fields.
     *
     * @param otherModule The other module being compared against.
     * @return true if the two Module objects have the same module code;
     *         else return false
     */
    public boolean isSameModule(Module otherModule) {
        return this.equals(otherModule);
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code this}
     * edited with {@code editModuleDescriptor}.
     */
    public Module edit(EditModuleCommand.EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(editModuleDescriptor);

        ModuleCode updatedModuleCode = editModuleDescriptor.getModuleCode().orElse(this.moduleCode);
        return new Module(updatedModuleCode);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleCode().equals(getModuleCode());
    }

    @Override
    public String toString() {
        return getModuleCode().toString();
    }

    @Override
    public int compareTo(Module mod) {
        return this.getModuleCode().moduleCode.compareTo(mod.getModuleCode().moduleCode);
    }
}
