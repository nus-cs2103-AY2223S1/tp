package seedu.address.testutil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();


    public PersonMatchesKeywordsPredicateBuilder(PersonMatchesKeywordsPredicate predicateToCopy) {
        predicate.setKeywords(predicateToCopy.getKeywords());
    }

    public static PersonMatchesKeywordsPredicate buildUserPredicate() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        predicate.setKeywords(DEFAULT_NAME + " " + DEFAULT_ADDRESS + " " + DEFAULT_ROLE + " "  + DEFAULT_TAG + " "  + DEFAULT_GITHUBUSER);

        return predicate;
    }

}
