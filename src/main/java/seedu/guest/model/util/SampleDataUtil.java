package seedu.guest.model.util;

import seedu.guest.model.GuestBook;
import seedu.guest.model.ReadOnlyGuestBook;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;

/**
 * Contains utility methods for populating {@code GuestBook} with sample data.
 */
public class SampleDataUtil {
    public static Guest[] getSampleGuests() {
        return new Guest[]{
            new Guest(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new DateRange("13/09/22 - 15/09/22"),
                    new NumberOfGuests("1")),
            new Guest(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new DateRange("01/01/23 - 07/01/23"),
                    new NumberOfGuests("4")),
            new Guest(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new DateRange("21/10/22 - 22/10/22"),
                    new NumberOfGuests("2")),
            new Guest(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new DateRange("08/04/23 - 22/04/23"),
                    new NumberOfGuests("3")),
            new Guest(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new DateRange("29/05/23 - 03/06/23"),
                    new NumberOfGuests("1")),
            new Guest(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new DateRange("30/12/22 - 02/01/23"),
                    new NumberOfGuests("4"))
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
