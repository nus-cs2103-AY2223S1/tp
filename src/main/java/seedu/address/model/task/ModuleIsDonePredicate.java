package seedu.address.model.task;

import seedu.address.commons.util.StringUtil;

import java.util.function.Predicate;

import java.util.List;

public class ModuleIsDonePredicate implements Predicate<Task> {

    private final List<String> isDone;

    public ModuleIsDonePredicate(List<String> isDone) {
        this.isDone = isDone;
    }

    @Override
    public boolean test(Task task) {
        return isDone.stream().anyMatch(isDone -> StringUtil.containsWordIgnoreCase(String.valueOf(task.isDone()), isDone));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleIsDonePredicate // instanceof handles nulls
                && isDone.equals(((ModuleIsDonePredicate) other).isDone)); // state check
    }

}
