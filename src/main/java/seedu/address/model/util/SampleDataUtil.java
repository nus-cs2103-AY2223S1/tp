package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.*;
import seedu.address.model.client.Client;
import seedu.address.model.listing.Listing;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.offer.Offer;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Client[] getSampleCLients() {
        return new Client[]{
            new Client(new Name("Alex Yeoh"), new ArrayList<Meeting>(), new ArrayList<Offer>(),
                new ArrayList<Listing>(), EMPTY_REMARK,
                getTagSet("friends")),
            new Client(new Name("Bernice Yu"), new ArrayList<Meeting>(), new ArrayList<Offer>(),
                    new ArrayList<Listing>(), EMPTY_REMARK,
                getTagSet("colleagues", "friends")),
            new Client(new Name("Charlotte Oliveiro"), new ArrayList<Meeting>(), new ArrayList<Offer>(),
                    new ArrayList<Listing>(), EMPTY_REMARK,
                getTagSet("neighbours")),
            new Client(new Name("David Li"), new ArrayList<Meeting>(), new ArrayList<Offer>(),
                    new ArrayList<Listing>(), EMPTY_REMARK,
                getTagSet("family")),
            new Client(new Name("Irfan Ibrahim"), new ArrayList<Meeting>(), new ArrayList<Offer>(),
                    new ArrayList<Listing>(), EMPTY_REMARK,
                getTagSet("classmates")),
            new Client(new Name("Roy Balakrishnan"), new ArrayList<Meeting>(), new ArrayList<Offer>(),
                    new ArrayList<Listing>(), EMPTY_REMARK,
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleCLients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
