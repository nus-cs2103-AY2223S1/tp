package longtimenosee.model.person.predicate;

import java.util.function.Predicate;

import longtimenosee.model.person.Income;
import longtimenosee.model.person.Income.IncomeBracket;
import longtimenosee.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Income} falls under the same income bracket as the input provided.
 */
public class IncomeMatchesInputPredicate implements Predicate<Person> {
    private final String income;

    /**
     * Constructs an IncomeMatchesInputPredicate object, which consists of an income input.
     *
     * @param income is the input by the user to be compared.
     */
    public IncomeMatchesInputPredicate(String income) {
        assert income.length() != 0;
        this.income = income;
    }

    @Override
    public boolean test(Person person) {
        IncomeBracket personIncomeBracket = person.getIncome().getIncomeBracket();
        IncomeBracket inputIncomeBracket = Income.parseIncome(Double.parseDouble(income));
        return personIncomeBracket.equals(inputIncomeBracket);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof IncomeMatchesInputPredicate) {
                return income.equals(((IncomeMatchesInputPredicate) other).income);
            } else {
                return false;
            }
        }
    }
}
