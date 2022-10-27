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
            new Eatery(new Name("Vegetarian (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Chinese"), new Location("Frontier, Faculty of Science"),
                getTagSet("vegetarian")),
            new Eatery(new Name("Korean (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Korean"), new Location("Frontier, Faculty of Science"),
                getTagSet()),
            new Eatery(new Name("Chinese (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Chinese"), new Location("Frontier, Faculty of Science"),
                getTagSet()),
            new Eatery(new Name("Indian (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Indian"), new Location("Frontier, Faculty of Science"),
                getTagSet()),
            new Eatery(new Name("Noodles (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Chinese"), new Location("Frontier, Faculty of Science"),
                getTagSet()),
            new Eatery(new Name("Japanese (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Japanese"), new Location("Frontier, Faculty of Science"),
                getTagSet()),
            new Eatery(new Name("Thai (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Thai"), new Location("Frontier, Faculty of Science"),
                    getTagSet()),
            new Eatery(new Name("Yong Tau Foo (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Chinese"), new Location("Frontier, Faculty of Science"),
                    getTagSet()),
            new Eatery(new Name("Chef's Wok (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Chinese"), new Location("Frontier, Faculty of Science"),
                    getTagSet("halal")),
            new Eatery(new Name("Muslim (Frontier, Non-Aircon)"), new Phone(""),
                    new Cuisine("Malay"), new Location("Frontier, Faculty of Science"),
                    getTagSet("halal")),
            new Eatery(new Name("Mala Hotpot (Frontier, Non-Aircon)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Frontier, Faculty of Science"),
                        getTagSet("mala", "spicy" )),
            new Eatery(new Name("Western (Frontier, Non-Aircon)"), new Phone(""),
                        new Cuisine("Western"), new Location("Frontier, Faculty of Science"),
                        getTagSet()),
            new Eatery(new Name("Chicken Rice (Frontier, Non-Aircon)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Frontier, Faculty of Science"),
                        getTagSet("vegetarian")),
            new Eatery(new Name("Fruits & Juices (Frontier, Non-Aircon)"), new Phone(""),
                        new Cuisine("Beverages"), new Location("Frontier, Faculty of Science"),
                        getTagSet()),
            new Eatery(new Name("Gong Cha (Frontier, Non-Aircon)"), new Phone(""),
                        new Cuisine("Beverages"), new Location("Frontier, Faculty of Science"),
                        getTagSet("halal")),
            new Eatery(new Name("Drinks & Snacks (Frontier, Non-Aircon)"), new Phone(""),
                        new Cuisine("Beverages"), new Location("Frontier, Faculty of Science"),
                        getTagSet()),
            new Eatery(new Name("Uncle Penyet Fusion (Frontier, Aircon)"), new Phone(""),
                        new Cuisine("Malay"), new Location("Frontier, Faculty of Science"),
                        getTagSet()),
            new Eatery(new Name("Li Ji Coffee House (Frontier, Aircon)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Frontier, Faculty of Science"),
                        getTagSet()),
            new Eatery(new Name("Pasta Express (Frontier, Aircon)"), new Phone(""),
                        new Cuisine("Western"), new Location("Frontier, Faculty of Science"),
                        getTagSet()),
            new Eatery(new Name("Taiwan Ichiban (Frontier, Aircon)"), new Phone(""),
                        new Cuisine("Taiwan"), new Location("Frontier, Faculty of Science"),
                        getTagSet()),
            new Eatery(new Name("Bistro Box (Techno Edge)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet()),
            new Eatery(new Name("Chicken Rice (Techno Edge)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet()),
            new Eatery(new Name("Drinks & Snacks (Techno Edge)"), new Phone(""),
                        new Cuisine("Beverages"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet()),
            new Eatery(new Name("Indian (Techno Edge)"), new Phone(""),
                        new Cuisine("Indian"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet()),
            new Eatery(new Name("Mala Xiang Guo (Techno Edge)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet("mala", "spicy")),
            new Eatery(new Name("Mixed Rice (Techno Edge)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet()),
            new Eatery(new Name("Nasi Padang (Techno Edge)"), new Phone(""),
                        new Cuisine("Malay"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet("halal")),
            new Eatery(new Name("Noodles (Techno Edge)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet()),
            new Eatery(new Name("Ramen & Soup (Techno Edge)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet()),
            new Eatery(new Name("Taiwan (Techno Edge)"), new Phone(""),
                        new Cuisine("Taiwan"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet()),
            new Eatery(new Name("Vegetarian (Techno Edge)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet("vegetarian")),
            new Eatery(new Name("Western Food (Techno Edge)"), new Phone(""),
                        new Cuisine("Western"), new Location("Techno Edge, Faculty of Engineering"),
                        getTagSet()),
            new Eatery(new Name("Food Time"), new Phone(""),
                        new Cuisine("Mixed"), new Location("The Terrace, School Of Computing"),
                        getTagSet()),
            new Eatery(new Name("Korean (PGPR)"), new Phone(""),
                        new Cuisine("Korean"), new Location("Prince George's Park Residences"),
                        getTagSet()),
            new Eatery(new Name("Chicken Rice (PGPR)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Prince George's Park Residences"),
                        getTagSet()),
            new Eatery(new Name("Chinese Cuisine (PGPR)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Prince George's Park Residences"),
                        getTagSet()),
            new Eatery(new Name("Korean (PGPR)"), new Phone(""),
                        new Cuisine("Korean"), new Location("Prince George's Park Residences"),
                        getTagSet()),
            new Eatery(new Name("Chicken Rice (PGPR)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Prince George's Park Residences"),
                        getTagSet()),
             new Eatery(new Name("Chinese Cuisine (PGPR)"), new Phone(""),
                        new Cuisine("Chinese"), new Location("Prince George's Park Residences"),
                        getTagSet()),

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
