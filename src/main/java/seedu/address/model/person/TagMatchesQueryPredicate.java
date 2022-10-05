package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the set of tags given.
 */
public class TagMatchesQueryPredicate implements Predicate<Person> {
    private final Set<Tag> queries;

    public TagMatchesQueryPredicate(Set<Tag> queries) {
        this.queries = queries;
    }


    @Override
    public boolean test(Person person) {
        Set<Tag> intersection = new HashSet<>(queries);
        intersection.retainAll(person.getTags());
        return !intersection.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagMatchesQueryPredicate // instanceof handles nulls
                        && queries.containsAll(((TagMatchesQueryPredicate) other).queries)); // state check
    }

    @Override
    public int hashCode() {
        return queries.stream().map(str -> str.hashCode()).reduce(0, (code1, code2) -> code1 + code2);
    }

}
