package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.order.*;
import seedu.address.model.person.Name;
import seedu.address.model.pet.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Orders} objects to be used in tests.
 */
public class TypicalOrders {
    public static final Order ORDER_ONE = new Order(TypicalBuyers.ALICE,
            new PriceRange(new Price(60.6), new Price(150.4)),
            new Request(new Age(2), new Color("white"), new ColorPattern("stripes"),
                    new Species("cat")),
            new AdditionalRequests("fluffy"),
            LocalDate.parse("2020-10-10"),
            new Price(100.2));

    public static final Order ORDER_TWO = new Order(TypicalBuyers.CARL,
            new PriceRange(new Price(40.55), new Price(300.4)),
            new Request(new Age(2), new Color("black"), new ColorPattern("polka-dots"),
                    new Species("cat")),
            new AdditionalRequests("fat"),
            LocalDate.parse("2021-09-10"),
            new Price(150.2));

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
        return new ArrayList<>(Arrays.asList(ORDER_ONE, ORDER_TWO));
    }
}
