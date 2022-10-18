package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.EditModuleCommand;


/**
 * Module class represents a Module being taken.
 */
public class Module {
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
     * Checks whether two modules have the same module code.
     *
     * @param otherModule The other module being compared against.
     * @return true if the two Module objects have the same module code;
     *         else return false
     */
    public boolean isSameModuleCode(Module otherModule) {
        if (otherModule == null) {
            return false;
        }
        return otherModule == this || otherModule.moduleCode.equals(this.moduleCode);
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
    public boolean equals(Object otherModule) {
        return otherModule == this || (otherModule instanceof Module
                && isSameModuleCode((Module) otherModule));
    }

    @Override
    public String toString() {
        return getModuleCode().toString();
    }

}
