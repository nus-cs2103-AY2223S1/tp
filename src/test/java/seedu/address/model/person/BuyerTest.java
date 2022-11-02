package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse; //import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.BOB;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class BuyerTest {

    @Test
    public void addOrder() {
        Buyer buyer = new PersonBuilder().buildBuyer();
        Order order = new Order(buyer, new PriceRange(new Price(50), new Price(100)), new Request(new Age(1),
                new Color("brown"), new ColorPattern("brown and white"), new Species("cat")),
                new AdditionalRequests("cute"), LocalDate.now(), new Price(100));
        List<UniqueId> expectedList = Arrays.asList(order).stream().map(x -> x.getId()).collect(Collectors.toList());
        buyer.addOrders(Arrays.asList(order).stream().map(x -> x.getId()).collect(Collectors.toList()));
        assertTrue(buyer.getOrderIds().size() == 1);
        assertEquals(buyer.getOrderIds(), expectedList);
    }

    @Test
    public void isSameBuyer() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Buyer editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .buildBuyer();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).buildBuyer();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Buyer editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).buildBuyer();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).buildBuyer();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Buyer aliceCopy = new PersonBuilder(TypicalPersons.ALICE).buildBuyer();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).buildBuyer();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).buildBuyer();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).buildBuyer();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).buildBuyer();
        assertFalse(ALICE.equals(editedAlice));

    }

    //    @Test
    //    public void equals_differentOrders() {
    //        Buyer buyer1 = new PersonBuilder().buildBuyer();
    //        Buyer buyer2 = new PersonBuilder().buildBuyer();
    //        Order order1 = new Order(ALICE, new PriceRange(new Price(100), new Price(1000)),
    //                new Request(new Age(1), new Color("black"), new ColorPattern("black and white"),
    //                        new Species("cat")), new AdditionalRequests("cute"), LocalDate.now(),
    //                new Price(100));
    //        Order order2 = new Order(ALICE, new PriceRange(new Price(1), new Price(100)),
    //                new Request(new Age(1), new Color("brown"), new ColorPattern("brown and white"),
    //                        new Species("cat")), new AdditionalRequests("pretty"), LocalDate.now(),
    //                new Price(100));
    //        buyer1.addOrder(order1);
    //        buyer2.addOrder(order2);
    //        assertNotEquals(order1, order2);
    //    }

    @Test
    public void hashCode_differentObjects() {
        Buyer buyer1 = new PersonBuilder().buildBuyer();
        Buyer buyer2 = new PersonBuilder().buildBuyer();
        assertEquals(buyer1.hashCode(), buyer2.hashCode());
    }

    //    @Test
    //    public void toString_differentSuppliers() {
    //        Buyer buyer1 = new PersonBuilder().buildBuyer();
    //        Buyer buyer2 = new PersonBuilder().buildBuyer();
    //        Order order = new Order(ALICE, new PriceRange(new Price(50), new Price(100)),
    //                new Request(new Age(1), new Color("brown"), new ColorPattern("brown and white"),
    //                        new Species("cat")), new AdditionalRequests("cute"), LocalDate.now(),
    //                new Price(100));
    //        buyer1.addOrder(order);
    //        buyer2.addOrder(order);
    //        assertEquals(buyer1.toString(), buyer2.toString());
    //    }
}
