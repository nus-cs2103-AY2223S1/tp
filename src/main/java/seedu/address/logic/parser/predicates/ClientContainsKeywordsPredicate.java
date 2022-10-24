package seedu.address.logic.parser.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.client.Client;

import java.util.ArrayList;
import java.util.Arrays;
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

    public boolean testName(String namePresent, String nameGiven) {
        return Arrays.stream(namePresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(nameGiven, words));
    }

    public boolean testPhone(String phonePresent, String phoneGiven) {
        return Arrays.stream(phonePresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(phoneGiven, words));
    }

    public boolean testEmail(String emailPresent, String emailGiven) {
        return Arrays.stream(emailPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(emailGiven, words));
    }


    public boolean testName(Client client) {
        if (nameKeywords.isEmpty()) {
            return true;
        } else {
           return nameKeywords.stream().anyMatch(name -> testName(name, client.getClientName().toString()));
        }
    }

    public boolean testEmail(Client client) {
        if (emailKeywords.isEmpty()) {
            return true;
        } else {
            return emailKeywords.stream().anyMatch(email -> testEmail(email, client.getClientEmail().toString()));
        }
    }

    public boolean testPhone(Client client) {
        if (phoneKeywords.isEmpty()) {
            return true;
        } else {
            return phoneKeywords.stream().anyMatch(phone -> testPhone(phone, client.getClientPhone().toString()));
        }
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
