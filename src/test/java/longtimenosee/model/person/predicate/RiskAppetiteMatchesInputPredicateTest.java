package longtimenosee.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PersonBuilder;

public class RiskAppetiteMatchesInputPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "H";
        String secondPredicateInput = "M";

        RiskAppetiteMatchesInputPredicate firstPredicate = new RiskAppetiteMatchesInputPredicate(firstPredicateInput);
        RiskAppetiteMatchesInputPredicate secondPredicate = new RiskAppetiteMatchesInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal risk appetite
        RiskAppetiteMatchesInputPredicate firstPredicateCopy =
                new RiskAppetiteMatchesInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal risk appetite
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_riskAppetiteMatchesInput_returnsTrue() {
        // EP: same risk appetite H
        RiskAppetiteMatchesInputPredicate predicate = new RiskAppetiteMatchesInputPredicate("H");
        assertTrue(predicate.test(new PersonBuilder().withRiskAppetite("H").build()));

        // EP: same risk appetite M
        predicate = new RiskAppetiteMatchesInputPredicate("M");
        assertTrue(predicate.test(new PersonBuilder().withRiskAppetite("M").build()));

        // EP: same risk appetite L
        predicate = new RiskAppetiteMatchesInputPredicate("L");
        assertTrue(predicate.test(new PersonBuilder().withRiskAppetite("L").build()));
    }

    @Test
    public void test_riskAppetiteDoesNotMatchInput_returnsFalse() {
        RiskAppetiteMatchesInputPredicate predicate = new RiskAppetiteMatchesInputPredicate("H");

        // EP: different risk appetite
        assertFalse(predicate.test(new PersonBuilder().withRiskAppetite("M").build()));

        assertFalse(predicate.test(new PersonBuilder().withRiskAppetite("L").build()));
    }
}
