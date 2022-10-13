package seedu.address.model.task;

import java.util.function.Predicate;

import seedu.address.model.module.Module;

/**
 * Tests that a {@code Task} fulfils the given predicate
 */
public class FilterPredicate implements Predicate<Task> {
    private final Module moduleToCheck;
    private boolean hasModuleToCheck = false;

    private final TaskStatus statusToCheck;
    private boolean hasStatusToCheck = false;

    /**
     * Tests that a {@code Task} matches all the module and status contraints given.
     * @param module
     * @param status
     */
    public FilterPredicate(Module module, TaskStatus status) {
        if (module != null) {
            hasModuleToCheck = true;
        }
        if (status != null) {
            hasStatusToCheck = true;
        }
        this.moduleToCheck = module;
        this.statusToCheck = status;
    }

    public Module getModuleToCheck() {
        return moduleToCheck;
    }

    public boolean hasModuleToCheck() {
        return hasModuleToCheck;
    }

    @Override
    public boolean test(Task task) {
        boolean result = true;
        if (hasModuleToCheck) {
            result &= task.getModule().equals(moduleToCheck);
        }
        if (hasStatusToCheck) {
            result &= task.getStatus().equals(statusToCheck);
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPredicate // instanceof handles nulls
                && moduleToCheck.equals(((FilterPredicate) other).moduleToCheck)
                && statusToCheck.equals(((FilterPredicate) other).statusToCheck)); // state check
    }

    @Override
    public String toString() {
        String result = "";
        if (hasModuleToCheck) {
            result += " Module: " + moduleToCheck;
        }
        if (hasStatusToCheck) {
            result += " Status: " + statusToCheck;
        }
        return result;
    }
}
