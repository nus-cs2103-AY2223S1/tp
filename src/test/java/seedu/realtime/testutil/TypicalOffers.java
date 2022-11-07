package seedu.realtime.testutil;

import static seedu.realtime.logic.commands.CommandTestUtil.VALID_LISTING_ID_1;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_LISTING_ID_2;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.realtime.model.RealTime;
import seedu.realtime.model.offer.Offer;

/**
 * A utility class containing a list of {@code Offer} objects to be used in tests.
 */
public class TypicalOffers {

    public static final Offer ALICE = new OfferBuilder().withBuyer("Alice Pauline")
            .withListing("1").withOfferPrice("1000000").build();
    public static final Offer BENSON = new OfferBuilder().withBuyer("Benson Meier")
            .withOfferPrice("200000").build();
    public static final Offer CARL = new OfferBuilder().withListing("3").withBuyer("Carl Kurz")
            .withOfferPrice("500000").build();
    public static final Offer DANIEL = new OfferBuilder().withListing("4").withBuyer("Daniel Meier")
            .withOfferPrice("5000000").build();
    public static final Offer ELLE = new OfferBuilder().withListing("5").withBuyer("Elle Meyer")
            .withOfferPrice("900000").build();
    public static final Offer FIONA = new OfferBuilder().withListing("6").withBuyer("Fiona Kunz")
            .withOfferPrice("2000000").build();
    public static final Offer GEORGE = new OfferBuilder().withListing("7").withBuyer("George Best")
            .withOfferPrice("5000000").build();

    // Manually added
    public static final Offer HOON = new OfferBuilder().withBuyer("Hoon Meier")
            .build();
    public static final Offer IDA = new OfferBuilder().withBuyer("Listinga Mueller")
            .build();

    // Manually added - Offer's details found in {@code CommandTestUtil}
    public static final Offer AMY = new OfferBuilder().withBuyer(VALID_NAME_AMY)
            .withListing(VALID_LISTING_ID_1).withOfferPrice(VALID_PRICE_1).build();
    public static final Offer BOB = new OfferBuilder().withBuyer(VALID_NAME_BOB)
            .withListing(VALID_LISTING_ID_2).withOfferPrice(VALID_PRICE_2).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalOffers() {} // prevents instantiation

    /**
     * Returns an {@code RealTime} with all the typical Offers.
     */
    public static RealTime getTypicalRealTime() {
        RealTime rt = new RealTime();
        for (Offer offer : getTypicalOffers()) {
            rt.addOffer(offer);
        }
        return rt;
    }

    public static List<Offer> getTypicalOffers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
