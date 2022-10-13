package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Person}'s {@code Tags} has all the tags given; case-insensitive. Tags must be a full
 * word for word match.
 */
public class PersonContainsTagsPredicate implements Predicate<Person> {
    private final List<String> tagsToSearchInUpperCase;

    public PersonContainsTagsPredicate(List<String> tagsToSearch) {
        this.tagsToSearchInUpperCase = tagsToSearch.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        List<String> personTagsInUpperCase =
                person.getTags().stream().map(tag -> tag.tagName.toUpperCase()).collect(Collectors.toList());

        return personTagsInUpperCase.containsAll(tagsToSearchInUpperCase);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsTagsPredicate // instanceof handles nulls
                // state check
                && tagsToSearchInUpperCase.equals(((PersonContainsTagsPredicate) other).tagsToSearchInUpperCase));
    }
}
