package tracko.testutil;

import tracko.model.order.ItemQuantityPair;

import static tracko.logic.commands.CommandTestUtil.*;

public class TypicalItemQuantityPairs {

    public static final ItemQuantityPair PAIR_1 = new ItemQuantityPairBuilder()
        .withItemName("Mattress").withQuantity(1).build();
    public static final ItemQuantityPair PAIR_2 = new ItemQuantityPairBuilder()
        .withItemName("Bolster").withQuantity(2).build();
    public static final ItemQuantityPair PAIR_3 = new ItemQuantityPairBuilder()
        .withItemName("Pillow").withQuantity(3).build();
    public static final ItemQuantityPair PAIR_4 = new ItemQuantityPairBuilder()
        .withItemName("Chair").withQuantity(4).build();
    public static final ItemQuantityPair PAIR_5 = new ItemQuantityPairBuilder()
        .withItemName("Table").withQuantity(5).build();
    public static final ItemQuantityPair PAIR_6 = new ItemQuantityPairBuilder()
        .withItemName("Cushion").withQuantity(6).build();
    public static final ItemQuantityPair PAIR_7 = new ItemQuantityPairBuilder()
        .withItemName("Towel").withQuantity(7).build();

    public static final ItemQuantityPair PAIR_8 = new ItemQuantityPairBuilder()
        .withItemName("Apple").withQuantity(8).build();
    public static final ItemQuantityPair PAIR_9 = new ItemQuantityPairBuilder()
        .withItemName("Pineapple").withQuantity(9).build();

    public static final ItemQuantityPair PAIR_10 = new ItemQuantityPairBuilder()
        .withItemName(VALID_ITEM_NAME_AMY).withQuantity(VALID_ITEM_QUANTITY_AMY).build();
    public static final ItemQuantityPair PAIR_11 = new ItemQuantityPairBuilder()
        .withItemName(VALID_ITEM_NAME_BOB).withQuantity(VALID_ITEM_QUANTITY_BOB).build();

}
