package seedu.address.model.person;

import java.util.List;


/**
 * Tests that a {@code Person}'s {@code Monthly} matches any of the keywords given.
 */
public class MonthlyContainsKeywordsPredicate extends FindPredicate {
    private final List<String> monthly;
    private String predicate;

    /**
     * Tests that a {@code Person}'s {@code Monthly} is greater or lesser than given value.
     */
    public MonthlyContainsKeywordsPredicate(List<String> monthly, String predicate) {
        super(monthly);
        this.monthly = monthly;
        this.predicate = predicate;
    }

    @Override
    public boolean test(Person person) {

        Monthly personMonthly = person.getMonthly();
        for (String predicateMonthlyName : monthly) {
            if (predicate.equals(">")) {
                if (personMonthly.convertMonthlyToLong() >= Long.parseLong(predicateMonthlyName)) {
                    return true;
                }
            } else if (predicate.equals("<")) {
                if (personMonthly.convertMonthlyToLong() <= Long.parseLong(predicateMonthlyName)) {
                    return true;
                }
            } else if (predicate.equals("=")) {
                if (personMonthly.convertMonthlyToLong() == Long.parseLong(predicateMonthlyName)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MonthlyContainsKeywordsPredicate // instanceof handles nulls
                && monthly.equals(((MonthlyContainsKeywordsPredicate) other).monthly)); // state check
    }

}
