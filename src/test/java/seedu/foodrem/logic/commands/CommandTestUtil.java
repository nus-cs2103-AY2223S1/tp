package seedu.foodrem.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.NameContainsKeywordsPredicate;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_ITEM_NAME_CARROTS = "Carrots";

    public static final String VALID_ITEM_NAME_POTATOES = "Potatoes";
    public static final String VALID_ITEM_QUANTITY_POTATOES = "10";
    public static final String VALID_ITEM_UNIT_POTATOES = "kg";
    public static final String VALID_ITEM_BOUGHT_DATE_POTATOES = "11-10-2022";
    public static final String VALID_ITEM_EXPIRY_DATE_POTATOES = "11-11-2022";
    public static final String VALID_ITEM_PRICE_POTATOES = "10.30";
    public static final String VALID_ITEM_REMARKS_POTATOES = "For Mashed Potatoes";

    public static final String VALID_ITEM_NAME_CUCUMBERS = "Cucumbers";
    public static final String VALID_ITEM_QUANTITY_CUCUMBERS = "2000";
    public static final String VALID_ITEM_UNIT_CUCUMBERS = "grams";
    public static final String VALID_ITEM_BOUGHT_DATE_CUCUMBERS = "12-10-2022";
    public static final String VALID_ITEM_EXPIRY_DATE_CUCUMBERS = "12-12-2022";
    public static final String VALID_ITEM_PRICE_CUCUMBERS = "8";
    public static final String VALID_ITEM_REMARKS_CUCUMBERS = "For Salad";

    public static final String VALID_TAG_NAME_FRUITS = "fruits";
    public static final String VALID_TAG_NAME_FRUITS_WITH_WHITESPACES = "f r u i t s";
    public static final String VALID_TAG_NAME_NUMBERS = "2 1 0 3 t";
    public static final String VALID_TAG_NAME_VEGETABLES = "vegetables";

    public static final String VALID_ID_NUMBER_ONE = "1";

    public static final String VALID_DESC_ITEM_NAME_POTATOES = " " + CliSyntax.PREFIX_NAME
            + VALID_ITEM_NAME_POTATOES;
    public static final String VALID_DESC_ITEM_QUANTITY_POTATOES = " " + CliSyntax.PREFIX_ITEM_QUANTITY
            + VALID_ITEM_QUANTITY_POTATOES;
    public static final String VALID_DESC_ITEM_UNIT_POTATOES = " " + CliSyntax.PREFIX_ITEM_UNIT
            + VALID_ITEM_UNIT_POTATOES;
    public static final String VALID_DESC_ITEM_BOUGHT_DATE_POTATOES = " " + CliSyntax.PREFIX_ITEM_BOUGHT_DATE
            + VALID_ITEM_BOUGHT_DATE_POTATOES;
    public static final String VALID_DESC_ITEM_EXPIRY_DATE_POTATOES = " " + CliSyntax.PREFIX_ITEM_EXPIRY_DATE
            + VALID_ITEM_EXPIRY_DATE_POTATOES;
    public static final String VALID_DESC_ITEM_PRICE_POTATOES = " " + CliSyntax.PREFIX_ITEM_PRICE
            + VALID_ITEM_PRICE_POTATOES;
    public static final String VALID_DESC_ITEM_REMARKS_POTATOES = " " + CliSyntax.PREFIX_ITEM_REMARKS
            + VALID_ITEM_REMARKS_POTATOES;

    public static final String VALID_DESC_ITEM_NAME_CUCUMBERS = " " + CliSyntax.PREFIX_NAME
            + VALID_ITEM_NAME_CUCUMBERS;
    public static final String VALID_DESC_ITEM_QUANTITY_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_QUANTITY
            + VALID_ITEM_QUANTITY_CUCUMBERS;
    public static final String VALID_DESC_ITEM_UNIT_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_UNIT
            + VALID_ITEM_UNIT_CUCUMBERS;
    public static final String VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_BOUGHT_DATE
            + VALID_ITEM_BOUGHT_DATE_CUCUMBERS;
    public static final String VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_EXPIRY_DATE
            + VALID_ITEM_EXPIRY_DATE_CUCUMBERS;
    public static final String VALID_DESC_ITEM_PRICE_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_PRICE
            + VALID_ITEM_PRICE_CUCUMBERS;
    public static final String VALID_DESC_ITEM_REMARKS_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_REMARKS
            + VALID_ITEM_REMARKS_CUCUMBERS;

    public static final String VALID_DESC_TAG_NAME_FRUITS = " " + CliSyntax.PREFIX_NAME
            + VALID_TAG_NAME_FRUITS;

    public static final String VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES = " " + CliSyntax.PREFIX_NAME
            + VALID_TAG_NAME_FRUITS_WITH_WHITESPACES;

    public static final String VALID_DESC_TAG_NAME_NUMBERS = " " + CliSyntax.PREFIX_NAME
            + VALID_TAG_NAME_NUMBERS;

    public static final String VALID_DESC_ID_FIRST = " " + VALID_ID_NUMBER_ONE;

    public static final String INVALID_ITEM_NAME_POTATOES = "Potatoes||";
    public static final String INVALID_ITEM_QUANTITY_POTATOES = "1@0";
    public static final String INVALID_ITEM_UNIT_POTATOES = "kg||";
    public static final String INVALID_ITEM_BOUGHT_DATE_POTATOES = "11-11-202@2";
    public static final String INVALID_ITEM_EXPIRY_DATE_POTATOES = "11-11-202@2";

    public static final String INVALID_ITEM_NAME_CUCUMBERS = "Cucumbers||";
    public static final String INVALID_ITEM_QUANTITY_CUCUMBERS = "2$00@0";
    public static final String INVALID_ITEM_UNIT_CUCUMBERS = "grams||";
    public static final String INVALID_ITEM_BOUGHT_DATE_CUCUMBERS = "12-12-202@2";
    public static final String INVALID_ITEM_EXPIRY_DATE_CUCUMBERS = "12-12-202@2";
    public static final String INVALID_ITEM_PRICE_CUCUMBERS = "1/2";
    public static final String INVALID_ITEM_REMARKS_CUCUMBERS = "}|||///||";

    public static final String INVALID_TAG_NAME_DISALLOWED_PUNCTUATION = "Frui//ts";
    public static final String INVALID_TAG_NAME_EXCEED_CHAR_LIMIT =
            "vegetablesvegetablesvegetablesvegetablesvegetables";

    public static final String INVALID_DESC_ITEM_NAME_CUCUMBERS = " " + CliSyntax.PREFIX_NAME
            + INVALID_ITEM_NAME_CUCUMBERS;
    public static final String INVALID_DESC_ITEM_QUANTITY_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_QUANTITY
            + INVALID_ITEM_QUANTITY_CUCUMBERS;
    public static final String INVALID_DESC_ITEM_UNIT_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_UNIT
            + INVALID_ITEM_UNIT_CUCUMBERS;
    public static final String INVALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_BOUGHT_DATE
            + INVALID_ITEM_BOUGHT_DATE_CUCUMBERS;
    public static final String INVALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_EXPIRY_DATE
            + INVALID_ITEM_EXPIRY_DATE_CUCUMBERS;
    public static final String INVALID_DESC_ITEM_PRICE_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_PRICE
            + INVALID_ITEM_PRICE_CUCUMBERS;
    public static final String INVALID_DESC_ITEM_REMARKS_CUCUMBERS = " " + CliSyntax.PREFIX_ITEM_REMARKS
            + INVALID_ITEM_REMARKS_CUCUMBERS;

    public static final String INVALID_DESC_ITEM_NAME_POTATOES = " " + CliSyntax.PREFIX_NAME
            + INVALID_ITEM_NAME_POTATOES;
    public static final String INVALID_DESC_ITEM_QUANTITY_POTATOES = " " + CliSyntax.PREFIX_ITEM_QUANTITY
            + INVALID_ITEM_QUANTITY_POTATOES;
    public static final String INVALID_DESC_ITEM_UNIT_POTATOES = " " + CliSyntax.PREFIX_ITEM_UNIT
            + INVALID_ITEM_UNIT_POTATOES;
    public static final String INVALID_DESC_ITEM_BOUGHT_DATE_POTATOES = " " + CliSyntax.PREFIX_ITEM_BOUGHT_DATE
            + INVALID_ITEM_BOUGHT_DATE_POTATOES;
    public static final String INVALID_DESC_ITEM_EXPIRY_DATE_POTATOES = " " + CliSyntax.PREFIX_ITEM_EXPIRY_DATE
            + INVALID_ITEM_EXPIRY_DATE_POTATOES;

    public static final String INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION = " " + CliSyntax.PREFIX_NAME
            + INVALID_TAG_NAME_DISALLOWED_PUNCTUATION;

    public static final String INVALID_DESC_TAG_NAME_EXCEED_CHAR_LIMIT = " " + CliSyntax.PREFIX_NAME
            + INVALID_TAG_NAME_EXCEED_CHAR_LIMIT;

    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditItemDescriptor DESC_POTATOES;
    public static final EditItemDescriptor DESC_CUCUMBERS;

    static {
        DESC_POTATOES = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_POTATOES)
                .withItemQuantity(VALID_ITEM_QUANTITY_POTATOES)
                .withItemUnit(VALID_ITEM_UNIT_POTATOES)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_POTATOES)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_POTATOES)
                .withItemPrice(VALID_ITEM_PRICE_POTATOES)
                .withItemRemarks(VALID_ITEM_REMARKS_POTATOES)
                .build();
        DESC_CUCUMBERS = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_CUCUMBERS)
                .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
                .withItemUnit(VALID_ITEM_UNIT_CUCUMBERS)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS)
                .withItemPrice(VALID_ITEM_PRICE_CUCUMBERS)
                .withItemRemarks(VALID_ITEM_REMARKS_CUCUMBERS)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult<?> expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult<?> result = command.execute(actualModel);
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
    public static <T> void assertCommandSuccess(Command command, Model actualModel, T expectedOutput,
                                            Model expectedModel) {
        CommandResult<T> expectedCommandResult = CommandResult.from(expectedOutput);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - FoodRem, filtered item list and selected item in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FoodRem expectedFoodRem = new FoodRem(actualModel.getFoodRem());
        List<Item> expectedFilteredList = new ArrayList<>(actualModel.getCurrentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFoodRem, actualModel.getFoodRem());
        assertEquals(expectedFilteredList, actualModel.getCurrentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the item at the given {@code targetIndex} in the
     * {@code model}'s foodRem.
     */
    public static void showItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getCurrentList().size());

        Item item = model.getCurrentList().get(targetIndex.getZeroBased());
        final String name = item.getName().toString();
        model.updateFilteredItemList(new NameContainsKeywordsPredicate(List.of(name)));

        assertEquals(1, model.getCurrentList().size());
    }
}
