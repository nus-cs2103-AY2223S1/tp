package tracko.testutil;

import static tracko.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tracko.testutil.TypicalItems.DEFAULT_INVENTORY_ITEM;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_1;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_10;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_11;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_2;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_3;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_4;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_5;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_6;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_7;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_8;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_9;
import static tracko.testutil.TypicalItems.INVENTORY_LIST;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tracko.model.TrackO;
import tracko.model.item.InventoryList;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {
    public static final InventoryList LOADED_INVENTORY_LIST = INVENTORY_LIST;

    public static final ItemQuantityPair DEFAULT_PAIR = new ItemQuantityPairBuilder()
        .withItem(LOADED_INVENTORY_LIST.get(DEFAULT_INVENTORY_ITEM.getItemName().toString())).withQuantity(10).build();

    // Items are in the typical loaded inventory list
    public static final ItemQuantityPair PAIR_1 = new ItemQuantityPairBuilder()
        .withItem(LOADED_INVENTORY_LIST.get(INVENTORY_ITEM_1.getItemName().toString())).withQuantity(1).build();
    public static final ItemQuantityPair PAIR_2 = new ItemQuantityPairBuilder()
        .withItem(LOADED_INVENTORY_LIST.get(INVENTORY_ITEM_2.getItemName().toString())).withQuantity(2).build();
    public static final ItemQuantityPair PAIR_3 = new ItemQuantityPairBuilder()
        .withItem(LOADED_INVENTORY_LIST.get(INVENTORY_ITEM_3.getItemName().toString())).withQuantity(3).build();
    public static final ItemQuantityPair PAIR_4 = new ItemQuantityPairBuilder()
        .withItem(LOADED_INVENTORY_LIST.get(INVENTORY_ITEM_4.getItemName().toString())).withQuantity(4).build();
    public static final ItemQuantityPair PAIR_5 = new ItemQuantityPairBuilder()
        .withItem(LOADED_INVENTORY_LIST.get(INVENTORY_ITEM_5.getItemName().toString())).withQuantity(5).build();
    public static final ItemQuantityPair PAIR_6 = new ItemQuantityPairBuilder()
        .withItem(LOADED_INVENTORY_LIST.get(INVENTORY_ITEM_6.getItemName().toString())).withQuantity(6).build();
    public static final ItemQuantityPair PAIR_7 = new ItemQuantityPairBuilder()
        .withItem(LOADED_INVENTORY_LIST.get(INVENTORY_ITEM_7.getItemName().toString())).withQuantity(7).build();

    public static final ItemQuantityPair PAIR_8 = new ItemQuantityPairBuilder()
        .withItem(INVENTORY_ITEM_8).withQuantity(8).build();
    public static final ItemQuantityPair PAIR_9 = new ItemQuantityPairBuilder()
        .withItem(INVENTORY_ITEM_9).withQuantity(9).build();
    public static final ItemQuantityPair PAIR_10 = new ItemQuantityPairBuilder()
        .withItem(INVENTORY_ITEM_10).withQuantity(10).build();
    public static final ItemQuantityPair PAIR_11 = new ItemQuantityPairBuilder()
        .withItem(INVENTORY_ITEM_11).withQuantity(11).build();

    public static final Order ORDER_1 = new OrderBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTimeCreated(LocalDateTime.of(2022, 5, 12, 15, 45, 23))
            .withItemList(List.of(DEFAULT_PAIR, PAIR_1)).build();
    public static final Order ORDER_2 = new OrderBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTimeCreated(LocalDateTime.of(2022, 6, 19, 9, 23, 12))
            .withItemList(List.of(DEFAULT_PAIR, PAIR_2)).build();
    public static final Order ORDER_3 = new OrderBuilder().withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com")
            .withAddress("wall street")
            .withTimeCreated(LocalDateTime.of(2022, 7, 22, 23, 55, 13))
            .withItemList(List.of(DEFAULT_PAIR, PAIR_3)).build();
    public static final Order ORDER_4 = new OrderBuilder().withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withTimeCreated(LocalDateTime.of(2022, 8, 14, 3, 15, 44))
            .withItemList(List.of(DEFAULT_PAIR, PAIR_4)).build();
    public static final Order ORDER_5 = new OrderBuilder().withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withTimeCreated(LocalDateTime.of(2022, 9, 9, 12, 15, 54))
            .withItemList(List.of(DEFAULT_PAIR, PAIR_5)).build();
    public static final Order ORDER_6 = new OrderBuilder().withName("Fiona Kunz")
            .withPhone("9482427").withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withTimeCreated(LocalDateTime.of(2022, 9, 25, 15, 30, 21))
            .withItemList(List.of(DEFAULT_PAIR, PAIR_6)).build();
    public static final Order ORDER_7 = new OrderBuilder().withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com")
            .withAddress("4th street")
            .withTimeCreated(LocalDateTime.of(2022, 10, 11, 12, 4, 9))
            .withItemList(List.of(DEFAULT_PAIR, PAIR_7)).build();

    // Manually added
    public static final Order ORDER_8 = new OrderBuilder().withName("Hoon Meier")
            .withPhone("8482424").withEmail("stefan@example.com")
            .withAddress("little india").withItemQuantityPair(PAIR_8).build();
    public static final Order ORDER_9 = new OrderBuilder().withName("Ida Mueller")
            .withPhone("8482131").withEmail("hans@example.com")
            .withAddress("chicago ave").withItemQuantityPair(PAIR_9).build();

    // Manually added - Order's details found in {@code CommandTestUtil}
    public static final Order ORDER_10 = new OrderBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withItemQuantityPair(PAIR_10).build();
    public static final Order ORDER_11 = new OrderBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withItemQuantityPair(PAIR_11).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalOrders() {} // prevents instantiation

    /**
     * Returns an {@code TrackO} with all the typical {@code Order}.
     */
    public static TrackO getTrackOWithTypicalOrders() {
        TrackO trackO = new TrackO();
        trackO.setItems(LOADED_INVENTORY_LIST);
        for (Order order : getTypicalOrders()) {
            trackO.addOrder(order);
        }
        return trackO;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_1, ORDER_2, ORDER_3, ORDER_4, ORDER_5, ORDER_6, ORDER_7));
    }
}
