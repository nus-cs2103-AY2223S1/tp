package longtimenosee.model.policy.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PolicyBuilder;

public class CoverageMatchesInputPredicateTest {
    @Test
    public void test_equals() {
        List<String> firstPredicateInput = new ArrayList<>(List.of("LIFE"));
        List<String> secondPredicateInput = new ArrayList<>(List.of("INSURANCE"));

        CoverageMatchesInputPredicate firstPredicate = new CoverageMatchesInputPredicate(firstPredicateInput);
        CoverageMatchesInputPredicate secondPredicate = new CoverageMatchesInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal Coverage
        CoverageMatchesInputPredicate firstPredicateCopy = new CoverageMatchesInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal email
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_coverageMatchesInput_returnsTrue() {
        CoverageMatchesInputPredicate predicate = new CoverageMatchesInputPredicate(new ArrayList<>(List.of("LIFE")));

        // EP: matches exact coverage
        assertTrue(predicate.test(new PolicyBuilder().withCoverages("LIFE").build()));
    }

    @Test
    public void test_emailDoesNotMatchInput_returnsFalse() {
        CoverageMatchesInputPredicate predicate = new CoverageMatchesInputPredicate(
                new ArrayList<>(List.of("INSURANCE")));

        // EP: does not match email
        assertFalse(predicate.test(new PolicyBuilder().withCoverages("LIFE").build()));
    }
}
