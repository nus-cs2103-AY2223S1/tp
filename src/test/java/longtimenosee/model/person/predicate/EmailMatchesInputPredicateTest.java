package longtimenosee.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PersonBuilder;

public class EmailMatchesInputPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "alex@example.com";
        String secondPredicateInput = "bernice@email.abc";

        EmailMatchesInputPredicate firstPredicate = new EmailMatchesInputPredicate(firstPredicateInput);
        EmailMatchesInputPredicate secondPredicate = new EmailMatchesInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal email
        EmailMatchesInputPredicate firstPredicateCopy = new EmailMatchesInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal email
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailMatchesInput_returnsTrue() {
        EmailMatchesInputPredicate predicate = new EmailMatchesInputPredicate("alex@example.com");

        // EP: matches exact email
        assertTrue(predicate.test(new PersonBuilder().withEmail("alex@example.com").build()));

        // EP: matches email with different casing
        assertTrue(predicate.test(new PersonBuilder().withEmail("alEX@examPLE.com").build()));
    }

    @Test
    public void test_emailDoesNotMatchInput_returnsFalse() {
        EmailMatchesInputPredicate predicate = new EmailMatchesInputPredicate("alex@example.com");

        // EP: matches partial email
        assertFalse(predicate.test(new PersonBuilder().withEmail("alex@email.abc").build()));

        // EP: does not match email
        assertFalse(predicate.test(new PersonBuilder().withAddress("t@b.c").build()));
    }
}
