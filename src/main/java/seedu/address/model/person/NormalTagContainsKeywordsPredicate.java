package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.NormalTag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NormalTagContainsKeywordsPredicate extends FindPredicate {
    private final List<String> tags;

    /**
     * Constructs a {@code NormalTagContainsKeywordsPredicate}.
     *
     * @param tags Tags to be tested against.
     */
    public NormalTagContainsKeywordsPredicate(List<String> tags) {
        super(tags);
        this.tags = tags.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        Set<NormalTag> personTags = person.getTags();
        for (String predicateTagName : tags) {
            for (NormalTag personsTag : personTags) {
                if (personsTag.tagName.equalsIgnoreCase(predicateTagName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NormalTagContainsKeywordsPredicate // instanceof handles nulls
                && tags.equals(((NormalTagContainsKeywordsPredicate) other).tags)); // state check
    }

}
