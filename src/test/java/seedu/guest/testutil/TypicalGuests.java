package seedu.guest.testutil;

import static seedu.guest.logic.commands.CommandTestUtil.VALID_DATE_RANGE_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_DATE_RANGE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_IS_ROOM_CLEAN_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_IS_ROOM_CLEAN_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NUMBER_OF_GUESTS_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NUMBER_OF_GUESTS_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_ROOM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.guest.model.GuestBook;
import seedu.guest.model.guest.Guest;

/**
 * A utility class containing a list of {@code Guest} objects to be used in tests.
 */
public class TypicalGuests {
    public static final Guest ALICE = new GuestBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withRoom("05-73")
            .withDateRange("13/09/22 - 15/09/22").withNumberOfGuests("1")
            .withPhone("94351253").withIsRoomClean("yes")
            .build();
    public static final Guest BENSON = new GuestBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withRoom("03-23")
            .withDateRange("30/09/22 - 02/10/22").withNumberOfGuests("2")
            .withPhone("98765432").withIsRoomClean("yes")
            .build();
    public static final Guest CARL = new GuestBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withRoom("05-25").withDateRange("30/12/22 - 02/01/23")
            .withNumberOfGuests("3").withIsRoomClean("no")
            .build();
    public static final Guest DANIEL = new GuestBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withRoom("04-03").withDateRange("29/05/23 - 03/06/23")
            .withNumberOfGuests("4").withIsRoomClean("yes")
            .build();
    public static final Guest ELLE = new GuestBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withRoom("03-25").withDateRange("08/04/23 - 22/04/23")
            .withNumberOfGuests("1").withIsRoomClean("no")
            .build();
    public static final Guest FIONA = new GuestBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withRoom("06-22").withDateRange("21/10/22 - 22/10/22")
            .withNumberOfGuests("2").withIsRoomClean("no")
            .build();
    public static final Guest GEORGE = new GuestBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withRoom("07-05").withDateRange("01/01/23 - 07/01/23")
            .withNumberOfGuests("3").withIsRoomClean("yes")
            .build();

    // Manually added
    public static final Guest HOON = new GuestBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withRoom("02-21").withDateRange("01/05/23 - 01/01/24")
            .withNumberOfGuests("1").withIsRoomClean("no")
            .build();
    public static final Guest IDA = new GuestBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withRoom("02-29").withDateRange("11/11/23 - 12/11/23")
            .withNumberOfGuests("4").withIsRoomClean("yes")
            .build();

    // Manually added - Guest's details found in {@code CommandTestUtil}
    public static final Guest AMY = new GuestBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withRoom(VALID_ROOM_AMY).withDateRange(VALID_DATE_RANGE_AMY)
            .withNumberOfGuests(VALID_NUMBER_OF_GUESTS_AMY)
            .withIsRoomClean(VALID_IS_ROOM_CLEAN_AMY)
            .build();
    public static final Guest BOB = new GuestBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withRoom(VALID_ROOM_BOB).withDateRange(VALID_DATE_RANGE_BOB)
            .withNumberOfGuests(VALID_NUMBER_OF_GUESTS_BOB)
            .withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalGuests() {} // prevents instantiation

    /**
     * Returns an {@code GuestBook} with all the typical guests.
     */
    public static GuestBook getTypicalGuestBook() {
        GuestBook ab = new GuestBook();
        for (Guest guest : getTypicalGuests()) {
            ab.addGuest(guest);
        }
        return ab;
    }

    public static List<Guest> getTypicalGuests() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
