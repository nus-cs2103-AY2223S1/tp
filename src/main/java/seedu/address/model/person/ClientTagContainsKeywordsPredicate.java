package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ClientTagContainsKeywordsPredicate extends FindPredicate {
    private final List<String> clientTags;

    /**
     * Constructs a {@code RiskTagContainsKeywordsPredicate}.
     *
     * @param clientTags RiskTags to be tested against.
     */
    public ClientTagContainsKeywordsPredicate(List<String> clientTags) {
        super(clientTags);
        this.clientTags = clientTags.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> personTags = person.getSpecialTags();
        for (String predicateTagName : clientTags) {
            for (Tag personsTag : personTags) {
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
                || (other instanceof ClientTagContainsKeywordsPredicate // instanceof handles nulls
                && clientTags.equals(((ClientTagContainsKeywordsPredicate) other).clientTags)); // state check
    }

}
