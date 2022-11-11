package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_CUISINE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_HELP;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_LOCATION;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_NAME;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_PRICE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_TAG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.NameContainsKeywordsPredicate;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.EditEateryDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PRICE_AMY = "$";
    public static final String VALID_PRICE_BOB = "$$$";
    public static final String VALID_CUISINE_AMY = "amyexamplecom";
    public static final String VALID_CUISINE_BOB = "bobexamplecom";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PRICE_DESC_AMY = " " + PREFIX_PRICE + VALID_PRICE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PRICE + VALID_PRICE_BOB;
    public static final String CUISINE_DESC_AMY = " " + PREFIX_CUISINE + VALID_CUISINE_AMY;
    public static final String CUISINE_DESC_BOB = " " + PREFIX_CUISINE + VALID_CUISINE_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_LOCATION + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_LOCATION + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String HELP_DESC = " " + PREFIX_HELP;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME; // empty string not allowed
    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE + "$a$"; // 'a' not allowed in prices
    public static final String INVALID_CUISINE_DESC = " " + PREFIX_CUISINE + "bob!yahoo"; // "!" not allowed in cuisine
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_LOCATION; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby♥"; // '♥' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEateryDescriptor DESC_AMY;
    public static final EditCommand.EditEateryDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditEateryDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PRICE_AMY).withEmail(VALID_CUISINE_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditEateryDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PRICE_BOB).withEmail(VALID_CUISINE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

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
     * - the food guide, filtered eatery list and selected eatery in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FoodGuide expectedFoodGuide = new FoodGuide(actualModel.getFoodGuide());
        List<Eatery> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEateryList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFoodGuide, actualModel.getFoodGuide());
        assertEquals(expectedFilteredList, actualModel.getFilteredEateryList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the eatery at the given {@code targetIndex} in the
     * {@code model}'s food guide.
     */
    public static void showEateryAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEateryList().size());

        Eatery eatery = model.getFilteredEateryList().get(targetIndex.getZeroBased());
        final String[] splitName = eatery.getName().fullName.split("\\s+");
        model.updateFilteredEateryList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEateryList().size());
    }

}
