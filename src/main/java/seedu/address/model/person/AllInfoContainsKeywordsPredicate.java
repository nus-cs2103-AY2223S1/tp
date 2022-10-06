package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AllInfoContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public AllInfoContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String allInfo = person.getAllInfo();
        for (String s: keywords){
            if (allInfo.contains(s)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AllInfoContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AllInfoContainsKeywordsPredicate) other).keywords)); // state check
    }

}
