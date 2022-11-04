package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Events} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event SHOE_SALE = new EventBuilder().withEventTitle("Shoe sale")
            .withDate("02/04/2022").withStartTime("09:30")
            .withPurpose("$10 off all shoes").build();

    public static final Event SLIPPER_SALE = new EventBuilder().withEventTitle("Slipper Sale")
            .withDate("03/04/2022").withStartTime("10:30")
            .withPurpose("$0.50 off all slippers").build();

    public static final Event BOTTLE_SALE = new EventBuilder().withEventTitle("Bottle Sale")
            .withDate("05/02/2022").withStartTime("11:20")
            .withPurpose("$1 off all bottles").build();

    public static final Event BRUSH_SALE = new EventBuilder().withEventTitle("Brush Sale")
            .withDate("05/05/2022").withStartTime("11:50")
            .withPurpose("$0.50 off all brushes").build();

    public static final Event PAPER_SALE = new EventBuilder().withEventTitle("Paper Sale")
            .withDate("05/06/2022").withStartTime("10:25")
            .withPurpose("$1 off all papers").build();

    public static final Event PENCIL_SALE = new EventBuilder().withEventTitle("Pencil Sale")
            .withDate("06/01/2022").withStartTime("14:20")
            .withPurpose("$0.40 off all pencils").build();

    public static final Event PEN_SALE = new EventBuilder().withEventTitle("Pen Sale")
            .withDate("01/01/2023").withStartTime("15:30")
            .withPurpose("$1 off all pens").build();

    // Manually added
    public static final Event MARKER_SALE = new EventBuilder().withEventTitle("Marker Sale")
            .withDate("01/10/2022").withStartTime("16:50")
            .withPurpose("$0.70 off all markers").build();

    public static final Event CHOPSTICK_SALE = new EventBuilder().withEventTitle("Chopstick Sale")
            .withDate("04/09/2022").withStartTime("09:25")
            .withPurpose("$0.80 off all chopsticks").build();

    private TypicalEvents() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical events.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(SHOE_SALE, SLIPPER_SALE, BOTTLE_SALE, BRUSH_SALE, PAPER_SALE,
                PENCIL_SALE, PEN_SALE));
    }
}
