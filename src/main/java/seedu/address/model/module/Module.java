package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.EditModuleCommand;

/**
 * Module class represents a Module being taken.
 */
public class Module implements Comparable<Module> {

    private static final String MESSAGE_NO_TASKS_FOR_MODULE = "You have no tasks for this module";

    private final ModuleCode moduleCode;
    private int totalNumOfTasks;
    private int numOfCompletedTasks;

    /**
     * Constructor of the Module class.
     * Module code must be present.
     *
     * @param moduleCode The module code of the module.
     */
    public Module(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
        this.numOfCompletedTasks = 0;
        this.totalNumOfTasks = 0;
    }

    /**
     * Constructor of the Module class.
     * Module code, number of completed tasks and total number of tasks must be specified.
     *
     * @param moduleCode The module code of the module.
     * @param numOfCompletedTasks The number of completed tasks the module has.
     * @param totalNumOfTasks The total number of tasks the module has.
     */
    public Module(ModuleCode moduleCode, int numOfCompletedTasks, int totalNumOfTasks) {
        requireAllNonNull(moduleCode, numOfCompletedTasks, totalNumOfTasks);
        this.moduleCode = moduleCode;
        this.numOfCompletedTasks = numOfCompletedTasks;
        this.totalNumOfTasks = totalNumOfTasks;
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
     * Checks whether two modules have the same module fields.
     *
     * @param firstModule The first module being checked.
     * @param secondModule The second module being checked against.
     * @return true if both Module objects have the same fields; else return false.
     */
    public static boolean isSameModule(Module firstModule, Module secondModule) {
        return firstModule.equals(secondModule);
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

    public Module setTotalNumOfTasks(Integer numOfTasks) {
        return new Module(this.moduleCode, this.numOfCompletedTasks, numOfTasks);
    }

    public Module setNumOfCompletedTasks(Integer numOfCompletedTasks) {
        return new Module(this.moduleCode, numOfCompletedTasks, this.totalNumOfTasks);
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
