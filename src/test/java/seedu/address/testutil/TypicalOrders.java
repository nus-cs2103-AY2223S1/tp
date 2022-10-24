package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.order.Order;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {

    public static final Order ORDER_1 = new OrderBuilder().withBuyer(TypicalBuyers.ALICE)
            .withRequestedPriceRange(200.00, 400.00)
            .withRequest(new Age(1), new Color("Black"), new ColorPattern("Striped"),
                    new Species("European shorthair"))
            .withAdditionalRequests("Vaccinated", "Good with children")
            .withByDate(2022, 12, 20)
            .withSettledPrice(400.00)
            .withStatus("Pending").build();
    public static final Order ORDER_2 = new OrderBuilder().withBuyer(TypicalBuyers.BENSON)
            .withRequestedPriceRange(300.00, 700.00)
            .withRequest(new Age(2), new Color("White"), new ColorPattern("None"),
                    new Species("Shiba Inu"))
            .withAdditionalRequests("Vaccinated")
            .withByDate(2022, 11, 13)
            .withSettledPrice(640.00)
            .withStatus("Delivering").build();
    public static final Order ORDER_3 = new OrderBuilder().withBuyer(TypicalBuyers.CARL)
            .withRequestedPriceRange(450.00, 1000.00)
            .withRequest(new Age(1), new Color("Mixed"), new ColorPattern("Spotted"),
                    new Species("Calico"))
            .withAdditionalRequests("On regular flea medication")
            .withByDate(2023, 1, 2)
            .withSettledPrice(950.00)
            .withStatus("Negotiating").build();

    private TypicalOrders() {}

    /**
     * Returns an {@code AddressBook} with all the typical orders.
     */
    public static AddressBook getTypicalOrdersAddressBook() {
        AddressBook ab = new AddressBook();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_1, ORDER_2, ORDER_3));
    }
}
