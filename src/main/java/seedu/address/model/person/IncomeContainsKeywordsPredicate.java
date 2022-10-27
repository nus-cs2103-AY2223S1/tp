package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class IncomeContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> income;
    private boolean greater;
    private String predicate;

    /**
     * Tests that a {@code Person}'s {@code Income} is greater or lesser than given value.
     */
    public IncomeContainsKeywordsPredicate(List<String> income, String predicate) {
        this.income = income;
        this.predicate = predicate;
    }

    @Override
    public boolean test(Person person) {
        if (predicate.equals(">")) {
            greater = true;
        } else {
            greater = false;
        }

        IncomeLevel personIncome = person.getIncome();
        for (String predicateIncomeName : income) {
            if (greater) {
                if (personIncome.convertIncomeToInt() > Integer.parseInt(predicateIncomeName)) {
                    return true;
                }
            } else {
                if (personIncome.convertIncomeToInt() < Integer.parseInt(predicateIncomeName)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IncomeContainsKeywordsPredicate // instanceof handles nulls
                && income.equals(((IncomeContainsKeywordsPredicate) other).income)); // state check
    }

}
