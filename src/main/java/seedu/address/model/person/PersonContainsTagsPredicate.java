package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Tests that a {@code Person}'s {@code Tags} contains all the tags given; case-insensitive.
 */
public class PersonContainsTagsPredicate implements Predicate<Person> {
    private final List<String> searchTags;

    public PersonContainsTagsPredicate(List<String> searchTags) {
        this.searchTags = searchTags;
    }

    @Override
    public boolean test(Person person) {
        Stream<String> personTagsInStringStream = person.getTags().stream().map(tag -> tag.tagName.toLowerCase());
        return searchTags.stream()
                .allMatch(searchTag -> isTagPresentOnPersonIgnoreCase(personTagsInStringStream, searchTag));
    }

    private boolean isTagPresentOnPersonIgnoreCase(Stream<String> personTags, String searchTag) {
        return personTags.anyMatch(personTag -> personTag.equals(searchTag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsTagsPredicate // instanceof handles nulls
                && searchTags.equals(((PersonContainsTagsPredicate) other).searchTags)); // state check
    }
}
