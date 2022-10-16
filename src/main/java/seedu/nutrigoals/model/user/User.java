package seedu.nutrigoals.model.user;

import static seedu.nutrigoals.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a user object
 */
public class User {
    private final Height height;
    private final Weight weight;
    private final Weight idealWeight;
    private final Gender gender;
    private final Age age;


    /**
     * Initialises a user object with given parameters
     *
     * @param gender      gender of the user
     * @param height      height of the user
     * @param weight      current weight of the user
     * @param idealWeight ideal weight that the user wishes to achieve
     * @param age         user's age
     */
    public User(Gender gender, Height height, Weight weight, Weight idealWeight, Age age) {
        requireAllNonNull(gender, height, weight, idealWeight, age);
        this.height = height;
        this.weight = weight;
        this.idealWeight = idealWeight;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Default constructor of user sets height, weight, ideal weight to 0 and gender to M
     */
    public User() {
        this.height = new Height();
        this.weight = new Weight();
        this.idealWeight = new Weight();
        this.gender = new Gender();
        this.age = new Age();
    }

    public Weight getWeight() {
        return weight;
    }

    public Weight getIdealWeight() {
        return idealWeight;
    }

    public Gender getGender() {
        return gender;
    }

    public Height getHeight() {
        return height;
    }

    public Age getAge() {
        return age;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }

        User u = (User) other;
        return (this.weight.equals(u.weight) && this.height.equals(u.height)
                && this.idealWeight.equals(u.idealWeight) && this.gender.equals(u.gender))
                && this.age.equals(u.age);
    }
}
