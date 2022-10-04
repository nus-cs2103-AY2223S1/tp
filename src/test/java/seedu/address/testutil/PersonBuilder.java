package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.Calorie;
import seedu.address.model.person.Food;
import seedu.address.model.person.MealType;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";

    private Name name;
    private MealType mealType;
    private Calorie calorie;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        mealType = new MealType(DEFAULT_PHONE);
        calorie = new Calorie();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Food foodToCopy) {
        name = foodToCopy.getName();
        mealType = foodToCopy.getPhone();
        calorie = foodToCopy.getCalorie();
        tags = new HashSet<>(foodToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.mealType = new MealType(phone);
        return this;
    }

    /**
     * Sets the {@code Calorie} of the {@code Person} that we are building.
     */
    public PersonBuilder withCalorie(String calorie) {
        this.calorie = new Calorie(calorie);
        return this;
    }

    public Food build() {
        return new Food(name, mealType, calorie, tags);
    }

}
