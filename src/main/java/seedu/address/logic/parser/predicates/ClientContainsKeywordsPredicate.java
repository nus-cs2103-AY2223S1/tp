package seedu.address.logic.parser.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.client.Client;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a Client object matches any of the keywords given.
 */
public class ClientContainsKeywordsPredicate implements Predicate<Client> {

    private final List<String> nameKeywords;
    private final List<String> emailKeywords;
    private final List<String> phoneKeywords;

    public ClientContainsKeywordsPredicate(List<String> nameKeywords, List<String> emailKeywords,
                                         List<String> phoneKeywords) {
        this.nameKeywords = nameKeywords;
        this.emailKeywords = emailKeywords;
        this.phoneKeywords = phoneKeywords;
    }

    @Override
    public boolean test(Client client) {
        return nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getClientName().toString(), keyword))
                && emailKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getClientEmail().toString(), keyword))
                && phoneKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getClientPhone().toString(), keyword));
    }

}
