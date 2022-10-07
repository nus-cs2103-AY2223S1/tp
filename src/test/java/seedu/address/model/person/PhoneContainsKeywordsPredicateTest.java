package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PhoneContainsKeywordsPredicateTest {
    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Collections.singletonList("89897676"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("89897676").build()));

        // Multiple keywords
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("89897676", "34345555"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("89897676").build()));

        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("34345555", "89897676"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("89897676").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("34345555").build()));

        // Non-matching keyword
        predicate = new PhoneContainsKeywordsPredicate(List.of("20220810"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("34345555").build()));

        // Keywords match name, email and address, but does not match phone number
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com", "Main"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12346")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}