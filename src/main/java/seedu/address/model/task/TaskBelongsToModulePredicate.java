package seedu.address.model.task;

import java.util.function.Predicate;

import seedu.address.model.module.Module;

/**
 * Tests that a {@code Task}'s {@code Module} matches the module given.
 */
public class TaskBelongsToModulePredicate implements Predicate<Task> {
    private final Module toCheck;

    public TaskBelongsToModulePredicate(Module module) {
        this.toCheck = module;
    }

    public Module getToCheck() {
        return toCheck;
    }

    @Override
    public boolean test(Task task) {
        return task.getModule().equals(toCheck);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBelongsToModulePredicate // instanceof handles nulls
                && toCheck.equals(((TaskBelongsToModulePredicate) other).toCheck)); // state check
    }

}
