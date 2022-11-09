package seedu.guest.model.util;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.guest.model.GuestBook;
import seedu.guest.model.guest.Bill;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.model.guest.Request;
import seedu.guest.model.guest.Room;

public class SampleDataUtilTest {

    @Test
    public void getSampleGuests() {
        // different values -> return false
        Guest[] testGuests = new Guest[]{
            new Guest(new Name("James Oliver"), new Phone("98345344"), new Email("jamesoliver@example.com"),
                    new Room("04-73"), new DateRange("15/09/22 - 19/09/22"),
                    new NumberOfGuests("2"), new IsRoomClean("no"), new Bill(), new Request()),
            new Guest(new Name("Kayla Smith"), new Phone("91823471"), new Email("kaylasmith@example.com"),
                    new Room("05-25"), new DateRange("22/01/23 - 25/01/23"),
                    new NumberOfGuests("3"), new IsRoomClean("no"), new Bill("10"),
                    new Request("Could you help me book a room service")),
            new Guest(new Name("Charlie Lim"), new Phone("88237126"),
                    new Email("charlie@example.com"), new Room("05-63"),
                    new DateRange("21/02/22 - 22/03/22"),
                    new NumberOfGuests("2"), new IsRoomClean("yes"), new Bill("19.99"),
                    new Request("Help me to kill insects")),
            new Guest(new Name("Ryan Wong"), new Phone("82831111"), new Email("wongryan@example.com"),
                    new Room("06-23"), new DateRange("28/04/23 - 29/04/23"),
                    new NumberOfGuests("2"), new IsRoomClean("no"), new Bill("50"),
                    new Request("Change my bed")),
            new Guest(new Name("Ibrahim Irfan"), new Phone("84912293"), new Email("irfan@example.com"),
                    new Room("06-33"), new DateRange("23/05/23 - 03/06/23"),
                    new NumberOfGuests("1"), new IsRoomClean("yes"), new Bill("999.99"),
                    new Request("I want to decorate my room, could you help me?")),
            new Guest(new Name("Dave Balakrishnan"), new Phone("92821374"), new Email("daveb@example.com"),
                    new Room("07-55"), new DateRange("30/12/22 - 02/01/23"),
                    new NumberOfGuests("3"), new IsRoomClean("yes"), new Bill("1000.01"),
                    new Request("Fix television"))
            };

        assertNotEquals(SampleDataUtil.getSampleGuests(), testGuests);

        // null -> returns false
        assertNotEquals(SampleDataUtil.getSampleGuests(), null);

        // empty Guest[] -> returns false
        assertNotEquals(SampleDataUtil.getSampleGuests(), new Guest[] {});
    }

    @Test
    public void getSampleGuestBook() {
        GuestBook exceptedGb = new GuestBook();
        exceptedGb.addGuest(new Guest(new Name("James Oliver"), new Phone("98345344"),
                new Email("jamesoliver@example.com"), new Room("04-73"),
                new DateRange("15/09/22 - 19/09/22"),
                new NumberOfGuests("2"), new IsRoomClean("no"), new Bill(), new Request()));
        exceptedGb.addGuest(new Guest(new Name("Kayla Smith"), new Phone("91823471"),
                new Email("kaylasmith@example.com"), new Room("05-25"),
                new DateRange("22/01/23 - 25/01/23"),
                new NumberOfGuests("3"), new IsRoomClean("no"), new Bill("10"),
                new Request("Could you help me book a room service")));
        exceptedGb.addGuest(new Guest(new Name("Charlie Lim"), new Phone("88237126"),
                new Email("charlie@example.com"), new Room("05-63"),
                new DateRange("21/02/22 - 22/03/22"),
                new NumberOfGuests("2"), new IsRoomClean("yes"), new Bill("19.99"),
                new Request("Help me to kill insects")));
        exceptedGb.addGuest(new Guest(new Name("Ryan Wong"), new Phone("82831111"),
                new Email("wongryan@example.com"), new Room("06-23"),
                new DateRange("28/04/23 - 29/04/23"),
                new NumberOfGuests("2"), new IsRoomClean("no"), new Bill("50"),
                new Request("Change my bed")));
        exceptedGb.addGuest(new Guest(new Name("Ibrahim Irfan"), new Phone("84912293"),
                new Email("irfan@example.com"), new Room("06-33"),
                new DateRange("23/05/23 - 03/06/23"),
                new NumberOfGuests("1"), new IsRoomClean("yes"), new Bill("999.99"),
                new Request("I want to decorate my room, could you help me?")));
        exceptedGb.addGuest(new Guest(new Name("Dave Balakrishnan"), new Phone("92821374"),
                new Email("daveb@example.com"), new Room("07-55"),
                new DateRange("30/12/22 - 02/01/23"),
                new NumberOfGuests("3"), new IsRoomClean("no"), new Bill("1000.01"),
                new Request("Fix television")));

        // different values -> return false
        assertNotEquals(SampleDataUtil.getSampleGuestBook(), exceptedGb);

        // null -> return false
        assertNotEquals(SampleDataUtil.getSampleGuestBook(), null);
    }
}
