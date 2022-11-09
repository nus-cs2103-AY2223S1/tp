package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.BOB;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.product.Product;
import seedu.address.model.util.ClientRebuilder;

public class ClientRebuilderTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientRebuilder(null));
    }

    @Test
    public void noChange_success() {
        Client client = new ClientRebuilder(ALICE).build();
        assertEquals(ALICE, client);
    }

    @Test
    public void withName_validName_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Name newName = new Name("ALICIA");
        Client editedClient = cr.withName(newName).build();

        assertEqualsExcept(ALICE, editedClient, Name.class);
        assertEquals(editedClient.getName(), newName);
    }

    @Test
    public void withAddress_validAddress_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Address newAddress = BOB.getAddress().get();
        Client editedClient = cr.withAddress(newAddress).build();

        assertEqualsExcept(ALICE, editedClient, Address.class);
        assertEquals(editedClient.getAddress().get(), newAddress);
    }

    @Test
    public void withPhone_validPhone_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Phone newPhone = new Phone("81234567");
        Client editedClient = cr.withPhone(newPhone).build();

        assertEqualsExcept(ALICE, editedClient, Phone.class);
        assertEquals(editedClient.getPhone(), newPhone);
    }

    @Test
    public void withEmail_validEmail_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Email newEmail = new Email("alice123@gmail.com");
        Client editedClient = cr.withEmail(newEmail).build();

        assertEqualsExcept(ALICE, editedClient, Email.class);
        assertEquals(editedClient.getEmail().get(), newEmail);
    }

    @Test
    public void withBirthday_validBirthday_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Birthday newBirthday = new Birthday(LocalDate.of(2000, 2, 28));
        Client editedClient = cr.withBirthday(newBirthday).build();

        assertEqualsExcept(ALICE, editedClient, Birthday.class);
        assertEquals(editedClient.getBirthday().get(), newBirthday);
    }

    @Test
    public void withProducts_validProducts_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Set<Product> newProducts = BENSON.getProducts();
        Client editedClient = cr.withProducts(newProducts).build();

        assertEqualsExcept(ALICE, editedClient, Product.class);
        assertEquals(editedClient.getProducts(), newProducts);
    }

    @Test
    public void withMeetings_validMeetings_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        List<Meeting> newMeetings = new ArrayList<>();
        Client editedClient = cr.withMeetings(newMeetings).build();

        assertEqualsExcept(ALICE, editedClient, Meeting.class);
        assertEquals(editedClient.getMeetings(), newMeetings);
    }

    @Test
    public void withMultipleField_validEmailBirthdayProductsMeetings_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Email newEmail = new Email("alice123@gmail.com");
        Birthday newBirthday = new Birthday(LocalDate.of(2000, 2, 28));
        Set<Product> newProducts = BENSON.getProducts();
        List<Meeting> newMeetings = new ArrayList<>();
        Client editedClient = cr
                .withEmail(newEmail)
                .withBirthday(newBirthday)
                .withProducts(newProducts)
                .withMeetings(newMeetings).build();

        assertEquals(editedClient.getName(), ALICE.getName());
        assertEquals(editedClient.getAddress(), ALICE.getAddress());
        assertEquals(editedClient.getEmail().get(), newEmail);
        assertEquals(editedClient.getBirthday().get(), newBirthday);
        assertEquals(editedClient.getPhone(), ALICE.getPhone());
        assertEquals(editedClient.getProducts(), newProducts);
        assertEquals(editedClient.getMeetings(), newMeetings);
    }

    @Test
    public void addMeeting_validMeeting_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Meeting newMeeting = new Meeting(
                ALICE,
                new Description("desc"),
                new MeetingDate(LocalDate.of(2022, 12, 31)),
                new MeetingTime(LocalTime.of(10, 0)),
                new MeetingTime(LocalTime.of(10, 10)));
        Client editedClient = cr
                .addMeeting(newMeeting).build();

        assertFalse(ALICE.getMeetings().contains(newMeeting));
        assertTrue(editedClient.getMeetings().contains(newMeeting));
        assertNotEquals(editedClient.getMeetings(), ALICE.getMeetings());
    }

    @Test
    public void addRemoveMeeting_validMeeting_success() {
        Meeting newMeeting = new Meeting(
                ALICE,
                new Description("desc"),
                new MeetingDate(LocalDate.of(2023, 1, 1)),
                new MeetingTime(LocalTime.of(0, 0)),
                new MeetingTime(LocalTime.of(23, 59)));
        ClientRebuilder cr = new ClientRebuilder(ALICE);

        Client editedClient = cr.addMeeting(newMeeting).build();
        assertTrue(editedClient.getMeetings().contains(newMeeting));

        editedClient = cr.removeMeeting(newMeeting).build();
        assertFalse(editedClient.getMeetings().contains(newMeeting));
    }

    @Test
    public void addProduct_validProduct_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Product newProduct = new Product("test product");
        Client editedClient = cr
                .addProduct(newProduct).build();

        assertFalse(ALICE.getProducts().contains(newProduct));
        assertTrue(editedClient.getProducts().contains(newProduct));
        assertNotEquals(editedClient.getProducts(), ALICE.getProducts());
    }

    @Test
    public void addRemoveProduct_validProduct_success() {
        Product newProduct = new Product("test product");
        ClientRebuilder cr = new ClientRebuilder(ALICE);

        Client editedClient = cr.addProduct(newProduct).build();
        assertTrue(editedClient.getProducts().contains(newProduct));

        editedClient = cr.removeProduct(newProduct).build();
        assertFalse(editedClient.getProducts().contains(newProduct));
    }

    private static <T> void assertEqualsExcept(Client original, Client edited, Class<T> except) {
        if (!(except.equals(Name.class))) {
            assertEquals(original.getName(), edited.getName());
        }
        if (!(except.equals(Phone.class))) {
            assertEquals(original.getPhone(), edited.getPhone());
        }
        if (!(except.equals(Birthday.class))) {
            assertEquals(original.getBirthday(), edited.getBirthday());
        }
        if (!(except.equals(Address.class))) {
            assertEquals(original.getAddress(), edited.getAddress());
        }
        if (!(except.equals(Email.class))) {
            assertEquals(original.getEmail(), edited.getEmail());
        }
        if (!(except.equals(Product.class))) {
            assertEquals(original.getProducts(), edited.getProducts());
        }
        if (!(except.equals(Meeting.class))) {
            assertEquals(original.getMeetings(), edited.getMeetings());
        }
    }
}
