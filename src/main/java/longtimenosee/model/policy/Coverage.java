package longtimenosee.model.policy;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

/**
 * Represents what a given policy covers.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCoverageName(String)}
 */
public class Coverage {
    public static final String MESSAGE_CONSTRAINTS = "Error: Coverages must be one "
            + "of the following types: "
            + "{LIFE, MOTOR, HEALTH, TRAVEL, PROPERTY, MOBILE, BITE}";

    public static final String CONSTRAINTS = "LIFE, MOTOR, HEALTH, TRAVEL, PROPERTY, MOBILE, BITE";

    public final String coverageType;

    private final CoverageName coverageName;

    /**
     * Enumerator representing CoverageNames.
     * Encapsulates the different kinds of Coverages.
     * Tagged with messages to be represented in String format.
     */
    public enum CoverageName {
        LIFE("Life Insurance"),
        MOTOR("Motor Insurance"),
        HEALTH("Health Insurance"),
        TRAVEL("Travel Insurance"),
        PROPERTY("Property Insurance"),
        MOBILE("Mobile Insurance"),
        BITE("Bite-sized Insurance");

        private String message;

        CoverageName(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

    /**
     * Constructs a {@code Coverage}.
     *
     * @param coverageType A valid coverageType.
     */
    public Coverage(String coverageType) {
        requireNonNull(coverageType);
        checkArgument(isValidCoverageName(coverageType), MESSAGE_CONSTRAINTS);
        this.coverageType = coverageType;
        this.coverageName = parseCoverageName(coverageType);

    }

    public CoverageName getCoverageName() {
        return this.coverageName;
    }

    public String getCoverageType() {
        return this.coverageType;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidCoverageName(String coverage) {
        return CONSTRAINTS.contains(coverage);
    }

    /**
     * Utility function to parse coverageName from string
     * @param indicator to parseCoverageName from Json
     * @return the appropriate CoverageName wrapped in an enum
     */
    public static CoverageName parseCoverageName(String indicator) {
        switch (indicator) {

        case "LIFE":
            return CoverageName.LIFE;
        case "MOTOR":
            return CoverageName.MOTOR;
        case "HEALTH":
            return CoverageName.HEALTH;
        case "MOBILE":
            return CoverageName.MOBILE;
        case "TRAVEL":
            return CoverageName.TRAVEL;
        case "PROPERTY":
            return CoverageName.PROPERTY;
        case "BITE":
            return CoverageName.BITE;

        default:
            //Should never reach this
            return CoverageName.HEALTH;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Coverage // instanceof handles nulls
                && this.getCoverageName() == (((Coverage) other).getCoverageName())); // state check
    }

    @Override
    public int hashCode() {
        return coverageName.hashCode();
    }

    /**
     * Format coverage as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + coverageName.toString() + ']';
    }
}
