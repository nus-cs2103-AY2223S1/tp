package jarvis.model;

/**
 * Represents a student's result for a specific mastery check.
 */
public class MasteryCheckResult {
    private final int mcNumber;
    private final boolean isPass;

    /**
     * Constructs a {@code MasteryCheckResult}
     *
     * @param mcNumber Mastery check number
     * @param isPass True if the result is a pass
     */
    public MasteryCheckResult(int mcNumber, boolean isPass) {
        this.mcNumber = mcNumber;
        this.isPass = isPass;
    }

    public int getMcNumber() {
        return mcNumber;
    }

    public boolean didPass() {
        return isPass;
    }

    @Override
    public String toString() {
        String result = isPass ? "PASSED" : "FAILED";

        return "MC " + mcNumber + ": " + result;
    }
}
