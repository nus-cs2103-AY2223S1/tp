package seedu.guest.model.util;

import seedu.guest.model.GuestBook;
import seedu.guest.model.ReadOnlyGuestBook;
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
        return new Guest[] {
            new Guest(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new NumberOfGuests("1")),
            new Guest(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new NumberOfGuests("4")),
            new Guest(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new NumberOfGuests("2")),
            new Guest(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new NumberOfGuests("3")),
            new Guest(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new NumberOfGuests("1")),
            new Guest(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new NumberOfGuests("4"))
        };
    }

    public static ReadOnlyGuestBook getSampleGuestBook() {
        GuestBook sampleAb = new GuestBook();
        for (Guest sampleGuest : getSampleGuests()) {
            sampleAb.addGuest(sampleGuest);
        }
        return sampleAb;
    }

}
