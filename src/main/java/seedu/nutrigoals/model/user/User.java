package seedu.nutrigoals.model.user;

import static seedu.nutrigoals.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the user of NutriGoals
 */
public class User {
    private final Height height;
    private final Weight weight;
    private final Weight idealWeight;
    private final Gender gender;
    private final Bmi bmi;


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
        bmi = new Bmi(height, weight);
    }

    /**
     * Default constructor of user sets height, weight, ideal weight to 0 and gender to M
     */
    public User() {
        this.height = new Height();
        this.weight = new Weight();
        this.idealWeight = new Weight();
        this.gender = new Gender();
        bmi = new Bmi(height, weight);
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

    public Bmi getBmi() {
        return bmi;
    }

    public boolean isUserCreated() {
        return !height.isZero() && !weight.isZero();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof User) {
            User u = (User) other;
            return (this.weight.equals(u.weight) && this.height.equals(u.height)
                    && this.idealWeight.equals(u.idealWeight) && this.gender.equals(u.gender));
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\nUser profile:\n");
        stringBuilder.append("Gender: ")
                .append(getGender())
                .append("\nCurrent Height: ")
                .append(getHeight())
                .append("\nCurrent Weight: ")
                .append(getWeight())
                .append("\nIdeal Weight: ")
                .append(getIdealWeight())
                .append("\nBMI: ")
                .append(getBmi());
        return stringBuilder.toString();
    }
}
