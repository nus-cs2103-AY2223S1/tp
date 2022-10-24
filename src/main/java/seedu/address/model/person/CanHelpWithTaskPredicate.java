package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * Tests if a {@code Person}'s {@code Module}s matches that of the given task.
 */
public class CanHelpWithTaskPredicate implements Predicate<Person> {
    private final int taskIndex;
    private Task task;

    public CanHelpWithTaskPredicate(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public void withTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean test(Person person) {
        Module taskModule = task.getModule();
        Set<Module> personMods = person.getModules();

        for (Module takenModule : personMods) {
            if (taskModule.equals(takenModule)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        System.out.println(other);
        return other == this // short circuit if same instance
                || (other instanceof CanHelpWithTaskPredicate
                && taskIndex == ((CanHelpWithTaskPredicate) other).taskIndex);
    }

}
