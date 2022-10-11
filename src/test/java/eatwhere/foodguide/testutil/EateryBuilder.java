package eatwhere.foodguide.testutil;

import java.util.HashSet;
import java.util.Set;

import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Phone;
import eatwhere.foodguide.model.tag.Tag;
import eatwhere.foodguide.model.util.SampleDataUtil;

/**
 * A utility class to help with building Eatery objects.
 */
public class EateryBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amygmailcom";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Cuisine cuisine;
    private Location location;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public EateryBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        cuisine = new Cuisine(DEFAULT_EMAIL);
        location = new Location(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code eateryToCopy}.
     */
    public EateryBuilder(Eatery eateryToCopy) {
        name = eateryToCopy.getName();
        phone = eateryToCopy.getPhone();
        cuisine = eateryToCopy.getCuisine();
        location = eateryToCopy.getLocation();
        tags = new HashSet<>(eateryToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Eatery} that we are building.
     */
    public EateryBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Eatery} that we are building.
     */
    public EateryBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Eatery} that we are building.
     */
    public EateryBuilder withLocation(String address) {
        this.location = new Location(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Eatery} that we are building.
     */
    public EateryBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Cuisine} of the {@code Eatery} that we are building.
     */
    public EateryBuilder withCuisine(String cuisine) {
        this.cuisine = new Cuisine(cuisine);
        return this;
    }

    public Eatery build() {
        return new Eatery(name, phone, cuisine, location, tags);
    }

}
