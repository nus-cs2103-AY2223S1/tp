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


    /**
     * Initialises a user object with given parameters
     * @param gender gender of the user
     * @param height height of the user
     * @param weight current weight of the user
     * @param idealWeight ideal weight that the user wishes to achieve
     */
    public User(Gender gender, Height height, Weight weight, Weight idealWeight) {
        requireAllNonNull(gender, height, weight, idealWeight);
        this.height = height;
        this.weight = weight;
        this.idealWeight = idealWeight;
        this.gender = gender;
    }

    /**
     * Default constructor of user sets height, weight, ideal weight to 0 and gender to M
     */
    public User() {
        this.height = new Height("0");
        this.weight = new Weight("0");
        this.idealWeight = new Weight("0");
        this.gender = new Gender("M");
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

    @Override
    public boolean equals(Object other) {
        if (other instanceof User) {
            User u = (User) other;
            return (this.weight.equals(u.weight) && this.height.equals(u.height)
                    && this.idealWeight.equals(u.idealWeight) && this.gender.equals(u.gender));
        }
        return false;
    }
}
