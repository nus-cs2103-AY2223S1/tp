package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.*;
import seedu.address.model.client.Client;
import seedu.address.model.listing.Listing;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.offer.Offer;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final ArrayList<Meeting> DEFAULT_MEEINGLIST = new ArrayList<Meeting>();
    public static final ArrayList<Offer> DEFAULT_OFFERLIST = new ArrayList<Offer>();
    public static final ArrayList<Listing> DEFAULT_LISTINGLIST = new ArrayList<Listing>();
    public static final String DEFAULT_REMARK = "She likes aardvarks.";

    private Name name;
    private ArrayList<Meeting> meetingList;
    private ArrayList<Offer> offerList;
    private ArrayList<Listing> listingList;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        meetingList = new ArrayList<Meeting>(DEFAULT_MEEINGLIST);
        offerList = new ArrayList<Offer>(DEFAULT_OFFERLIST);
        listingList = new ArrayList<Listing>(DEFAULT_LISTINGLIST);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        meetingList = clientToCopy.getMeetingList();
        offerList = clientToCopy.getOfferList();
        listingList = clientToCopy.getlistingList();
        remark = clientToCopy.getRemark();
        tags = new HashSet<>(clientToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code MeetingList} of the {@code Client} that we are building.
     */
    public ClientBuilder withMeetingList(ArrayList<Meeting> meetingList) {
        this.meetingList = new ArrayList<Meeting>(meetingList);
        return this;
    }

    /**
     * Sets the {@code OfferList} of the {@code Client} that we are building.
     */
    public ClientBuilder withOfferList(ArrayList<Offer> offerList) {
        this.offerList = new ArrayList<Offer>(offerList);
        return this;
    }

    /**
     * Sets the {@code ListingList} of the {@code Client} that we are building.
     */
    public ClientBuilder withListingList(ArrayList<Listing> listingList) {
        this.listingList = new ArrayList<Listing>(listingList);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Client} that we are building.
     */
    public ClientBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Client build() {
        return new Client(name, meetingList, offerList, listingList, remark, tags);
    }

}
