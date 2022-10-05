package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.Calorie;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Food;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class FoodBuilder {

    public static final String DEFAULT_NAME = "Almonds";

    private Name name;
    private Calorie calorie;
    private Set<Tag> tags;
    private DateTime dateTime;

    /**
     * Creates a {@code FoodBuilder} with the default details.
     */
    public FoodBuilder() {
        name = new Name(DEFAULT_NAME);
        calorie = new Calorie();
        tags = new HashSet<>();
        dateTime = new DateTime();
    }

    /**
     * Initializes the FoodBuilder with the data of {@code foodToCopy}.
     */
    public FoodBuilder(Food foodToCopy) {
        name = foodToCopy.getName();
        calorie = foodToCopy.getCalorie();
        tags = new HashSet<>(foodToCopy.getTags());
        dateTime = foodToCopy.getDateTime();
    }

    /**
     * Sets the {@code Name} of the {@code Food} that we are building.
     */
    public FoodBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Food} that we are building.
     */
    public FoodBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Calorie} of the {@code Food} that we are building.
     */
    public FoodBuilder withCalorie(String calorie) {
        this.calorie = new Calorie(calorie);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Food} that we are building.
     */
    public FoodBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    public Food build() {
        return new Food(name, calorie, tags, dateTime);
    }

}
