package seedu.address.model.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class MonthlyContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> monthly;
    private String predicate;

    /**
     * Tests that a {@code Person}'s {@code Income} is greater or lesser than given value.
     */
    public MonthlyContainsKeywordsPredicate(List<String> monthly, String predicate) {
        this.monthly = monthly;
        this.predicate = predicate;
    }

    @Override
    public boolean test(Person person) {

        Monthly personMonthly = person.getMonthly();
        for (String predicateMonthlyName : monthly) {
            if (predicate.equals(">")) {
                if (personMonthly.convertMonthlyToLong() >= Integer.parseInt(predicateMonthlyName)) {
                    return true;
                }
            } else if (predicate.equals("<")) {
                if (personMonthly.convertMonthlyToLong() <= Integer.parseInt(predicateMonthlyName)) {
                    return true;
                }
            } else if (predicate.equals("=")) {
                if (personMonthly.convertMonthlyToLong() == Integer.parseInt(predicateMonthlyName)) {
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
