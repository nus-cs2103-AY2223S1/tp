package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.module.Module;

/**
 * Tests that a {@code Task}'s {@code module} matches the module given.
 */
public class TaskContainsModulesPredicate implements Predicate<Task> {
    private final List<Module> modules;

    public TaskContainsModulesPredicate(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public boolean test(Task task) {
        return modules.stream()
                .anyMatch(module -> StringUtil.containsWordIgnoreCase(task.getModule().toString(), module.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsModulesPredicate // instanceof handles nulls
                && modules.equals(((TaskContainsModulesPredicate) other).modules)); // state check
    }

}
