package tracko.testutil;

import static tracko.logic.commands.CommandTestUtil.VALID_ITEM_NAME_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_BOB;
import static tracko.testutil.TypicalItems.*;

import tracko.model.order.ItemQuantityPair;

/**
 * A utility class containing a list of {@code ItemQuantityPair} objects to be used in tests.
 */
public class TypicalItemQuantityPairs {

    public static final ItemQuantityPair PAIR_1 = new ItemQuantityPairBuilder()
        .withItem(ITEM_1).withQuantity(1).build();
    public static final ItemQuantityPair PAIR_2 = new ItemQuantityPairBuilder()
        .withItem(ITEM_2).withQuantity(2).build();
    public static final ItemQuantityPair PAIR_3 = new ItemQuantityPairBuilder()
        .withItem(ITEM_3).withQuantity(3).build();
    public static final ItemQuantityPair PAIR_4 = new ItemQuantityPairBuilder()
        .withItem(ITEM_4).withQuantity(4).build();
    public static final ItemQuantityPair PAIR_5 = new ItemQuantityPairBuilder()
        .withItem(ITEM_5).withQuantity(5).build();
    public static final ItemQuantityPair PAIR_6 = new ItemQuantityPairBuilder()
        .withItem(ITEM_6).withQuantity(6).build();
    public static final ItemQuantityPair PAIR_7 = new ItemQuantityPairBuilder()
        .withItem(ITEM_7).withQuantity(7).build();
    public static final ItemQuantityPair PAIR_8 = new ItemQuantityPairBuilder()
        .withItem(ITEM_8).withQuantity(8).build();
    public static final ItemQuantityPair PAIR_9 = new ItemQuantityPairBuilder()
        .withItem(ITEM_9).withQuantity(9).build();
    public static final ItemQuantityPair PAIR_10 = new ItemQuantityPairBuilder()
        .withItem(ITEM_10).withQuantity(VALID_ITEM_QUANTITY_AMY).build();
    public static final ItemQuantityPair PAIR_11 = new ItemQuantityPairBuilder()
        .withItem(ITEM_11).withQuantity(VALID_ITEM_QUANTITY_BOB).build();

}
