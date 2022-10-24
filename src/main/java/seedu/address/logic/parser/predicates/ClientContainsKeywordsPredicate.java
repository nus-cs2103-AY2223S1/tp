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

    public boolean testName(Client client) {
        return nameKeywords.isEmpty() ? true : nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getClientName().toString(), keyword));
    }

    public boolean testEmail(Client client) {
        return emailKeywords.isEmpty() ? true : emailKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getClientEmail().toString(), keyword));
    }

    public boolean testPhone(Client client) {
        return phoneKeywords.isEmpty() ? true : phoneKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getClientPhone().toString(), keyword));
    }

    @Override
    public boolean test(Client client) {
        return testName(client) && testEmail(client) && testPhone(client);
    }

    @Override
    public boolean equals(Object other){
        return other == this // short circuit if same object
                || (other instanceof ClientContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((ClientContainsKeywordsPredicate) other).nameKeywords) //state checks
                && phoneKeywords.equals(((ClientContainsKeywordsPredicate) other).phoneKeywords)
                && emailKeywords.equals(((ClientContainsKeywordsPredicate) other).emailKeywords));
    }

}
