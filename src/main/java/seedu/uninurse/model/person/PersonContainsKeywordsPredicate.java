package seedu.uninurse.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.uninurse.model.tag.TagContainsKeywordsPredicate;

/**
 * Tests that a {@code Person} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final NameContainsKeywordsPredicate nameContainsKeywordsPredicate;
    private final PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate;
    private final EmailContainsKeywordsPredicate emailContainsKeywordsPredicate;
    private final AddressContainsKeywordsPredicate addressContainsKeywordsPredicate;
    private final TagContainsKeywordsPredicate tagContainsKeywordsPredicate;

    /**
     * Constructs a {@code PersonContainsKeywordsPredicate}
     * which tests {@code Person} to any of the keywords given.
     */
    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.nameContainsKeywordsPredicate = new NameContainsKeywordsPredicate(keywords);
        this.phoneContainsKeywordsPredicate = new PhoneContainsKeywordsPredicate(keywords);
        this.emailContainsKeywordsPredicate = new EmailContainsKeywordsPredicate(keywords);
        this.addressContainsKeywordsPredicate = new AddressContainsKeywordsPredicate(keywords);
        this.tagContainsKeywordsPredicate = new TagContainsKeywordsPredicate(keywords);
    }

    @Override
    public boolean test(Person person) {
        return nameContainsKeywordsPredicate.test(person)
                || phoneContainsKeywordsPredicate.test(person)
                || emailContainsKeywordsPredicate.test(person)
                || addressContainsKeywordsPredicate.test(person)
                || tagContainsKeywordsPredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }
}
