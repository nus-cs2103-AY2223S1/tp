package seedu.guest.model.util;

import seedu.guest.model.GuestBook;
import seedu.guest.model.ReadOnlyGuestBook;
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

/**
 * Contains utility methods for populating {@code GuestBook} with sample data.
 */
public class SampleDataUtil {
    public static Guest[] getSampleGuests() {
        return new Guest[]{
            new Guest(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Room("05-73"), new DateRange("11/11/22 - 15/11/22"), new NumberOfGuests("1"),
                    new IsRoomClean("yes"), new Bill(), new Request()),
            new Guest(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Room("06-84"), new DateRange("09/11/22 - 13/11/22"), new NumberOfGuests("4"),
                    new IsRoomClean("no"), new Bill("10"), new Request("Change bed")),
            new Guest(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Room("03-68"), new DateRange("05/10/22 - 27/11/22"), new NumberOfGuests("2"),
                    new IsRoomClean("no"), new Bill("19.99"), new Request("Room service")),
            new Guest(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Room("02-50"), new DateRange("09/11/22 - 12/11/22"), new NumberOfGuests("3"),
                    new IsRoomClean("yes"), new Bill("50.50"), new Request("Extra pillow")),
            new Guest(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Room("09-72"), new DateRange("29/05/22 - 01/04/23"), new NumberOfGuests("1"),
                    new IsRoomClean("no"), new Bill("100"), new Request("Late checkout")),
            new Guest(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Room("04-23"), new DateRange("30/05/22 - 22/11/22"), new NumberOfGuests("4"),
                    new IsRoomClean("yes"), new Bill("1000.01"), new Request("Unclog toilet"))
        };
    }

    public static ReadOnlyGuestBook getSampleGuestBook() {
        GuestBook sampleGb = new GuestBook();
        for (Guest sampleGuest : getSampleGuests()) {
            sampleGb.addGuest(sampleGuest);
        }
        return sampleGb;
    }
}
