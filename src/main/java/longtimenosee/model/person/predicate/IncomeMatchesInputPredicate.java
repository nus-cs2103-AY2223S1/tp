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

    public IncomeMatchesInputPredicate(String income) {
        this.income = income;
    }

    @Override
    public boolean test(Person person) {
        IncomeBracket personIncomeBracket = person.getIncome().getIncomeBracket();
        IncomeBracket inputIncomeBracket = Income.parseIncome(Double.parseDouble(income));
        return personIncomeBracket.equals(inputIncomeBracket);
    }
}
