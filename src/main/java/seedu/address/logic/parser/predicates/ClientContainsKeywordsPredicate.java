package seedu.address.logic.parser.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.client.Client;

/**
 * Tests that a Client object matches any of the keywords given.
 */
public class ClientContainsKeywordsPredicate implements Predicate<Client> {

    private final List<String> nameKeywords;
    private final List<String> emailKeywords;
    private final List<String> phoneKeywords;

    /**
     * Constructs a ClientContainsKeywordsPredicate object with the user inputs.
     * @param nameKeywords List of Strings representing keywords to search for in name
     * @param emailKeywords List of Strings representing keywords to search for in email
     * @param phoneKeywords List of Strings representing keywords to search for in phone
     */
    public ClientContainsKeywordsPredicate(List<String> nameKeywords, List<String> emailKeywords,
                                         List<String> phoneKeywords) {
        this.nameKeywords = nameKeywords;
        this.emailKeywords = emailKeywords;
        this.phoneKeywords = phoneKeywords;
    }

    /**
     * Checks if given name matches with any word in the name present.
     * @param namePresent String representing name present
     * @param nameGiven String representing name given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testName(String namePresent, String nameGiven) {
        return Arrays.stream(namePresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(nameGiven, words));
    }

    /**
     * Checks if the client's name matches the name keyword being search for.
     * @param client Client whose name is being used to search the keyword in
     * @return boolean true if the name fulfills the search criteria and false otherwise
     */
    public boolean testName(Client client) {
        if (nameKeywords.isEmpty()) {
            return true;
        } else {
            return nameKeywords.stream().anyMatch(name -> testName(name, client.getClientName().toString()));
        }
    }

    /**
     * Checks if given phone matches with any word in the phone present.
     * @param phonePresent String representing phone present
     * @param phoneGiven String representing phone given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testPhone(String phonePresent, String phoneGiven) {
        return Arrays.stream(phonePresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(phoneGiven, words));
    }

    /**
     * Checks if the client's phone matches the phone keyword being search for.
     * @param client Client whose phone is being used to search the keyword in
     * @return boolean true if the phone fulfills the search criteria and false otherwise
     */
    public boolean testPhone(Client client) {
        if (phoneKeywords.isEmpty()) {
            return true;
        } else {
            return phoneKeywords.stream().anyMatch(phone -> testPhone(phone, client.getClientPhone().toString()));
        }
    }

    /**
     * Checks if given email matches with any word in the email present.
     * @param emailPresent String representing email present
     * @param emailGiven String representing email given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testEmail(String emailPresent, String emailGiven) {
        return Arrays.stream(emailPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(emailGiven, words));
    }

    /**
     * Checks if the client's email matches the email keyword being search for.
     * @param client Client whose email is being used to search the keyword in
     * @return boolean true if the email fulfills the search criteria and false otherwise
     */
    public boolean testEmail(Client client) {
        if (emailKeywords.isEmpty()) {
            return true;
        } else {
            return emailKeywords.stream().anyMatch(email -> testEmail(email, client.getClientEmail().toString()));
        }
    }

    @Override
    public boolean test(Client client) {
        return testName(client) && testEmail(client) && testPhone(client);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((ClientContainsKeywordsPredicate) other).nameKeywords) //state checks
                && phoneKeywords.equals(((ClientContainsKeywordsPredicate) other).phoneKeywords)
                && emailKeywords.equals(((ClientContainsKeywordsPredicate) other).emailKeywords));
    }

}
