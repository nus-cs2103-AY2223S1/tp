package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_4_BEDROOM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GARDEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POOL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.listing.Listing;

/**
 * A utility class containing a list of {@code Listing} objects to be used in tests.
 */
public class TypicalListings {

    public static final Listing ALICE = new ListingBuilder().withId("1").withOwner("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withAskingPrice("1000000")
            .withTags("4bedroom").build();
    public static final Listing BENSON = new ListingBuilder().withId("2").withOwner("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withAskingPrice("200000")
            .withTags("pool", "backyard").build();
    public static final Listing CARL = new ListingBuilder().withId("3").withOwner("Carl Kurz")
            .withAddress("wall street").withAskingPrice("500000").build();
    public static final Listing DANIEL = new ListingBuilder().withId("4").withOwner("Daniel Meier")
            .withAddress("10th street").withAskingPrice("5000000").withTags("mansion").build();
    public static final Listing ELLE = new ListingBuilder().withId("5").withOwner("Elle Meyer")
            .withAddress("michegan ave").withAskingPrice("900000").build();
    public static final Listing FIONA = new ListingBuilder().withId("6").withOwner("Fiona Kunz")
            .withAddress("little tokyo").withAskingPrice("2000000").build();
    public static final Listing GEORGE = new ListingBuilder().withId("7").withOwner("George Best")
            .withAddress("4th street").withAskingPrice("5000000").build();

    // Manually added
    public static final Listing HOON = new ListingBuilder().withOwner("Hoon Meier")
            .withAddress("little india").build();
    public static final Listing IDA = new ListingBuilder().withOwner("Ida Mueller")
            .withAddress("chicago ave").build();

    // Manually added - Listing's details found in {@code CommandTestUtil}
    public static final Listing AMY = new ListingBuilder().withOwner(VALID_NAME_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_4_BEDROOM).build();
    public static final Listing BOB = new ListingBuilder().withOwner(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_POOL, VALID_TAG_GARDEN)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalListings() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Listings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Listing listing : getTypicalListings()) {
            ab.addListing(listing);
        }
        return ab;
    }

    public static List<Listing> getTypicalListings() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
