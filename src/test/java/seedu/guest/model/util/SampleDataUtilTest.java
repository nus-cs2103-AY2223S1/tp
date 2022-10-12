package seedu.guest.model.util;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.guest.model.GuestBook;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;

public class SampleDataUtilTest {

    @Test
    public void getSampleGuests() {
        // different values -> return false
        Guest[] testGuests = new Guest[]{
            new Guest(new Name("James Oliver"), new Phone("98345344"), new Email("jamesoliver@example.com"),
                    new DateRange("15/09/22 - 19/09/22"),
                    new NumberOfGuests("2")),
            new Guest(new Name("Kayla Smith"), new Phone("91823471"), new Email("kaylasmith@example.com"),
                    new DateRange("22/01/23 - 25/01/23"),
                    new NumberOfGuests("3")),
            new Guest(new Name("Charlie Lim"), new Phone("88237126"),
                    new Email("charlie@example.com"), new DateRange("21/02/22 - 22/03/22"),
                    new NumberOfGuests("2")),
            new Guest(new Name("Ryan Wong"), new Phone("82831111"), new Email("wongryan@example.com"),
                    new DateRange("28/04/23 - 29/04/23"),
                    new NumberOfGuests("2")),
            new Guest(new Name("Ibrahim Irfan"), new Phone("84912293"), new Email("irfan@example.com"),
                    new DateRange("23/05/23 - 03/06/23"),
                    new NumberOfGuests("1")),
            new Guest(new Name("Dave Balakrishnan"), new Phone("92821374"), new Email("daveb@example.com"),
                    new DateRange("30/12/22 - 02/01/23"),
                    new NumberOfGuests("3"))
            };

        assertNotEquals(SampleDataUtil.getSampleGuests(), testGuests);

        // null -> returns false
        assertNotEquals(SampleDataUtil.getSampleGuests(), null);
    }

    @Test
    public void getSampleGuestBook() {
        GuestBook exceptedGb = new GuestBook();
        exceptedGb.addGuest(new Guest(new Name("Alex Yooh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new DateRange("13/09/22 - 15/09/22"),
                new NumberOfGuests("1")));
        exceptedGb.addGuest(new Guest(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new DateRange("01/01/23 - 07/01/23"),
                new NumberOfGuests("4")));
        exceptedGb.addGuest(new Guest(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new DateRange("21/10/22 - 22/10/22"),
                new NumberOfGuests("2")));
        exceptedGb.addGuest(new Guest(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new DateRange("08/04/23 - 22/04/23"),
                new NumberOfGuests("3")));
        exceptedGb.addGuest(new Guest(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new DateRange("29/05/23 - 03/06/23"),
                new NumberOfGuests("1")));
        exceptedGb.addGuest(new Guest(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"), new DateRange("30/12/22 - 02/01/23"), new NumberOfGuests("4")));

        // null -> return false
        assertNotEquals(SampleDataUtil.getSampleGuestBook(), null);
    }
}
