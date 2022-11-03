package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.model.module.exceptions.NullModuleCodeException;

/**
 * Module class represents a Module being taken.
 */
public class Module implements Comparable<Module> {

    public static final String MESSAGE_NO_TASKS_FOR_MODULE = "You have no tasks for this module";

    private final ModuleCode moduleCode;
    private int totalNumOfTasks;
    private int numOfCompletedTasks;
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
        requireAllNonNull(moduleCode, moduleName, moduleCredit);
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleCredit = moduleCredit;
        this.totalNumOfTasks = 0;
        this.numOfCompletedTasks = 0;
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
        this.numOfCompletedTasks = 0;
        this.totalNumOfTasks = 0;
    }

    /**
     * Constructor of the Module class.
     * Module code, number of completed tasks and total number of tasks must be specified.
     *
     * @param moduleCode The module code of the module.
     * @param moduleName The name of the module.
     * @param moduleCredit The number of module credit of the module.
     * @param numOfCompletedTasks The number of completed tasks the module has.
     * @param totalNumOfTasks The total number of tasks the module has.
     */
    private Module(ModuleCode moduleCode, ModuleName moduleName, ModuleCredit moduleCredit,
                  int numOfCompletedTasks, int totalNumOfTasks) {
        requireAllNonNull(moduleCode, moduleName, moduleCredit);
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleCredit = moduleCredit;
        this.numOfCompletedTasks = numOfCompletedTasks;
        this.totalNumOfTasks = totalNumOfTasks;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public ModuleCredit getModuleCredit() {
        return moduleCredit;
    }

    /**
     * Checks whether two modules have the same module code.
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
     *     has a null module code field.
     */
    private boolean hasSameModuleCode(Module otherModule) throws NullModuleCodeException {
        requireNonNull(otherModule);
        if (moduleCode == null || otherModule.moduleCode == null) {
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
    private boolean hasSameModuleName(Module otherModule) {
        if (moduleName != null && otherModule.moduleName != null) {
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
    private boolean hasSameModuleCredit(Module otherModule) {
        if (moduleCredit != null && otherModule.moduleCredit != null) {
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

        return new Module(updatedModuleCode, updatedModuleName, updatedModuleCredit,
                this.numOfCompletedTasks, this.totalNumOfTasks);
    }

    public Module setTotalNumOfTasks(Integer numOfTasks) {
        return new Module(this.moduleCode, this.moduleName, this.moduleCredit, this.numOfCompletedTasks, numOfTasks);
    }

    public Module setNumOfCompletedTasks(Integer numOfCompletedTasks) {
        return new Module(this.moduleCode, this.moduleName, this.moduleCredit,
                numOfCompletedTasks, this.totalNumOfTasks);
    }

    /**
     * Returns the percentage of tasks completed for the module.
     */
    public double getPercentageCompleted() {
        return (double) numOfCompletedTasks / (double) totalNumOfTasks;
    }

    /**
     * Returns a string representation of the number of completed tasks and number of total tasks.
     */
    public String generateProgressMessage() {
        if (totalNumOfTasks == 0) {
            return MESSAGE_NO_TASKS_FOR_MODULE;
        } else {
            return numOfCompletedTasks + " / " + totalNumOfTasks + " task(s) completed";
        }
    }

    public boolean hasTasks() {
        return totalNumOfTasks > 0;
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

        return hasSameModuleCode(otherModule) ;
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
