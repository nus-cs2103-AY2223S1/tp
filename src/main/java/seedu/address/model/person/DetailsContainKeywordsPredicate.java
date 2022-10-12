package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class DetailsContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final Set<Name> nameKeywords;
    private final Set<Phone> phoneKeywords;
    private final Set<Email> emailKeywords;
    private final Set<Address> addressKeywords;
    private final Set<Tag> tagKeywords;
    private final Set<Status> statusKeywords;

    /**
     * Constructor for DetailsContainKeywordsPredicate.
     *
     * @param keywords List of keywords to search for.
     */
    public DetailsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.nameKeywords = new HashSet<>();
        this.phoneKeywords = new HashSet<>();
        this.emailKeywords = new HashSet<>();
        this.addressKeywords = new HashSet<>();
        this.tagKeywords = new HashSet<>();
        this.statusKeywords = new HashSet<>();
    }

    /**
     * Constructor for DetailsContainKeywordsPredicate.
     *
     * @param nameKeywords    Set of Name keywords to search for.
     * @param phoneKeywords   Set of Phone keywords to search for.
     * @param emailKeywords   Set of Email keywords to search for.
     * @param addressKeywords Set of Address keywords to search for.
     * @param tagKeywords     Set of Tag keywords to search for.
     */
    public DetailsContainKeywordsPredicate(Set<Name> nameKeywords, Set<Phone> phoneKeywords, Set<Email> emailKeywords,
                                           Set<Address> addressKeywords, Set<Tag> tagKeywords,
                                           Set<Status> statusKeywords) {
        this.keywords = new ArrayList<>();
        this.nameKeywords = nameKeywords;
        this.phoneKeywords = phoneKeywords;
        this.emailKeywords = emailKeywords;
        this.addressKeywords = addressKeywords;
        this.tagKeywords = tagKeywords;
        this.statusKeywords = statusKeywords;
    }

    @Override
    public boolean test(Person person) {
        if (!keywords.isEmpty()) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getDetailsAsString(), keyword));
        } else {
            return nameKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getName().fullName,
                            keyword.fullName))
                    || phoneKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getPhone().value, keyword.value))
                    || emailKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getEmail().value, keyword.value))
                    || addressKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getAddress().value,
                            keyword.value))
                    || tagKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getTags().toString(),
                            keyword.tagName))
                    || statusKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getStatus().toString(),
                            keyword.toString()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DetailsContainKeywordsPredicate) other).keywords)); // state check
    }

}
