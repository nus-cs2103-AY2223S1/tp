package seedu.nutrigoals.model.user;

import static seedu.nutrigoals.commons.util.CollectionUtil.requireAllNonNull;

import seedu.nutrigoals.model.Calorie;

/**
 * Represents the user of NutriGoals
 */
public class User {

    private static final double MALE_CONSTANT = 66.47;
    private static final double FEMALE_CONSTANT = 655.1;
    private static final double MALE_WEIGHT_COEFFICIENT = 13.75;
    private static final double FEMALE_WEIGHT_COEFFICIENT = 9.563;
    private static final double MALE_HEIGHT_COEFFICIENT = 5.003;
    private static final double FEMALE_HEIGHT_COEFFICIENT = 1.850;
    private static final double MALE_AGE_COEFFICIENT = 6.755;
    private static final double FEMALE_AGE_COEFFICIENT = 4.676;
    private static final double SEDENTARY = 1.2;
    private final Height height;
    private final Weight weight;
    private final Weight idealWeight;
    private final Gender gender;
    private final Age age;
    private final Bmi bmi;

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
        this.age = new Age();
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

    public Age getAge() {
        return age;
    }

    /**
     * Calculates the suggested calorie intake based on the user's height, weight and age
     * @return Suggested daily calorie intake
     */
    public Calorie calculateSuggestedCalorieIntake() {
        double bmr = calculateBmr();
        int suggestedCalorie = (int) (bmr * SEDENTARY);
        String calorie = Integer.toString(suggestedCalorie);
        return new Calorie(calorie);
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
        if (!(other instanceof User)) {
            return false;
        }

        User u = (User) other;
        return (this.weight.equals(u.weight) && this.height.equals(u.height)
                && this.idealWeight.equals(u.idealWeight) && this.gender.equals(u.gender))
                && this.age.equals(u.age) && this.bmi.equals(u.bmi);
    }

    private double calculateBmr() {
        // formula used: https://www.verywellfit.com/how-many-calories-do-i-need-each-day-2506873
        double h = height.getHeight() * 100; // height in cm
        int w = idealWeight.getWeight(); // weight in kg
        int a = age.getAge(); // age in years
        if (gender.isMale()) {
            return MALE_CONSTANT + (MALE_WEIGHT_COEFFICIENT * w)
                    + (MALE_HEIGHT_COEFFICIENT * h)
                    - (MALE_AGE_COEFFICIENT * a);
        } else { // female
            return FEMALE_CONSTANT + (FEMALE_WEIGHT_COEFFICIENT * w)
                    + (FEMALE_HEIGHT_COEFFICIENT * h)
                    - (FEMALE_AGE_COEFFICIENT * a);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n");
        stringBuilder.append("Gender: ")
                .append(getGender())
                .append("\nCurrent Height: ")
                .append(getHeight())
                .append("\nCurrent Weight: ")
                .append(getWeight())
                .append("\nIdeal Weight: ")
                .append(getIdealWeight())
                .append("\nBMI: ")
                .append(getBmi())
                .append("\nAge: ")
                .append(getAge());
        return stringBuilder.toString();
    }
}
