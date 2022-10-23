package seedu.address.model.task;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.model.module.Module;

/**
 * Tests that a {@code Task} fulfils the given predicate.
 */
public class FilterPredicate implements Predicate<Task> {
    private final Optional<Module> moduleToCheck;
    private final Optional<TaskStatus> completionStatusToCheck;
    private final Optional<LinkStatus> linkStatusToCheck;

    /**
     * Tests that a {@code Task} matches all the module and status constraints given.
     * @param module check if task has same module code.
     * @param completionStatus check if task has same status.
     */
    public FilterPredicate(Optional<Module> module, Optional<TaskStatus> completionStatus,
                           Optional<LinkStatus> linkStatusToCheck) {
        this.moduleToCheck = module;
        this.completionStatusToCheck = completionStatus;
        this.linkStatusToCheck = linkStatusToCheck;
    }

    public Module getModuleToCheck() {
        return moduleToCheck.get();
    }

    public boolean hasModuleToCheck() {
        return moduleToCheck.isPresent();
    }

    @Override
    public boolean test(Task task) {
        boolean result = true;
        if (moduleToCheck.isPresent()) {
            result &= task.getModule().equals(moduleToCheck.get());
        }
        if (completionStatusToCheck.isPresent()) {
            result &= task.getStatus().equals(completionStatusToCheck.get());
        }
        if (linkStatusToCheck.isPresent()) {
            result &= task.isLinked() == linkStatusToCheck.get().isLinked();
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPredicate // instanceof handles nulls
                && moduleToCheck.equals(((FilterPredicate) other).moduleToCheck)
                && completionStatusToCheck.equals(((FilterPredicate) other).completionStatusToCheck)
                && linkStatusToCheck.equals(((FilterPredicate) other).linkStatusToCheck)); // state check
    }

    @Override
    public String toString() {
        String result = "";
        if (moduleToCheck.isPresent()) {
            result += " Module: " + moduleToCheck.get();
        }
        if (completionStatusToCheck.isPresent()) {
            result += " " + completionStatusToCheck.get();
        }
        if (linkStatusToCheck.isPresent()) {
            result += " " + linkStatusToCheck.get();
        }
        return result;
    }
}
