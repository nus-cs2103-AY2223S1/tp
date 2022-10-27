package seedu.address.model.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class IncomeContainsKeywordsPredicate extends FindPredicate {
    private final List<String> income;
    private String predicate;

    /**
     * Tests that a {@code Person}'s {@code Income} is greater or lesser than given value.
     */
    public IncomeContainsKeywordsPredicate(List<String> income, String predicate) {
        super(income);
        this.income = income;
        this.predicate = predicate;
    }

    @Override
    public boolean test(Person person) {
        IncomeLevel personIncome = person.getIncome();
        for (String predicateIncomeName : income) {
            if (predicate.equals(">")) {
                if (personIncome.convertIncomeToLong() >= Integer.parseInt(predicateIncomeName)) {
                    return true;
                }
            } else if (predicate.equals("<")) {
                if (personIncome.convertIncomeToLong() <= Integer.parseInt(predicateIncomeName)) {
                    return true;
                }
            } else if (predicate.equals("=")) {
                if (personIncome.convertIncomeToLong() == Integer.parseInt(predicateIncomeName)) {
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
