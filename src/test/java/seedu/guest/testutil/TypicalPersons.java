package seedu.guest.testutil;

import static seedu.guest.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NUMBER_OF_GUESTS_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NUMBER_OF_GUESTS_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.guest.model.GuestBook;
import seedu.guest.model.guest.Guest;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Guest ALICE = new GuestBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com").withNumberOfGuests("1")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Guest BENSON = new GuestBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withNumberOfGuests("2").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Guest CARL = new GuestBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withNumberOfGuests("3").withAddress("wall street").build();
    public static final Guest DANIEL = new GuestBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withNumberOfGuests("4").withAddress("10th street").withTags("friends")
            .build();
    public static final Guest ELLE = new GuestBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withNumberOfGuests("1").withAddress("michegan ave").build();
    public static final Guest FIONA = new GuestBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withNumberOfGuests("2").withAddress("little tokyo").build();
    public static final Guest GEORGE = new GuestBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withNumberOfGuests("3").withAddress("4th street").build();

    // Manually added
    public static final Guest HOON = new GuestBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withNumberOfGuests("1").withAddress("little india").build();
    public static final Guest IDA = new GuestBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withNumberOfGuests("4").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Guest AMY = new GuestBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withNumberOfGuests(VALID_NUMBER_OF_GUESTS_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Guest BOB = new GuestBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withNumberOfGuests(VALID_NUMBER_OF_GUESTS_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static GuestBook getTypicalAddressBook() {
        GuestBook ab = new GuestBook();
        for (Guest guest : getTypicalPersons()) {
            ab.addGuest(guest);
        }
        return ab;
    }

    public static List<Guest> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
