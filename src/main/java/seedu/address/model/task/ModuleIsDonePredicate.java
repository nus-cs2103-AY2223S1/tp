package seedu.address.model.task;

import java.util.function.Predicate;

public class ModuleIsDonePredicate implements Predicate<Task> {

    private final boolean isDone;

    public ModuleIsDonePredicate(Boolean isDone) {
        this.isDone =isDone;
    }

    @Override
    public boolean test(Task task) {
        return !(task.isDone());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleIsDonePredicate // instanceof handles nulls
                && (isDone == (((ModuleIsDonePredicate) other).isDone))); // state check
    }

}
