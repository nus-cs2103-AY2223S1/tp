package seedu.clinkedin.model.person;

import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.clinkedin.commons.util.StringUtil;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.tag.UniqueTagList;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the prefixed keywords given.
 */
public class DetailsContainPrefixedKeywordsPredicate implements DetailsContainKeywordsPredicate {

    private final Set<Name> nameKeywords;
    private final Set<Phone> phoneKeywords;
    private final Set<Email> emailKeywords;
    private final Set<Address> addressKeywords;
    private final Set<Status> statusKeywords;
    private final Set<Note> noteKeywords;
    private final Set<Rating> ratingKeywords;
    private final Set<Link> linkKeywords;
    private final Map<Prefix, List<String>> tagMap;

    /**
     * Constructor for DetailsContainPrefixedKeywordsPredicate.
     *
     * @param nameKeywords    Set of Name keywords to search for.
     * @param phoneKeywords   Set of Phone keywords to search for.
     * @param emailKeywords   Set of Email keywords to search for.
     * @param addressKeywords Set of Address keywords to search for.
     * @param noteKeywords    Set of Note keywords to search for.
     * @param statusKeywords  Set of Status keywords to search for.
     * @param ratingKeywords  Set of Rating keywords to search for.
     * @param linkKeywords    Set of Link keywords to search for.
     * @param prefToStrings   Set of Tag keywords to search for.
     */
    public DetailsContainPrefixedKeywordsPredicate(Set<Name> nameKeywords, Set<Phone> phoneKeywords,
                                                   Set<Email> emailKeywords, Set<Address> addressKeywords,
                                                   Set<Status> statusKeywords, Set<Note> noteKeywords,
                                                   Set<Rating> ratingKeywords, Set<Link> linkKeywords,
                                                   Map<Prefix, List<String>> prefToStrings) {
        this.nameKeywords = nameKeywords;
        this.phoneKeywords = phoneKeywords;
        this.emailKeywords = emailKeywords;
        this.addressKeywords = addressKeywords;
        this.statusKeywords = statusKeywords;
        this.noteKeywords = noteKeywords;
        this.tagMap = prefToStrings;
        this.ratingKeywords = ratingKeywords;
        this.linkKeywords = linkKeywords;
    }

    @Override
    public boolean test(Person person) {
        for (Prefix p : tagMap.keySet()) {
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
                        keyword.value))
                || ratingKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getRating().toString(),
                        keyword.toString()))
                || linkKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getLinks().toString(),
                        keyword.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsContainPrefixedKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((DetailsContainPrefixedKeywordsPredicate) other).nameKeywords)
                && phoneKeywords.equals(((DetailsContainPrefixedKeywordsPredicate) other).phoneKeywords)
                && emailKeywords.equals(((DetailsContainPrefixedKeywordsPredicate) other).emailKeywords)
                && addressKeywords.equals(((DetailsContainPrefixedKeywordsPredicate) other).addressKeywords)
                && statusKeywords.equals(((DetailsContainPrefixedKeywordsPredicate) other).statusKeywords)
                && noteKeywords.equals(((DetailsContainPrefixedKeywordsPredicate) other).noteKeywords)
                && ratingKeywords.equals(((DetailsContainPrefixedKeywordsPredicate) other).ratingKeywords)
                && linkKeywords.equals(((DetailsContainPrefixedKeywordsPredicate) other).linkKeywords)
                && tagMap.equals(((DetailsContainPrefixedKeywordsPredicate) other).tagMap)); // state check
    }

}
