package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tag given.
 */
public class TagMatchesQueryPredicate implements Predicate<Person> {
    private final Tag tag;

    public TagMatchesQueryPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagMatchesQueryPredicate // instanceof handles nulls
                && tag.equals(((TagMatchesQueryPredicate) other).tag)); // state check
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }
}
