package soconnect.model.todo;

import java.time.LocalDate;
import java.util.function.Predicate;

import soconnect.model.tag.Tag;

/**
 * Tests that a {@code Todo} contains a certain {@code Tag} and
 * the {@code Date} of the {@code Todo} should not be earlier than the current date.
 */
public class TodoContainsTagPredicate implements Predicate<Todo> {

    private final Tag tag;

    public TodoContainsTagPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Todo todo) {
        Date now = new Date(LocalDate.now());

        return !todo.isBefore(now) && todo.getTags().contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TodoContainsTagPredicate // instanceof handles nulls
            && tag.equals(((TodoContainsTagPredicate) other).tag)); // state check
    }

}
