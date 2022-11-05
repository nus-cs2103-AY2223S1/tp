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

public class PolicyTitleContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateInput = List.of("Health");
        List<String> secondPredicateInput = List.of("Life", "Plan");

        PolicyTitleContainsKeywordsPredicate firstPredicate =
                new PolicyTitleContainsKeywordsPredicate(firstPredicateInput);
        PolicyTitleContainsKeywordsPredicate secondPredicate =
                new PolicyTitleContainsKeywordsPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal keywords
        PolicyTitleContainsKeywordsPredicate firstPredicateCopy =
                new PolicyTitleContainsKeywordsPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal keywords
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_policyTitleContainsInput_returnsTrue() {
        // EP: single keyword
        PolicyTitleContainsKeywordsPredicate predicate = new PolicyTitleContainsKeywordsPredicate(List.of("Life"));
        Person personToTest = new PersonBuilder().build();
        Policy policy = new PolicyBuilder().withTitle("Life").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());
        assertTrue(predicate.test(personToTest));

        // EP: Title contains single keyword
        predicate = new PolicyTitleContainsKeywordsPredicate(List.of("Life"));
        personToTest = new PersonBuilder().build();
        policy = new PolicyBuilder().withTitle("Life Plan").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());
        assertTrue(predicate.test(personToTest));

        // EP: Title contains multiple keywords
        predicate = new PolicyTitleContainsKeywordsPredicate(List.of("Life", "Plan"));
        personToTest = new PersonBuilder().build();
        policy = new PolicyBuilder().withTitle("Life Plan 101").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());
        assertTrue(predicate.test(personToTest));

        // EP: Title contains at least one keyword
        predicate = new PolicyTitleContainsKeywordsPredicate(List.of("Life", "Health"));
        personToTest = new PersonBuilder().build();
        policy = new PolicyBuilder().withTitle("Life Plan 101").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());
        assertTrue(predicate.test(personToTest));

        // EP: Title contains keyword with different casing
        predicate = new PolicyTitleContainsKeywordsPredicate(List.of("LifE"));
        personToTest = new PersonBuilder().build();
        policy = new PolicyBuilder().withTitle("Life Plan 101").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_policyTitleDoesNotContainInput_returnsFalse() {
        Person personToTest = new PersonBuilder().build();
        Policy policy = new PolicyBuilder().withTitle("Life plan").build();
        personToTest.addPolicy(new AssignedPolicyBuilder().withPolicy(policy).build());

        // EP: Title does not contain any keywords
        PolicyTitleContainsKeywordsPredicate predicate = new PolicyTitleContainsKeywordsPredicate(List.of("Health"));
        assertFalse(predicate.test(personToTest));

        // EP: Title only partially contains input
        predicate = new PolicyTitleContainsKeywordsPredicate(List.of("ife"));
        assertFalse(predicate.test(personToTest));
    }
}
