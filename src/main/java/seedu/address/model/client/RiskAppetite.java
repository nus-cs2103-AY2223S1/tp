package seedu.address.model.client;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a client's risk level
 * wraps an enum with 3 levels (high/medium/low)
 * and an optional notes string
 */
public class RiskAppetite {

    public static String MESSAGE_FORMAT_CONSTRAINTS = "Error: RiskAppetite must be one " +
            "of the following indicators: " +
            "{H, M, L}";

    private static ArrayList<String> MESSAGE_INDICATORS = new ArrayList<String>(Arrays.asList("H", "M", "L"));

    public static boolean isValidFormat(String ra) {
        return MESSAGE_INDICATORS.contains(ra);
    }

    public enum RiskLevel {
        HIGH, MEDIUM, LOW
    }

    private final RiskLevel rl;

    public String value;


    public RiskAppetite(String riskLevel) {
        value = riskLevel;
        rl = parseRiskLevel(riskLevel);
    }


    public RiskLevel getRiskLevel() {
        return this.rl;
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

        }
        return RiskLevel.MEDIUM; //TODO: Add a default value?
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (! (other instanceof RiskAppetite)) {
            return false;
        }

        RiskAppetite otherRA = (RiskAppetite) other;
        boolean isSameRA = this.getRiskLevel() == otherRA.getRiskLevel();
        return isSameRA;
    }

}
