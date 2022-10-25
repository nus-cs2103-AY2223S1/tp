package seedu.address.model.order.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;
import seedu.address.testutil.TypicalBuyers;

/**
 * Tests that a {@code Order}'s {@code AdditionalRequests} matches any of the keywords given.
 */
public class AdditionalRequestsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AdditionalRequestPredicate firstPredicate = new AdditionalRequestPredicate(firstPredicateKeywordList);
        AdditionalRequestPredicate secondPredicate = new AdditionalRequestPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AdditionalRequestPredicate firstPredicateCopy = new AdditionalRequestPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_additionalRequestsContainsKeywords_returnsTrue() {
        Price lowerBound = new Price(7.6);
        Price upperBound = new Price(75.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);
        Request request = new Request(new Age(1), new Color("white"), new ColorPattern("stripes"),
                new Species("cat"));
        AdditionalRequests additionalRequests = new AdditionalRequests("fluffy");
        LocalDate localDate = LocalDate.parse("2020-10-10");
        Price settledPrice = new Price(56);
        Order order = new Order(TypicalBuyers.ALICE, priceRange, request, additionalRequests, localDate, settledPrice);

        // One keyword
        AdditionalRequestPredicate predicate = new AdditionalRequestPredicate(Collections.singletonList("fluffy"));
        assertTrue(predicate.test(order));

        // Multiple keywords
        predicate = new AdditionalRequestPredicate(Arrays.asList("fluffy", "fat"));
        assertTrue(predicate.test(order));

        // Only one matching keyword
        order = new Order(TypicalBuyers.ALICE, priceRange, request,
                new AdditionalRequests(Arrays.asList("fluffy", "cute")), localDate, settledPrice);
        predicate = new AdditionalRequestPredicate(Arrays.asList("fat", "fluffy"));
        assertTrue(predicate.test(order));
    }

    @Test
    public void test_additionalRequestsDoesNotContainsKeywords_returnsFalse() {
        Price lowerBound = new Price(7.6);
        Price upperBound = new Price(75.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);
        Request request = new Request(new Age(1), new Color("white"), new ColorPattern("stripes"),
                new Species("cat"));
        AdditionalRequests additionalRequests = new AdditionalRequests("fluffy");
        LocalDate localDate = LocalDate.parse("2020-10-10");
        Price settledPrice = new Price(56);
        Order order = new Order(TypicalBuyers.ALICE, priceRange, request, additionalRequests, localDate, settledPrice);

        AdditionalRequestPredicate predicate = new AdditionalRequestPredicate(Arrays.asList("big-butt", "fat"));
        assertFalse(predicate.test(order));
    }
}
