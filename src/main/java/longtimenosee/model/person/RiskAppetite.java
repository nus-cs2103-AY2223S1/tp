package longtimenosee.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *  Encapsulation for a client's RiskAppetite
 */
public class RiskAppetite {

    public static final Comparator<Person> RISK_APPETITE_COMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            RiskLevel ra1 = p1.getRiskAppetite().getRiskLevel();
            RiskLevel ra2 = p2.getRiskAppetite().getRiskLevel();
            if (ra1 == ra2) {
                return 0;
            } else if (ra1 == RiskLevel.HIGH && (ra2 == RiskLevel.LOW || ra2 == RiskLevel.MEDIUM)) {
                return 1;
            } else if (ra1 == RiskLevel.MEDIUM && ra2 == RiskLevel.LOW) {
                return 1;
            } else if (ra2 == RiskLevel.HIGH && (ra1 == RiskLevel.LOW || ra1 == RiskLevel.MEDIUM)) {
                return -1;
            } else {
                return -1;
            }
        }
    };
    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Error: RiskAppetite must be one "
            + "of the following indicators: "
            + "{H, M, L}";

    public static final List<String> MESSAGE_VALIDATION = Collections.unmodifiableList(List.of("H", "M", "L"));

    public static final String SORT_RISK_APPETITE = "risk appetite";

    public final String value;

    private final RiskLevel rl;

    /**
     * Enumerator representing RiskLevel.
     * Encapsulates 3 different categories, High, Medium and Low risk.
     * Tagged with messages to be represented in String format.
     */

    public enum RiskLevel {
        HIGH("High"), MEDIUM("Medium"), LOW("Low");

        private String message;

        RiskLevel(String message) {
            this.message = message;
        }
        @Override
        public String toString() {
            return message;
        }
    }
    /**
     * Default constructor.
     * @param riskLevel
     */
    public RiskAppetite(String riskLevel) {
        requireNonNull(riskLevel);
        value = riskLevel;
        rl = parseRiskLevel(riskLevel);
    }

    public RiskLevel getRiskLevel() {
        return this.rl;
    }

    /**
     * Returns true if a given string is a valid risk appetite.
     *
     * @param ra is the input string to be tested
     * @return boolean to indicate if the given string is a valid risk appetite
     */
    public static boolean isValidFormat(String ra) {
        return MESSAGE_VALIDATION.contains(ra);
    }

    /**
     * Utility function to parse risklevel from string
     * @param indicator to parseRiskLevel from Json
     * @return the appropriate risklevel wrapped in an enum
     */
    public static RiskLevel parseRiskLevel(String indicator) {
        switch (indicator) {
        case "H":
            return RiskLevel.HIGH;
        case "M":
            return RiskLevel.MEDIUM;
        case "L":
            return RiskLevel.LOW;
        default:
            return RiskLevel.HIGH;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RiskAppetite)) {
            return false;
        }

        RiskAppetite otherRA = (RiskAppetite) other;
        boolean isSameRA = this.getRiskLevel() == otherRA.getRiskLevel();
        return isSameRA;
    }

    @Override
    public String toString() {
        return rl.toString();
    }

}
