package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.guest.testutil.GuestBuilder;

public class GuestContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        GuestContainsKeywordsPredicate firstPredicate = new GuestContainsKeywordsPredicate(firstPredicateKeywordList);
        GuestContainsKeywordsPredicate secondPredicate = new GuestContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GuestContainsKeywordsPredicate firstPredicateCopy =
                new GuestContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        GuestContainsKeywordsPredicate predicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice Bob").build()));

        // Non-complete match keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Ali"));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        GuestContainsKeywordsPredicate predicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("98765432"));
        assertTrue(predicate.test(new GuestBuilder().withPhone("98765432").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("98765432", "12345678"));
        assertTrue(predicate.test(new GuestBuilder().withPhone("98765432").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withPhone("98765432").build()));

        // Non-matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("12345678"));
        assertFalse(predicate.test(new GuestBuilder().withPhone("98765432").build()));

        // Non-complete match keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("9876"));
        assertFalse(predicate.test(new GuestBuilder().withPhone("98765432").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        GuestContainsKeywordsPredicate predicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("alice@example.com"));
        assertTrue(predicate.test(new GuestBuilder().withEmail("alice@example.com").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("alice@example.com", "bob@example.com"));
        assertTrue(predicate.test(new GuestBuilder().withEmail("alice@example.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withEmail("alice@example.com").build()));

        // Non-matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("bob@example.com"));
        assertFalse(predicate.test(new GuestBuilder().withEmail("alice@example.com").build()));

        // Non-complete match keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("bob"));
        assertFalse(predicate.test(new GuestBuilder().withEmail("alice@example.com").build()));
    }

    @Test
    public void test_roomContainsKeywords_returnsTrue() {
        // One keyword
        GuestContainsKeywordsPredicate predicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("04-05"));
        assertTrue(predicate.test(new GuestBuilder().withRoom("04-05").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("04-05", "04-06"));
        assertTrue(predicate.test(new GuestBuilder().withRoom("04-05").build()));
    }

    @Test
    public void test_roomDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withRoom("04-05").build()));

        // Non-matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("04-06"));
        assertFalse(predicate.test(new GuestBuilder().withRoom("04-05").build()));

        // Non-complete match keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("04"));
        assertFalse(predicate.test(new GuestBuilder().withRoom("04-05").build()));
    }

    @Test
    public void test_dateRangeContainsKeywords_returnsTrue() {
        // One keyword
        GuestContainsKeywordsPredicate predicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("03/03/22"));
        assertTrue(predicate.test(new GuestBuilder().withDateRange("03/03/22 - 05/03/22").build()));

        // Multiple keywords
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("03/03/22", "05/03/22"));
        assertTrue(predicate.test(new GuestBuilder().withDateRange("03/03/22 - 05/03/22").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("03/03/22", "07/05/23"));
        assertTrue(predicate.test(new GuestBuilder().withDateRange("03/03/22 - 05/03/22").build()));
    }

    @Test
    public void test_dateRangeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withDateRange("03/03/22 - 05/03/22").build()));

        // Non-matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("07/05/23"));
        assertFalse(predicate.test(new GuestBuilder().withDateRange("03/03/22 - 05/03/22").build()));

        // Non-complete match keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("03/03"));
        assertFalse(predicate.test(new GuestBuilder().withDateRange("03/03/22 - 05/03/22").build()));
    }

    @Test
    public void test_numberOfGuestsContainsKeywords_returnsTrue() {
        // One keyword
        GuestContainsKeywordsPredicate predicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("4"));
        assertTrue(predicate.test(new GuestBuilder().withNumberOfGuests("4").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("2", "4"));
        assertTrue(predicate.test(new GuestBuilder().withNumberOfGuests("4").build()));
    }

    @Test
    public void test_numberOfGuestsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withNumberOfGuests("4").build()));

        // Non-matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("3"));
        assertFalse(predicate.test(new GuestBuilder().withNumberOfGuests("4").build()));
    }

    @Test
    public void test_isRoomCleanContainsKeywords_returnsTrue() {
        // One keyword
        GuestContainsKeywordsPredicate predicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("yes"));
        assertTrue(predicate.test(new GuestBuilder().withIsRoomClean("yes").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("yes", "no"));
        assertTrue(predicate.test(new GuestBuilder().withIsRoomClean("yes").build()));
    }

    @Test
    public void test_isRoomCleanDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withIsRoomClean("yes").build()));

        // Non-matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("no"));
        assertFalse(predicate.test(new GuestBuilder().withIsRoomClean("yes").build()));

        // Non-complete match keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("ye"));
        assertFalse(predicate.test(new GuestBuilder().withIsRoomClean("yes").build()));
    }

    @Test
    public void test_billContainsKeywords_returnsTrue() {
        // One keyword
        GuestContainsKeywordsPredicate predicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("100.00"));
        assertTrue(predicate.test(new GuestBuilder().withBill("100.00").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("100.00", "0.00"));
        assertTrue(predicate.test(new GuestBuilder().withBill("100.00").build()));
    }

    @Test
    public void test_billDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withBill("100.00").build()));

        // Non-matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("0.00"));
        assertFalse(predicate.test(new GuestBuilder().withBill("100.00").build()));

        // Non-complete match keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("100"));
        assertFalse(predicate.test(new GuestBuilder().withBill("100.00").build()));
    }

    @Test
    public void test_requestContainsKeywords_returnsTrue() {
        // One keyword
        GuestContainsKeywordsPredicate predicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("Extra"));
        assertTrue(predicate.test(new GuestBuilder().withRequest("Extra bed").build()));

        // Multiple keywords
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Extra", "bed"));
        assertTrue(predicate.test(new GuestBuilder().withRequest("Extra bed").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Extra", "pillow"));
        assertTrue(predicate.test(new GuestBuilder().withRequest("Extra bed").build()));

        // Mixed-case keywords
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("eXtRa", "BED"));
        assertTrue(predicate.test(new GuestBuilder().withRequest("Extra bed").build()));
    }

    @Test
    public void test_requestDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GuestBuilder().withRequest("Extra bed").build()));

        // Non-matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Another", "pillow"));
        assertFalse(predicate.test(new GuestBuilder().withRequest("Extra bed").build()));

        // Non-complete match keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Ext"));
        assertFalse(predicate.test(new GuestBuilder().withRequest("Extra bed").build()));
    }

    @Test
    public void test_guestContainsKeywords_returnsTrue() {
        // All keywords present
        GuestContainsKeywordsPredicate predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Alice",
                "98765432", "alice@example", "04-05", "03/03/22", "05/03/22", "4", "100.00", "Extra", "bed"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice").withPhone("98765432")
                .withEmail("alice@example").withRoom("04-05").withDateRange("03/03/22 - 05/03/22")
                .withNumberOfGuests("4").withBill("100.00").withRequest("Extra bed").build()));

        // Some keywords present
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Alice", "alice@example",
                "04-05", "05/03/22", "bed"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice").withPhone("98765432")
                .withEmail("alice@example").withRoom("04-05").withDateRange("03/03/22 - 05/03/22")
                .withNumberOfGuests("4").withBill("100.00").withRequest("Extra bed").build()));

        // One keyword present
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Alice"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice").withPhone("98765432")
                .withEmail("alice@example").withRoom("04-05").withDateRange("03/03/22 - 05/03/22")
                .withNumberOfGuests("4").withBill("100.00").withRequest("Extra bed").build()));

        // Only one matching keyword
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("Alice", "12345678", "bob@example.com",
                "04-06", "07/07/23", "09/07/23", "Another", "pillow"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice").withPhone("98765432")
                .withEmail("alice@example").withRoom("04-05").withDateRange("03/03/22 - 05/03/22")
                .withNumberOfGuests("4").withBill("100.00").withRequest("Extra bed").build()));

        // Mixed-case keywords
        predicate = new GuestContainsKeywordsPredicate(Arrays.asList("aLICe", "98765432", "aliCe@ExAmple",
                "04-05", "03/03/22", "05/03/22", "4", "100.00", "EXtrA", "bEd"));
        assertTrue(predicate.test(new GuestBuilder().withName("Alice").withPhone("98765432")
                .withEmail("alice@example").withRoom("04-05").withDateRange("03/03/22 - 05/03/22")
                .withNumberOfGuests("4").withBill("100.00").withRequest("Extra bed").build()));
    }
}
