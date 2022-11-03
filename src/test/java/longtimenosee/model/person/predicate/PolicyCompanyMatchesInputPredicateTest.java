package longtimenosee.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.model.person.Person;
import longtimenosee.model.policy.Policy;
import longtimenosee.testutil.AssignedPolicyBuilder;
import longtimenosee.testutil.PersonBuilder;
import longtimenosee.testutil.PolicyBuilder;

public class PolicyCompanyMatchesInputPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "AIA";
        String secondPredicateInput = "PRU";

        PolicyCompanyMatchesInputPredicate firstPredicate = new PolicyCompanyMatchesInputPredicate(firstPredicateInput);
        PolicyCompanyMatchesInputPredicate secondPredicate =
                new PolicyCompanyMatchesInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal company
        PolicyCompanyMatchesInputPredicate firstPredicateCopy =
                new PolicyCompanyMatchesInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal company
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_policyCompanyMatchesInput_returnsTrue() {
        // EP: same company
        PolicyCompanyMatchesInputPredicate predicate = new PolicyCompanyMatchesInputPredicate("PRU");
        Person personToTest = new PersonBuilder().build();
        Policy policy = new PolicyBuilder().withCompany("PRU").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_policyCompanyDoesNotMatchInput_returnsFalse() {
        // EP: different company
        PolicyCompanyMatchesInputPredicate predicate = new PolicyCompanyMatchesInputPredicate("AIA");
        Person personToTest = new PersonBuilder().build();
        Policy policy = new PolicyBuilder().withCompany("PRU").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());
        assertFalse(predicate.test(personToTest));
    }
}
