package longtimenosee.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PersonBuilder;

public class IncomeMatchesInputPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "100000";
        String secondPredicateInput = "200000";

        IncomeMatchesInputPredicate firstPredicate = new IncomeMatchesInputPredicate(firstPredicateInput);
        IncomeMatchesInputPredicate secondPredicate = new IncomeMatchesInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal income
        IncomeMatchesInputPredicate firstPredicateCopy = new IncomeMatchesInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal income
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_incomeMatchesInput_returnsTrue() {
        IncomeMatchesInputPredicate predicate = new IncomeMatchesInputPredicate("100000");

        // EP: same income value
        assertTrue(predicate.test(new PersonBuilder().withIncome("100000").build()));

        // EP: same income bracket
        assertTrue(predicate.test(new PersonBuilder().withIncome("120000").build())); // Boundary value
    }

    @Test
    public void test_incomeDoesNotMatchInput_returnsFalse() {
        IncomeMatchesInputPredicate predicate = new IncomeMatchesInputPredicate("200000");

        // EP: different income bracket
        assertFalse(predicate.test(new PersonBuilder().withIncome("200001").build())); // Boundary value

        assertFalse(predicate.test(new PersonBuilder().withIncome("160000").build())); // Boundary value
    }
}
