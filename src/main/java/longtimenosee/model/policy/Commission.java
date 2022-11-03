package longtimenosee.model.policy;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

/**
 * Represents a Policy's commission.
 * Guarantees: immutable; is valid as declared in {@link #isValidCommission(String)}
 */
public class Commission {

    public static final String MESSAGE_CONSTRAINTS = "Commissions should be positive percentages with up to 5 decimals,"
            + " between and including 0% to 100%, \n"
            + "taking the format of 'cms/1st yr % 2nd yr %"
            + " 3rd year and beyond %'";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "((([1-9]\\d*)?\\d)(\\.\\d{1,5})?%(\\s)?){3}";

    public final String value;

    public final float[] percentages;


    /**
     * Constructs an {@code Commission}.
     *
     * @param commission A valid commission.
     */
    public Commission(String commission) {
        requireNonNull(commission);
        checkArgument(isValidCommission(commission), MESSAGE_CONSTRAINTS);
        value = commission;
        percentages = parseCommission(commission);
    }

    /**
     * Returns true if a given string is a valid commission.
     */
    public static boolean isValidCommission(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        float[] percentages = parseCommission(test);
        for (float percentage: percentages) {
            if (percentage < 0 || percentage > 100) {
                return false;
            }
        }

        return true;
    }

    /**
     * Utility function to parse parseCommission from string
     * @param indicator to parseCommission from Json
     * @return the appropriate array of Commission percentages
     */
    public static float[] parseCommission(String indicator) {
        String[] commissions = indicator.split("%");
        float[] percentages = new float[commissions.length];
        for (int i = 0; i < commissions.length; i++) {
            percentages[i] = Float.valueOf(commissions[i]);
        }
        return percentages;
    }

    @Override
    public String toString() {
        return value;
    }
    /**
     * Gets commission of the policy based on the year
     * @param year to get the targeted year, 1 for first year, 2 for second...
     * @return % commission of that year
     */
    public float getCommission(int year) {
        if (year > 1) {
            return percentages[2] / 100f;
        }
        return percentages[year] / 100f;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Commission // instanceof handles nulls
                && value.equals(((Commission) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
