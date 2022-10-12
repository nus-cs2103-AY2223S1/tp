package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Person}'s {@code Tags} contains all the tags given; case-insensitive.
 */
public class PersonContainsTagsPredicate implements Predicate<Person> {
    private final List<String> tagsToSearchInLowerCase;

    public PersonContainsTagsPredicate(List<String> tagsToSearch) {
        this.tagsToSearchInLowerCase = tagsToSearch.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        List<String> personTagsInLowerCase =
                person.getTags().stream().map(tag -> tag.tagName.toLowerCase()).collect(Collectors.toList());

        return personTagsInLowerCase.containsAll(tagsToSearchInLowerCase);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsTagsPredicate // instanceof handles nulls
                // state check
                && tagsToSearchInLowerCase.equals(((PersonContainsTagsPredicate) other).tagsToSearchInLowerCase));
    }
}
