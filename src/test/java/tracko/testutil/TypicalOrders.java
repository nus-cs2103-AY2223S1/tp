package tracko.testutil;

import static tracko.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static tracko.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_1;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_2;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_3;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_4;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_5;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_6;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_7;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_8;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_9;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_10;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tracko.model.TrackO;
import tracko.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {

    public static final Order ORDER_1 = new OrderBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withItemQuantityPair(PAIR_1).build();
    public static final Order ORDER_2 = new OrderBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withItemQuantityPair(PAIR_2).build();
    public static final Order ORDER_3 = new OrderBuilder().withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com")
            .withAddress("wall street").withItemQuantityPair(PAIR_3).build();
    public static final Order ORDER_4 = new OrderBuilder().withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withAddress("10th street").withItemQuantityPair(PAIR_4).build();
    public static final Order ORDER_5 = new OrderBuilder().withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com")
            .withAddress("michegan ave").withItemQuantityPair(PAIR_5).build();
    public static final Order ORDER_6 = new OrderBuilder().withName("Fiona Kunz")
            .withPhone("9482427").withEmail("lydia@example.com")
            .withAddress("little tokyo").withItemQuantityPair(PAIR_6).build();
    public static final Order ORDER_7 = new OrderBuilder().withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com")
            .withAddress("4th street").withItemQuantityPair(PAIR_7).build();

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
     * Returns an {@code AddressBook} with all the typical Orders.
     */
    public static TrackO getTrackOWithTypicalOrders() {
        TrackO trackO = new TrackO();
        for (Order Order : getTypicalOrders()) {
            trackO.addOrder(Order);
        }
        return trackO;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_1, ORDER_2, ORDER_3, ORDER_4, ORDER_5, ORDER_6, ORDER_7));
    }
}
