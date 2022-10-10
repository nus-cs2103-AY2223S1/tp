package tracko.logic.commands;
//
//import static java.util.Objects.requireNonNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static tracko.commons.core.Messages.MESSAGE_ITEMS_FOUND_OVERVIEW;
//import static tracko.testutil.Assert.assertThrows;
//
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.function.Predicate;
//
//import org.junit.jupiter.api.Test;
//
//import javafx.collections.ObservableList;
//import tracko.commons.core.GuiSettings;
//import tracko.logic.commands.exceptions.CommandException;
//import tracko.logic.commands.item.FindItemCommand;
//import tracko.logic.commands.order.AddOrderCommand;
//import tracko.model.Model;
//import tracko.model.ReadOnlyTrackO;
//import tracko.model.ReadOnlyUserPrefs;
//import tracko.model.TrackO;
//import tracko.model.items.Item;
//import tracko.model.items.ItemContainsKeywordsPredicate;
//import tracko.model.order.Order;
// import tracko.model.person.Person;
//import tracko.testutil.OrderBuilder;

public class FindItemCommandTest {

//    private Model model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
//    private Model expectedModel = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
//
//    @Test
//    public void equals() {
//        NameContainsKeywordsPredicate firstPredicate =
//                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
//        NameContainsKeywordsPredicate secondPredicate =
//                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
//
//        FindOrderCommand findFirstCommand = new FindOrderCommand(firstPredicate);
//        FindOrderCommand findSecondCommand = new FindOrderCommand(secondPredicate);
//
//        // same object -> returns true
//        assertTrue(findFirstCommand.equals(findFirstCommand));
//
//        // same values -> returns true
//        FindOrderCommand findFirstCommandCopy = new FindOrderCommand(firstPredicate);
//        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(findFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(findFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(findFirstCommand.equals(findSecondCommand));
//    }
//
//    @Test
//    public void execute_zeroKeywords_noPersonFound() {
//        String expectedMessage = String.format(MESSAGE_ITEMS_FOUND_OVERVIEW, 0);
//        ItemContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindItemCommand command = new FindItemCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
//
//    @Test
//    public void execute_multipleKeywords_multiplePersonsFound() {
//        String expectedMessage = String.format(MESSAGE_ITEMS_FOUND_OVERVIEW, 3);
//        ItemContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
//        FindOrderCommand command = new FindOrderCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(ORDER_3, ORDER_5, ORDER_6), model.getFilteredPersonList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
//        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
}
