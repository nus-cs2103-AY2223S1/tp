package seedu.clinkedin.model.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import seedu.clinkedin.commons.util.StringUtil;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.tag.UniqueTagList;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class DetailsContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final Set<Name> nameKeywords;
    private final Set<Phone> phoneKeywords;
    private final Set<Email> emailKeywords;
    private final Set<Address> addressKeywords;
    private final Set<Status> statusKeywords;
    private final Set<Note> noteKeywords;
    private final Map<Prefix, List<String>> tagMap;

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
        this.statusKeywords = new HashSet<>();
        this.noteKeywords = new HashSet<>();
        this.tagMap = new HashMap<>();
    }

    /**
     * Constructor for DetailsContainKeywordsPredicate.
     *
     * @param nameKeywords    Set of Name keywords to search for.
     * @param phoneKeywords   Set of Phone keywords to search for.
     * @param emailKeywords   Set of Email keywords to search for.
     * @param addressKeywords Set of Address keywords to search for.
     * @param noteKeywords    Set of Note keywords to search for.
     */
    public DetailsContainKeywordsPredicate(Set<Name> nameKeywords, Set<Phone> phoneKeywords, Set<Email> emailKeywords,
                                           Set<Address> addressKeywords, Set<Status> statusKeywords,
                                           Set<Note> noteKeywords, Map<Prefix, List<String>> prefToStrings) {
        this.keywords = new ArrayList<>();
        this.nameKeywords = nameKeywords;
        this.phoneKeywords = phoneKeywords;
        this.emailKeywords = emailKeywords;
        this.addressKeywords = addressKeywords;
        this.statusKeywords = statusKeywords;
        this.noteKeywords = noteKeywords;
        this.tagMap = prefToStrings;
    }

    @Override
    public boolean test(Person person) {
        if (!keywords.isEmpty()) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(person.getDetailsAsString(),
                            keyword));
        } else {
            for (Prefix p : tagMap.keySet()) {
                System.out.println(person.getTags().get(UniqueTagTypeMap.getTagTypeFromPrefix(p)));
                UniqueTagList uniqueTagList = person.getTags().get(UniqueTagTypeMap.getTagTypeFromPrefix(p));
                for (String s : tagMap.get(p)) {
                    if (uniqueTagList == null) {
                        break;
                    }
                    if (uniqueTagList.hasSequenceMatch(s)) {
                        return true;
                    }
                }
            }
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
                    || statusKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getStatus().toString(),
                            keyword.toString()))
                    || noteKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getNote().value,
                            keyword.value));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DetailsContainKeywordsPredicate) other).keywords)); // state check
    }

}
