package seedu.address.model.tag;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tagToMatch given.
 */
public class TagIsSecuredPredicate implements Predicate<Person> {
    private final Tag tagToMatch;

    public TagIsSecuredPredicate(Tag tagToMatch) {
        this.tagToMatch = tagToMatch;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().contains(tagToMatch);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagIsSecuredPredicate // instanceof handles nulls
                && tagToMatch.equals(((TagIsSecuredPredicate) other).tagToMatch)); // state check
    }

}
