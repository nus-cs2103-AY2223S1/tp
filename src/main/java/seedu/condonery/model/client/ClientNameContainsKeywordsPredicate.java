package seedu.condonery.model.client;

import java.util.List;
import java.util.function.Predicate;

import seedu.condonery.commons.util.StringUtil;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class ClientNameContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public ClientNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ClientNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
