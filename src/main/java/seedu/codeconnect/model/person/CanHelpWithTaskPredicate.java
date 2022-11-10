package seedu.codeconnect.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.codeconnect.commons.core.index.Index;
import seedu.codeconnect.model.module.Module;
import seedu.codeconnect.model.task.Task;

/**
 * Tests if a {@code Person}'s {@code Module} matches that of the given task.
 */
public class CanHelpWithTaskPredicate implements Predicate<Person> {
    private final Index taskIndex;
    private Task task;

    public CanHelpWithTaskPredicate(Index taskIndex) {
        this.taskIndex = taskIndex;
    }

    public CanHelpWithTaskPredicate(int taskIndex) {
        this.taskIndex = Index.fromOneBased(taskIndex);
    }

    /**
     * Builds a {@code CanHelpWithTaskPredicate} using a given task instead of its index.
     *
     * @param task {@code Task} to test against a {@code Person} to determine if modules match.
     * @return {@code Predicate} to execute on the person.
     */
    public static CanHelpWithTaskPredicate withTask(Task task) {
        CanHelpWithTaskPredicate predicate = new CanHelpWithTaskPredicate(Index.fromOneBased(1));
        predicate.setTask(task);
        return predicate;
    }

    public Index getTaskIndex() {
        return taskIndex;
    }

    public void setTask(Task task) {
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
        return other == this // short circuit if same instance
                || (other instanceof CanHelpWithTaskPredicate
                && taskIndex.equals(((CanHelpWithTaskPredicate) other).taskIndex));
    }

}
