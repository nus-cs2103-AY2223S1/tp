package seedu.address.model.client.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.client.Client;

import java.util.List;
import java.util.function.Predicate;

public class EmailContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getClientEmail().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EmailContainsKeywordsPredicate) other).keywords)); // state check
    }
}
