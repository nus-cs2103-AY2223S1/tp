package seedu.nutrigoals.model.user;

/**
 * Represents the user's body-mass-index
 */
public class Bmi {

    public static final Height DEFAULT_HEIGHT = new Height("170");
    public static final Weight DEFAULT_WEIGHT = new Weight("70");
    public final String value;
    private final Height height;
    private final Weight weight;

    /**
     * Initialises a Bmi object using the {@code height} and {@code weight} provided by the user
     * @param height User's height
     * @param weight User's weight
     */
    public Bmi(Height height, Weight weight) {
        this.height = height;
        this.weight = weight;
        value = calculateBmi() + ""; // converts the bmi value into a string
    }

    /**
     * Initialises a Bmi object using a default height and weight
     */
    public Bmi() {
        height = DEFAULT_HEIGHT;
        weight = DEFAULT_WEIGHT;
        value = calculateBmi() + "";
    }

    @Override
    public String toString() {
        double bmi = Double.parseDouble(value);
        String roundedOff = String.format("%.1f", bmi);
        return roundedOff;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Bmi)) {
            return false;
        }

        Bmi other = (Bmi) obj;
        return value.equals(other.value);
    }

    /**
     * Calculates the user's {@code Bmi}
     * @return User's bmi as a double
     */
    public double calculateBmi() {
        double h = height.getHeight();
        int w = weight.getWeight();
        return w / (h * h);
    }

}
