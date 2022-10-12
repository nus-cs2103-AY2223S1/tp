package eatwhere.foodguide.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.ReadOnlyFoodGuide;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.Email;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Phone;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FoodGuide} with sample data.
 */
public class SampleDataUtil {
    public static Eatery[] getSamplePersons() {
        return new Eatery[] {
            new Eatery(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Location("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Eatery(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Location("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Eatery(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Location("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Eatery(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Location("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Eatery(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Location("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Eatery(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Location("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyFoodGuide getSampleFoodGuide() {
        FoodGuide sampleAb = new FoodGuide();
        for (Eatery sampleEatery : getSamplePersons()) {
            sampleAb.addEatery(sampleEatery);
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
