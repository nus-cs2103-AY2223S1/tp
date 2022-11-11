package longtimenosee.model.policy.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PolicyBuilder;

public class CompanyMatchesInputPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "MNF";
        String secondPredicateInput = "PRU";

        CompanyMatchesInputPredicate firstPredicate = new CompanyMatchesInputPredicate(firstPredicateInput);
        CompanyMatchesInputPredicate secondPredicate = new CompanyMatchesInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal company
        CompanyMatchesInputPredicate firstPredicateCopy = new CompanyMatchesInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal company
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_incomeMatchesInput_returnsTrue() {
        CompanyMatchesInputPredicate predicate = new CompanyMatchesInputPredicate("PRU");

        // EP: same company
        assertTrue(predicate.test(new PolicyBuilder().withCompany("PRU").build()));
    }

    @Test
    public void test_incomeDoesNotMatchInput_returnsFalse() {
        CompanyMatchesInputPredicate predicate = new CompanyMatchesInputPredicate("MNF");

        // EP: different income bracket
        assertFalse(predicate.test(new PolicyBuilder().withCompany("PRU").build()));
    }
}
