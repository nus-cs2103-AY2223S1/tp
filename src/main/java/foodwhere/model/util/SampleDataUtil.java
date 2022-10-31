package foodwhere.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import foodwhere.model.AddressBook;
import foodwhere.model.ReadOnlyAddressBook;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Rating;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Stall;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Stall[] getSampleStalls() {
        return new Stall[] {
            new Stall(new Name("Alex Chicken Rice"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("chickenrice")),
            new Stall(new Name("Char Char Kuey Tiao"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("charkwaytiao")),
            new Stall(new Name("Yu Bak Chor Mee"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("bakchormee")),
            new Stall(new Name("Irfan Muslim Food"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family", "halal", "muslim"))
        };
    }

    public static Review[] getSampleReviews() {
        return new Review[] {
            new Review(new Name("Alex Chicken Rice"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Date("20/09/2022"), new Content("Very tasty. Worth the trip"), new Rating("5"),
                    getTagSet("travelworthy")),
            new Review(new Name("Irfan Muslim Food"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Date("21/09/2022"), new Content("Very affordable"), new Rating("3"),
                    getTagSet("halal"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Stall sampleStall : getSampleStalls()) {
            sampleAb.addStall(sampleStall);
        }

        for (Review sampleReview : getSampleReviews()) {
            sampleAb.addReview(sampleReview);
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
