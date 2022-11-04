package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Phone},
 * {@code Email}, and {@code Tags} match any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> nameKeywords;
    private final List<String> phoneKeywords;
    private final List<String> emailKeywords;
    private final List<String> tagKeywords;
    private final List<String> companyKeywords;

    /**
     * Constructs a predicate for checking whether a {@code Person}'s
     * {@code Name}, {@code Phone}, {@code Email}, and {@code Tags}
     * match any of the keywords given.
     */
    public PersonContainsKeywordsPredicate(
            List<String> nameKeywords,
            List<String> phoneKeywords,
            List<String> emailKeywords,
            List<String> tagKeywords,
            List<String> companyKeywords) {
        this.nameKeywords = nameKeywords;
        this.phoneKeywords = phoneKeywords;
        this.emailKeywords = emailKeywords;
        this.tagKeywords = tagKeywords;
        this.companyKeywords = companyKeywords;
    }

    @Override
    public boolean test(Person person) {
        return (nameKeywords.isEmpty() || nameKeywords.stream().anyMatch(
                    keyword -> person.getName().fullName.toLowerCase().contains(keyword.toLowerCase())))
                && (phoneKeywords.isEmpty() || phoneKeywords.stream().anyMatch(
                    keyword -> person.getPhone().value != null
                            && person.getPhone().value.toLowerCase().contains(keyword.toLowerCase())))
                && (emailKeywords.isEmpty() || emailKeywords.stream().anyMatch(
                    keyword -> person.getEmail().value != null
                            && person.getEmail().value.toLowerCase().contains(keyword.toLowerCase())))
                && (tagKeywords.isEmpty() || tagKeywords.stream().anyMatch(
                    keyword -> person.getTags() != null
                            && person.getTags().stream().anyMatch(t -> t.tagName.toLowerCase().contains(keyword))))
                && (companyKeywords.isEmpty() || companyKeywords.stream().anyMatch(
                    keyword -> person.getCompany().fullName != null
                            && person.getCompany().fullName.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonContainsKeywordsPredicate)) {
            return false;
        }

        PersonContainsKeywordsPredicate otherPredicate = (PersonContainsKeywordsPredicate) other;

        return Objects.equals(nameKeywords, otherPredicate.nameKeywords)
                && Objects.equals(phoneKeywords, otherPredicate.phoneKeywords)
                && Objects.equals(emailKeywords, otherPredicate.emailKeywords)
                && Objects.equals(tagKeywords, otherPredicate.tagKeywords)
                && Objects.equals(companyKeywords, otherPredicate.companyKeywords);
    }

}
