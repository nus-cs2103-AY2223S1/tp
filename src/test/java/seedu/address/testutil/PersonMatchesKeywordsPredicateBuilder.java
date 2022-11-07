package seedu.address.testutil;

import seedu.address.model.person.PersonMatchesKeywordsPredicate;

/**
 * A utility class to help with building PersonMatchesKeywordPredicate objects.
 */
public class PersonMatchesKeywordsPredicateBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ROLE = "Software Engineer";
    public static final String DEFAULT_GITHUBUSER = "amyb";
    public static final String DEFAULT_TAG = "best friend";

    private PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();


    public PersonMatchesKeywordsPredicateBuilder(PersonMatchesKeywordsPredicate predicateToCopy) {
        predicate.setKeywords(predicateToCopy.getKeywords());
    }

    /**
     * Builds a predicate that match the {@code PersonBuilder class} default values
     *
     * @return the predicate builder object
     */
    public static PersonMatchesKeywordsPredicate buildUserPredicate() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        predicate.setKeywords(DEFAULT_NAME + " " + DEFAULT_ADDRESS + " "
                + DEFAULT_ROLE + " " + DEFAULT_TAG + " " + DEFAULT_GITHUBUSER);

        return predicate;
    }
}


