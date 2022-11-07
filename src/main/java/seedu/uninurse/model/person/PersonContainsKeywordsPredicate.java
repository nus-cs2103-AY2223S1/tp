package seedu.uninurse.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.uninurse.model.tag.TagContainsKeywordsPredicate;

/**
 * Tests that at least one of the Person's details matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final List<Predicate<Person>> predicates;

    /**
     * Constructs a PersonContainsKeywordsPredicate
     * which tests Person to any of the keywords given.
     *
     * @param keywords the keywords
     */
    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.predicates = new ArrayList<>();
        this.predicates.add(new NameContainsKeywordsPredicate(keywords));
        this.predicates.add(new PhoneContainsKeywordsPredicate(keywords));
        this.predicates.add(new EmailContainsKeywordsPredicate(keywords));
        this.predicates.add(new AddressContainsKeywordsPredicate(keywords));
        this.predicates.add(new TagContainsKeywordsPredicate(keywords));
    }

    @Override
    public boolean test(Person person) {
        return predicates.stream().anyMatch(x -> x.test(person));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }
}
