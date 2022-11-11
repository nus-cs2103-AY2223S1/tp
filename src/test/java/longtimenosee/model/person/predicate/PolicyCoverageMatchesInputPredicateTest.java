package longtimenosee.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import longtimenosee.model.person.Person;
import longtimenosee.model.policy.Policy;
import longtimenosee.testutil.AssignedPolicyBuilder;
import longtimenosee.testutil.PersonBuilder;
import longtimenosee.testutil.PolicyBuilder;

public class PolicyCoverageMatchesInputPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateInput = List.of("MOTOR");
        List<String> secondPredicateInput = List.of("HEALTH", "TRAVEL");

        PolicyCoverageMatchesInputPredicate firstPredicate =
                new PolicyCoverageMatchesInputPredicate(firstPredicateInput);
        PolicyCoverageMatchesInputPredicate secondPredicate =
                new PolicyCoverageMatchesInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal coverages
        PolicyCoverageMatchesInputPredicate firstPredicateCopy =
                new PolicyCoverageMatchesInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal coverages
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_policyCoverageMatchesInput_returnsTrue() {
        Person personToTest = new PersonBuilder().build();
        Policy policy = new PolicyBuilder().withCoverages("LIFE", "HEALTH", "MOTOR").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());

        // EP: matching single coverage
        PolicyCoverageMatchesInputPredicate predicate = new PolicyCoverageMatchesInputPredicate(List.of("LIFE"));
        assertTrue(predicate.test(personToTest));

        // EP: matching multiple coverages
        predicate = new PolicyCoverageMatchesInputPredicate(List.of("LIFE", "MOTOR"));
        assertTrue(predicate.test(personToTest));
        predicate = new PolicyCoverageMatchesInputPredicate(List.of("MOTOR", "LIFE", "HEALTH"));
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_policyCoverageDoesNotMatchInput_returnsFalse() {
        // EP: single coverage does not match
        PolicyCoverageMatchesInputPredicate predicate = new PolicyCoverageMatchesInputPredicate(List.of("LIFE"));
        Person personToTest = new PersonBuilder().build();
        Policy policy = new PolicyBuilder().withCoverages("MOTOR").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());
        assertFalse(predicate.test(personToTest));

        // EP: partial match of coverages
        predicate = new PolicyCoverageMatchesInputPredicate(List.of("LIFE", "MOTOR", "TRAVEL"));
        personToTest = new PersonBuilder().build();
        policy = new PolicyBuilder().withCoverages("TRAVEL", "LIFE").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());
        assertFalse(predicate.test(personToTest));
    }
}
