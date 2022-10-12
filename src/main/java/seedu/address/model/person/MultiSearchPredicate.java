package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class MultiSearchPredicate implements Predicate<Person> {
    private final AllInfoContainsKeywordsPredicate matchPredicate;
    private final NameSoundsSimilarToPredicate fuzzyPredicate;

    /**
     * Initialize the two sub-predicates
     *
     * @param keywords
     */
    public MultiSearchPredicate(List<String> keywords) {
        this.matchPredicate = new AllInfoContainsKeywordsPredicate(keywords);
        this.fuzzyPredicate = new NameSoundsSimilarToPredicate(keywords);
    }

    @Override
    public boolean test(Person person) {
        return matchPredicate.test(person) || fuzzyPredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MultiSearchPredicate // instanceof handles nulls
                && matchPredicate.equals(((MultiSearchPredicate) other).matchPredicate)
                && fuzzyPredicate.equals(((MultiSearchPredicate) other).fuzzyPredicate)); // state check
    }

}
