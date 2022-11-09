package seedu.nutrigoals.testutil;

import seedu.nutrigoals.model.user.Age;
import seedu.nutrigoals.model.user.Gender;
import seedu.nutrigoals.model.user.Height;
import seedu.nutrigoals.model.user.User;
import seedu.nutrigoals.model.user.Weight;

/**
 * A Utility class to help with building user objects
 */
public class UserBuilder {

    public static final String DEFAULT_WEIGHT = "65";
    public static final String DEFAULT_IDEAL = "60";
    public static final String DEFAULT_GENDER = "M";
    public static final String DEFAULT_HEIGHT = "170";
    public static final String DEFAULT_AGE = "20";

    private Weight weight;
    private Height height;
    private Weight ideal;
    private Gender gender;
    private Age age;

    /**
     * Default constructer initialises values to default values
     */
    public UserBuilder() {
        this.gender = new Gender(DEFAULT_GENDER);
        this.weight = new Weight(DEFAULT_WEIGHT);
        this.ideal = new Weight(DEFAULT_IDEAL);
        this.height = new Height(DEFAULT_HEIGHT);
        this.age = new Age(DEFAULT_AGE);
    }

    /**
     * copies User in userCopy into current user
     * @param userCopy
     */
    public UserBuilder(User userCopy) {
        this.height = userCopy.getHeight();
        this.weight = userCopy.getWeight();
        this.ideal = userCopy.getIdealWeight();
        this.gender = userCopy.getGender();
        this.age = userCopy.getAge();
    }

    /**
     * Sets the {@code Height} of the {@code User} that we are building.
     */
    public UserBuilder withHeight(String height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code IdealWeight} of the {@code User} that we are building.
     */
    public UserBuilder withIdeal(String ideal) {
        this.ideal = new Weight(ideal);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code User} that we are building.
     */
    public UserBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code User} that we are building.
     */
    public UserBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code User} that we are building.
     */
    public UserBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Builds user as per user builder
     */
    public User build() {
        return new User(gender, height, weight, ideal, age);
    }
}
