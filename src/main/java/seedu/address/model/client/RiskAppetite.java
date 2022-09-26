package seedu.address.model.client;

/**
 * Represents a client's risk level
 * wraps an enum with 3 levels (high/medium/low)
 * and an optional notes string
 */
public class RiskAppetite {

    public enum RiskLevel {
        HIGH, MEDIUM, LOW
    }

    private final String additionalNotes;
    private final RiskLevel rl;


    public RiskAppetite(String notes, RiskLevel r) {
        additionalNotes = notes;
        rl = r;
    }

    public RiskAppetite(RiskLevel r) {
        additionalNotes = null;
        rl = r;
    }

    public String getNotes() {
        return this.additionalNotes;
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

        if (this.getNotes() == null || otherRA.getNotes() == null) {
            return isSameRA  && this.getNotes() == otherRA.getNotes();
        } else {
            return isSameRA && this.getNotes().equals(otherRA.getNotes());
        }
    }

}
