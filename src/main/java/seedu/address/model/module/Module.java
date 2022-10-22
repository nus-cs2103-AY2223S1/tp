package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.model.module.exceptions.NullModuleCodeException;

/**
 * Module class represents a Module being taken.
 */
public class Module {
    private final ModuleCode moduleCode;
    private final ModuleName moduleName;
    private final ModuleCredit moduleCredit;

    /**
     * Constructor of the Module class.
     * Module code must be present.
     *
     * @param moduleCode The module code of the module.
     * @param moduleName The module name of the module.
     * @param moduleCredit The module credit of the module.
     */
    public Module(ModuleCode moduleCode, ModuleName moduleName, ModuleCredit moduleCredit) {
        requireNonNull(moduleCode);
        requireNonNull(moduleName);
        requireNonNull(moduleCredit);
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleCredit = moduleCredit;
    }

    /**
     * Constructor of the Module class.
     * Module code must be present.
     *
     * @param moduleCode The module code of the module.
     */
    public Module(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
        this.moduleName = null;
        this.moduleCredit = null;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public ModuleName getModuleName() { return moduleName; }

    public ModuleCredit getModuleCredit() { return moduleCredit; }

    /**
     * Checks whether two modules have the same module code or same module name.
     *
     * @param otherModule The other module being compared against.
     * @return true if the two Module objects have the same module code or module name;
     *         else return false
     */
    public boolean isSameModule(Module otherModule) {
        return this.equals(otherModule);
    }

    /**
     * Checks whether the two modules has the exact same fields.
     *
     * @param otherModule The other module being compared against.
     * @return true if the two Module objects have the same module code, module name and module credit;
     *         else return false
     */
    public boolean hasAllSameFields(Module otherModule) {
        return hasSameModuleCode(otherModule) && hasSameModuleName(otherModule) && hasSameModuleCredit(otherModule);
    }

    /**
     * Checks whether the two modules has the same module code.
     *
     * @param otherModule The other module being compared against.
     * @return true if the two Module objects have the same module code;
     *         else return false
     * @throws NullModuleCodeException if the current module or other module {@code otherModule}
     * has a null module code field.
     */
    public boolean hasSameModuleCode(Module otherModule) throws NullModuleCodeException{
        requireNonNull(otherModule);
        if (moduleCode == null || otherModule.moduleCode == null){
            throw new NullModuleCodeException();
        }
        return moduleCode.equals(otherModule.moduleCode);
    }

    /**
     * Checks whether the two modules has the same module name.
     *
     * @param otherModule The other module being compared against.
     * @return true if the two Module objects have the same module name;
     *         else return false
     */
    public boolean hasSameModuleName(Module otherModule) {
        if (moduleName != null && otherModule.moduleName != null){
            return moduleName.equals(otherModule.moduleName);
        }
        return false;
    }

    /**
     * Checks whether the two modules has the same module credit.
     *
     * @param otherModule The other module being compared against.
     * @return true if the two Module objects have the same module credit;
     *         else return false
     */
    public boolean hasSameModuleCredit(Module otherModule) {
        if (moduleCredit != null && otherModule.moduleCredit != null){
            return moduleCredit.equals(otherModule.moduleCredit);
        }
        return false;
    }


    /**
     * Creates and returns a {@code Module} with the details of {@code this}
     * edited with {@code editModuleDescriptor}.
     */
    public Module edit(EditModuleCommand.EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(editModuleDescriptor);

        ModuleCode updatedModuleCode = editModuleDescriptor.getModuleCode().orElse(this.moduleCode);
        ModuleName updatedModuleName = editModuleDescriptor.getModuleName().orElse(this.moduleName);
        ModuleCredit updatedModuleCredit = editModuleDescriptor.getModuleCredit().orElse(this.moduleCredit);

        return new Module(updatedModuleCode, updatedModuleName, updatedModuleCredit);
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

        return hasSameModuleCode(otherModule);
    }

    @Override
    public String toString() {
        return getModuleCode().toString();
    }

}
