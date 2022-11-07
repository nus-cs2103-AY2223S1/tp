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
    private final List<String> mobileKeywords;
    private final List<String> clientIdKeywords;

    /**
     * Constructs a ClientContainsKeywordsPredicate object with the user inputs.
     * @param nameKeywords List of Strings representing keywords to search for in name
     * @param emailKeywords List of Strings representing keywords to search for in email
     * @param mobileKeywords List of Strings representing keywords to search for in mobile
     * @param clientIdKeywords List of Strings representing keywords to search for in client id
     */
    public ClientContainsKeywordsPredicate(List<String> nameKeywords, List<String> emailKeywords,
                                         List<String> mobileKeywords, List<String> clientIdKeywords) {
        this.nameKeywords = nameKeywords;
        this.emailKeywords = emailKeywords;
        this.mobileKeywords = mobileKeywords;
        this.clientIdKeywords = clientIdKeywords;
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
     * Checks if the client id matches the id keyword being search for.
     * @param client Client whose id is being used to search the keyword in
     * @return true if the client id fulfills the search criteria and false otherwise
     */
    public boolean testClientId(Client client) {
        if (clientIdKeywords.isEmpty()) {
            return true;
        } else {
            return clientIdKeywords.stream().anyMatch(
                    c -> testClientId(c, client.getClientId().toString()));
        }
    }

    /**
     * Checks if given id matches with any word in the id present.
     * @param idPresent String representing id present
     * @param idGiven String representing id given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testClientId(String idPresent, String idGiven) {
        return Arrays.stream(idPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(idGiven, words));
    }

    /**
     * Checks if given mobile matches with any word in the mobile present.
     * @param mobilePresent String representing mobile present
     * @param mobileGiven String representing mobile given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testMobile(String mobilePresent, String mobileGiven) {
        return Arrays.stream(mobilePresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(mobileGiven, words));
    }

    /**
     * Checks if the client's mobile matches the mobile keyword being search for.
     * @param client Client whose mobile is being used to search the keyword in
     * @return boolean true if the mobile fulfills the search criteria and false otherwise
     */
    public boolean testMobile(Client client) {
        if (mobileKeywords.isEmpty()) {
            return true;
        } else {
            return mobileKeywords.stream().anyMatch(mobile -> testMobile(mobile, client.getClientMobile().toString()));
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
        return testName(client) && testEmail(client) && testMobile(client) && testClientId(client);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((ClientContainsKeywordsPredicate) other).nameKeywords) //state checks
                && mobileKeywords.equals(((ClientContainsKeywordsPredicate) other).mobileKeywords)
                && emailKeywords.equals(((ClientContainsKeywordsPredicate) other).emailKeywords)
                && clientIdKeywords.equals(((ClientContainsKeywordsPredicate) other).clientIdKeywords));
    }

}
