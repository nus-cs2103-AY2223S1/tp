package seedu.address.model.client.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.client.Client;

import java.util.List;
import java.util.function.Predicate;

public class PhoneContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getClientPhone().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }
}
