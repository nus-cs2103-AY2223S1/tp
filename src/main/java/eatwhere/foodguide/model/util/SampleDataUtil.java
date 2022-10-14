package eatwhere.foodguide.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.ReadOnlyFoodGuide;
import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Eatery;
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
            new Eatery(new Name("Arise n Shine"), new Phone(""), new Cuisine("snacks"),
                new Location("Engineering Block E4"),
                getTagSet()),
            new Eatery(new Name("Arise n Shine 2"), new Phone(""), new Cuisine("snacks"),
                new Location("Science Block S16"),
                getTagSet()),
            new Eatery(new Name("Frontier"), new Phone(""), new Cuisine("variety"),
                new Location("Faculty of Science"),
                getTagSet("foodcourt")),
            new Eatery(new Name("The Deck"), new Phone(""), new Cuisine("variety"),
                new Location("Faculty of Arts & Social Sciences"),
                getTagSet("fodocourt")),
            new Eatery(new Name("KOI"), new Phone("69933323"), new Cuisine("bubble tea"),
                new Location("Central Square"),
                getTagSet()),
            new Eatery(new Name("Techno Edge"), new Phone(""), new Cuisine("variety"),
                new Location("Faculty of Engineering / School of Design & Environment"),
                getTagSet("foodcourt"))
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
