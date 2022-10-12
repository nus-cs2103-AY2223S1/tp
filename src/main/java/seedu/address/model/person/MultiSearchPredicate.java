package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class MultiSearchPredicate implements Predicate<Person> {
    private final AllInfoContainsKeywordsPredicate MATCH_PREDICATE;
    private final NameSoundsSimilarToPredicate FUZZY_PREDICATE;

    public MultiSearchPredicate(List<String> keywords) {
        this.MATCH_PREDICATE = new AllInfoContainsKeywordsPredicate(keywords);
        this.FUZZY_PREDICATE = new NameSoundsSimilarToPredicate(keywords);
    }

    @Override
    public boolean test(Person person) {
        return MATCH_PREDICATE.test(person) || FUZZY_PREDICATE.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MultiSearchPredicate // instanceof handles nulls
                && MATCH_PREDICATE.equals(((MultiSearchPredicate) other).MATCH_PREDICATE)
                && FUZZY_PREDICATE.equals(((MultiSearchPredicate) other).FUZZY_PREDICATE)); // state check
    }

}
