package foodwhere.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import foodwhere.model.AddressBook;
import foodwhere.model.ReadOnlyAddressBook;
import foodwhere.model.commons.Detail;
import foodwhere.model.commons.Name;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Address;
import foodwhere.model.stall.Stall;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Stall[] getSampleStalls() {
        return new Stall[] {
            new Stall(new Name("Alex Yeoh"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    getDetailSet("friends")),
            new Stall(new Name("Bernice Yu"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getDetailSet("colleagues", "friends")),
            new Stall(new Name("Charlotte Oliveiro"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getDetailSet("neighbours")),
            new Stall(new Name("David Li"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getDetailSet("family")),
            new Stall(new Name("Irfan Ibrahim"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    getDetailSet("classmates")),
            new Stall(new Name("Roy Balakrishnan"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getDetailSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Stall sampleStall : getSampleStalls()) {
            sampleAb.addStall(sampleStall);
        }
        return sampleAb;
    }

    /**
     * Returns a detail set containing the list of strings given.
     */
    public static Set<Detail> getDetailSet(String... strings) {
        return Arrays.stream(strings)
                .map(Detail::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a review set containing the list of reviews given.
     */
    public static Set<Review> getReviewSet(Review... reviews) {
        return new HashSet<>(List.of(reviews));
    }

}
