package seedu.address.model.task;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.model.module.Module;

/**
 * Tests that a {@code Task} fulfils the given predicate.
 */
public class FilterPredicate implements Predicate<Task> {
    private final Optional<Module> moduleToCheck;
    private final Optional<Boolean> isCompleted;
    private final Optional<Boolean> isLinked;

    /**
     * Tests that a {@code Task} matches all the module and status constraints given.
     * @param module check if task has same module code.
     * @param isCompleted check if task has same completion status.
     * @param isLinked check if task has same link status.
     */
    public FilterPredicate(Optional<Module> module, Optional<Boolean> isCompleted, Optional<Boolean> isLinked) {
        this.moduleToCheck = module;
        this.isCompleted = isCompleted;
        this.isLinked = isLinked;
    }

    public Module getModuleToCheck() {
        return moduleToCheck.get();
    }

    public boolean hasModuleToCheck() {
        return moduleToCheck.isPresent();
    }

    public String getCompleteCondition() {
        if (isCompleted.get()) {
            return "Complete";
        } else {
            return "Incomplete";
        }
    }

    public String getLinkCondition() {
        if (isLinked.get()) {
            return "Linked";
        } else {
            return "Unlinked";
        }
    }

    @Override
    public boolean test(Task task) {
        boolean result = true;
        if (moduleToCheck.isPresent()) {
            result &= task.getModule().equals(moduleToCheck.get());
        }
        if (isCompleted.isPresent()) {
            result &= task.getStatus().isComplete() == isCompleted.get();
        }
        if (isLinked.isPresent()) {
            result &= task.isLinked() == isLinked.get();
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPredicate // instanceof handles nulls
                && moduleToCheck.equals(((FilterPredicate) other).moduleToCheck)
                && isCompleted.equals(((FilterPredicate) other).isCompleted)
                && isLinked.equals(((FilterPredicate) other).isLinked)); // state check
    }

    @Override
    public String toString() {
        String result = "";
        if (moduleToCheck.isPresent()) {
            result += " Module: " + moduleToCheck.get();
        }
        if (isCompleted.isPresent()) {
            result += " " + getCompleteCondition();
        }
        if (isLinked.isPresent()) {
            result += " " + getLinkCondition();
        }
        return result;
    }
}
