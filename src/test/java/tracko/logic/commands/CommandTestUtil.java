package tracko.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static tracko.logic.parser.CliSyntax.PREFIX_COST_PRICE;
import static tracko.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static tracko.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tracko.logic.parser.CliSyntax.PREFIX_ITEM;
import static tracko.logic.parser.CliSyntax.PREFIX_NAME;
import static tracko.logic.parser.CliSyntax.PREFIX_PHONE;
import static tracko.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static tracko.logic.parser.CliSyntax.PREFIX_SELL_PRICE;
import static tracko.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tracko.commons.core.index.Index;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.commands.item.EditItemCommand;
import tracko.logic.commands.order.EditOrderCommand;
import tracko.model.Model;
import tracko.model.TrackO;
import tracko.model.item.InventoryItem;
import tracko.model.item.ItemContainsKeywordsPredicate;
import tracko.model.order.Order;
import tracko.model.order.OrderMatchesFlagsAndPrefixPredicate;
import tracko.model.tag.Tag;
import tracko.testutil.EditItemDescriptorBuilder;
import tracko.testutil.EditOrderDescriptorBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_ITEM_NAME_PEN = "Pen";
    public static final String VALID_ITEM_NAME_ERASER = "Eraser";
    public static final Integer VALID_ITEM_QUANTITY_PEN = 2;
    public static final Integer VALID_ITEM_QUANTITY_ERASER = 1;
    public static final String VALID_ITEM_DESCRIPTION_PEN = "Blue Pen";
    public static final String VALID_ITEM_DESCRIPTION_ERASER = "Black Eraser";
    public static final String VALID_ITEM_TAG_PEN = "Limited";
    public static final String VALID_ITEM_TAG_ERASER = "New";
    public static final Double VALID_ITEM_SELL_PRICE_PEN = 5.00;
    public static final Double VALID_ITEM_SELL_PRICE_ERASER = 3.00;
    public static final Double VALID_ITEM_COST_PRICE_PEN = 2.00;
    public static final Double VALID_ITEM_COST_PRICE_ERASER = 1.00;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;

    public static final String ITEM_NAME_PEN = " " + PREFIX_ITEM + VALID_ITEM_NAME_PEN;
    public static final String QUANTITY_PEN = " " + PREFIX_QUANTITY + VALID_ITEM_QUANTITY_PEN;
    public static final String ITEM_NAME_ERASER = " " + PREFIX_ITEM + VALID_ITEM_NAME_ERASER;
    public static final String QUANTITY_ERASER = " " + PREFIX_QUANTITY + VALID_ITEM_QUANTITY_ERASER;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_ITEM_NAME_DESC = " " + PREFIX_ITEM;
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "-3";
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + " ";
    public static final String INVALID_SELL_PRICE_DESC = " " + PREFIX_SELL_PRICE + -33.00;
    public static final String INVALID_COST_PRICE_DESC = " " + PREFIX_COST_PRICE + 33.333;

    public static final String VALID_DEFAULT_ITEM_NAME = "Chair";
    public static final String VALID_DEFAULT_DESCRIPTION = "This is a wooden dining chair.";
    public static final String VALID_SECOND_DESCRIPTION = "This set of furniture require some DIY.";

    public static final Integer VALID_DEFAULT_QUANTITY = 300;

    public static final Double VALID_DEFAULT_SELL_PRICE = 60.00;
    public static final Double VALID_SECOND_SELL_PRICE = 10.00;

    public static final Double VALID_DEFAULT_COST_PRICE = 45.00;
    public static final Double VALID_SECOND_COST_PRICE = 15.00;

    public static final String ITEM_NAME_DESC_DEFAULT = " " + PREFIX_ITEM + VALID_DEFAULT_ITEM_NAME;
    public static final String QUANTITY_DESC_DEFAULT = " " + PREFIX_QUANTITY + VALID_DEFAULT_QUANTITY;
    public static final String ITEM_DESCRIPTION_DESC_DEFAULT = " " + PREFIX_DESCRIPTION + VALID_DEFAULT_DESCRIPTION;
    public static final String ITEM_DESCRIPTION_DESC_SECOND = " " + PREFIX_DESCRIPTION + VALID_SECOND_DESCRIPTION;
    public static final String SELL_PRICE_DESC_DEFAULT = " " + PREFIX_SELL_PRICE + VALID_DEFAULT_SELL_PRICE;
    public static final String SELL_PRICE_DESC_SECOND = " " + PREFIX_SELL_PRICE + VALID_SECOND_SELL_PRICE;
    public static final String COST_PRICE_DESC_DEFAULT = " " + PREFIX_COST_PRICE + VALID_DEFAULT_COST_PRICE;
    public static final String COST_PRICE_DESC_SECOND = " " + PREFIX_COST_PRICE + VALID_SECOND_COST_PRICE;


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditOrderCommand.EditOrderDescriptor DESC_AMY;
    public static final EditOrderCommand.EditOrderDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditOrderDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withItemList().build();
        DESC_BOB = new EditOrderDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withItemList().build();
    }

    public static final EditItemCommand.EditItemDescriptor DESC_PEN;
    public static final EditItemCommand.EditItemDescriptor DESC_ERASER;

    static {
        DESC_PEN = new EditItemDescriptorBuilder().withItemName(VALID_ITEM_NAME_PEN)
                .withQuantity(VALID_ITEM_QUANTITY_PEN).withDescription(VALID_ITEM_DESCRIPTION_PEN)
                .withTags(VALID_ITEM_TAG_PEN).withSellPrice(VALID_ITEM_SELL_PRICE_PEN)
                .withCostPrice(VALID_ITEM_COST_PRICE_PEN).build();
        DESC_ERASER = new EditItemDescriptorBuilder().withItemName(VALID_ITEM_NAME_ERASER)
                .withQuantity(VALID_ITEM_QUANTITY_ERASER).withDescription(VALID_ITEM_DESCRIPTION_ERASER)
                .withTags(VALID_ITEM_TAG_ERASER).withSellPrice(VALID_ITEM_SELL_PRICE_ERASER)
                .withCostPrice(VALID_ITEM_COST_PRICE_ERASER).build();
    }

    private static final List<Tag> TAGS_AMY = new ArrayList<>();

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TrackO expectedTrackO = new TrackO(actualModel.getTrackO());
        List<Order> expectedFilteredList = new ArrayList<>(actualModel.getOrderList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTrackO, actualModel.getTrackO());
    }

    /**
     * Updates {@code model}'s filtered list to show only the order at the given {@code targetIndex} in the
     * {@code model}'s order list.
     */
    public static void showOrderAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredOrderList().size());

        Order order = model.getOrderList().get(targetIndex.getZeroBased());
        // Index is at 1 because at 0, every order is initialized to have a keychain.
        final String[] splitName = order.getItemList().get(1).getItemName().split("\\s+");
        model.updateFilteredOrderList(new OrderMatchesFlagsAndPrefixPredicate(Collections.EMPTY_LIST,
                Collections.EMPTY_LIST, Collections.singletonList(splitName[0]), false,
                false, false, false));

        assertEquals(1, model.getFilteredOrderList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the item at the given {@code targetIndex} in the
     * {@code model}'s order list.
     */
    public static void showItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredItemList().size());

        InventoryItem inventoryItem = model.getInventoryList().get(targetIndex.getZeroBased());
        // Index is at 1 because at 0, every person is initialized to have a keychain.
        final String[] splitName = inventoryItem.getItemName().toString().split("\\s+");
        model.updateFilteredItemList(new ItemContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredItemListSize());
    }

}
