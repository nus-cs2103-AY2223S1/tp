package seedu.address.model.bill;

import java.util.function.Predicate;

/**
 * Tests that a {@code Bill}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicateBill implements Predicate<Bill> {
    private final String keywords;

    public NameContainsKeywordsPredicateBill(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Bill bill) {
        return bill.getAppointment().getName().fullName.toLowerCase().contains(keywords.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof seedu.address.model.bill.NameContainsKeywordsPredicateBill
                // state check
                && keywords.equals(((seedu.address.model.bill.NameContainsKeywordsPredicateBill) other).keywords));
    }

}
