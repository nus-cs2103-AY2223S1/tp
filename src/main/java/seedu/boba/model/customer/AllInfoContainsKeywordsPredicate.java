package seedu.boba.model.customer;

import java.util.List;
import java.util.function.Predicate;


/**
 * Tests that a {@code Customer}'s {@code Name} matches any of the keywords given.
 */
public class AllInfoContainsKeywordsPredicate implements Predicate<Customer> {
    private final List<String> keywords;


    public AllInfoContainsKeywordsPredicate(List<String> keywords) {
        assert keywords != null;
        if (keywords == null) {
            throw new RuntimeException("Keyword list cannot be null");
        }
        this.keywords = keywords;
    }

    @Override
    public boolean test(Customer customer) {
        String allInfo = customer.getAllInfo();
        for (String s : keywords) {
            if (allInfo.toLowerCase().contains(s.toLowerCase())) {
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
