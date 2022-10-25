package tracko.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.FindOrderCommand;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;
import tracko.model.order.OrderMatchesFlagsAndPrefixPredicate;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

public class FindOrderCommandTest {
    private Model model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());

    @Test
    public void equals() {
        List<String> firstPredicateNameKeywordList = Collections.singletonList("Alice");
        List<String> firstPredicateAddressKeywordList = Collections.singletonList("Clementi");
        List<String> firstPredicateItemKeywordList = Collections.singletonList("Keychain");
        Boolean firstPredicateIsFilteringByPaid = false;
        Boolean firstPredicateIsFilteringByDelivered = true;
        Boolean firstPredicateIsPaid = false;
        Boolean firstPredicateIsDelivered = true;
        List<String> secondPredicateNameKeywordList = Arrays.asList("Alice", "Bob");
        List<String> secondPredicateAddressKeywordList = Collections.singletonList("Clementi, Geylang");
        List<String> secondPredicateItemKeywordList = Collections.singletonList("keychain, pillow");
        Boolean secondPredicateIsFilteringByPaid = false;
        Boolean secondPredicateIsFilteringByDelivered = false;
        Boolean secondPredicateIsPaid = false;
        Boolean secondPredicateIsDelivered = false;

        OrderMatchesFlagsAndPrefixPredicate firstPredicate =
                new OrderMatchesFlagsAndPrefixPredicate(firstPredicateNameKeywordList,
                        firstPredicateAddressKeywordList, firstPredicateItemKeywordList,
                        firstPredicateIsFilteringByPaid, firstPredicateIsFilteringByDelivered,
                        firstPredicateIsPaid, firstPredicateIsDelivered);
        OrderMatchesFlagsAndPrefixPredicate secondPredicate =
                new OrderMatchesFlagsAndPrefixPredicate(secondPredicateNameKeywordList,
                        secondPredicateAddressKeywordList, secondPredicateItemKeywordList,
                        secondPredicateIsFilteringByPaid, secondPredicateIsFilteringByDelivered,
                        secondPredicateIsPaid, secondPredicateIsDelivered);

        FindOrderCommand findFirstCommand = new FindOrderCommand(firstPredicate);
        FindOrderCommand findSecondCommand = new FindOrderCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
}
