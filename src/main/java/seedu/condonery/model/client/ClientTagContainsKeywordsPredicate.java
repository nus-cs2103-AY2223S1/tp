package seedu.condonery.model.client;

import java.util.List;
import java.util.function.Predicate;

import seedu.condonery.commons.util.StringUtil;

/**
 * Tests that a {@code Property}'s {@code Tags} matches any of the keywords given.
 */
public class ClientTagContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public ClientTagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getTagNames(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientTagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ClientTagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
