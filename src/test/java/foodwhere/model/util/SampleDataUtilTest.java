package foodwhere.model.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

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

public class SampleDataUtilTest {

    @Test
    public void getSampleStalls_equals() {
        Stall[] sampleStalls = new Stall[] {
            new Stall(new Name("Alex Chicken Rice"), new Address("Blk 30 Geylang Street 29, #06-40"),
                SampleDataUtil.getTagSet("chickenrice")),
            new Stall(new Name("Char Char Kuey Tiao"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                SampleDataUtil.getTagSet("charkwaytiao")),
            new Stall(new Name("Yu Bak Chor Mee"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                SampleDataUtil.getTagSet("bakchormee")),
            new Stall(new Name("Irfan Muslim Food"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                SampleDataUtil.getTagSet("family", "halal", "muslim"))
        };

        // same stall list -> array equals return true
        assertArrayEquals(SampleDataUtil.getSampleStalls(), sampleStalls);

        Stall[] sampleStallsNotEquals = new Stall[] {
            new Stall(new Name("Alex Chicken Rice"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    SampleDataUtil.getTagSet("chickenrice")),
            new Stall(new Name("Char Char Kuey Tiao"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    SampleDataUtil.getTagSet("charkwaytiao"))
        };

        // different stall list -> array equals return false
        assertFalse(Arrays.equals(sampleStallsNotEquals, SampleDataUtil.getSampleStalls()));

        Stall[] sampleStallsNull = new Stall[] {
            null,
            null,
            null
        };

        // null stall list -> array equals return false
        assertFalse(Arrays.equals(sampleStallsNull, SampleDataUtil.getSampleStalls()));

    }

    @Test
    public void getSampleReviews_equals() {
        Review[] sampleReviews = new Review[] {
            new Review(new Name("Alex Chicken Rice"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Date("20/09/2022"), new Content("Very tasty. Worth the trip"), new Rating("5"),
                    SampleDataUtil.getTagSet("travelworthy")),
            new Review(new Name("Irfan Muslim Food"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Date("21/09/2022"), new Content("Very affordable"), new Rating("3"),
                    SampleDataUtil.getTagSet("halal"))
        };

        // same review list -> array equals returns true
        assertArrayEquals(sampleReviews, SampleDataUtil.getSampleReviews());

        Review[] sampleReviewsNotEquals = new Review[] {
            new Review(new Name("Alex Chicken Rice"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Date("20/09/2022"), new Content("Very tasty. Worth the trip"), new Rating("5"),
                    SampleDataUtil.getTagSet("travelworthy"))
        };

        // different review list -> array equals returns false
        assertFalse(Arrays.equals(sampleReviewsNotEquals, SampleDataUtil.getSampleReviews()));

        Review[] sampleReviewNull = new Review[] {
            null,
            null,
            null
        };

        // null review list -> array equals return false
        assertFalse(Arrays.equals(sampleReviewNull, SampleDataUtil.getSampleReviews()));

    }

    @Test
    public void test_getSampleAddressBook() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();
        AddressBook sampleAb = new AddressBook();
        for (Stall s: SampleDataUtil.getSampleStalls()) {
            sampleAb.addStall(s);
        }

        for (Review r: SampleDataUtil.getSampleReviews()) {
            sampleAb.addReview(r);
        }

        // same stall and review list -> equal address book
        assertEquals(sampleAb, addressBook);

        AddressBook sampleAb2 = new AddressBook();

        // different stall and review list -> not equal address book
        assertNotEquals(sampleAb2, addressBook);

        // address book null -> not equal address book
        assertNotEquals(null, addressBook);
    }

    @Test
    public void test_getTagSet() {
        Set<Tag> sampleTags = new HashSet<>();
        String[] tags = new String[] {
            "sampleTag1",
            "sampleTag2",
            "sampleTag3",
            "sampleTag4"
        };

        for (String s: tags) {
            sampleTags.add(new Tag(s));
        }

        // same list of tags -> equal tag set
        assertEquals(sampleTags, SampleDataUtil.getTagSet(tags));

        Set<Tag> sampleTagsNotEqual = new HashSet<>();
        String[] tagsNotEqual = new String[] {
            "sampleTag1",
            "sampleNotEqualTag"
        };

        for (String s: tagsNotEqual) {
            sampleTagsNotEqual.add(new Tag(s));
        }

        // different tag set -> not equal tag set
        assertNotEquals(sampleTagsNotEqual, SampleDataUtil.getTagSet(tags));

        // null tag set -> not equal tag set
        assertNotEquals(null, SampleDataUtil.getTagSet(tags));
    }
}
