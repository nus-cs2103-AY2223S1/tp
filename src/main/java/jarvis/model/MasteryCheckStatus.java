package jarvis.model;

/**
 * Represents a student's consolidated results for their mastery checks 1 and 2.
 *
 */
public class MasteryCheckStatus {
    public static final String MESSAGE_INVALID_MCNUM = "Mastery check number has to be 1 or 2.";
    public static final String MESSAGE_INVALID_MCRESULT = "Mastery check result has to be \"PASS\" or \"FAIL\"";

    private MasteryCheckResult mc1;
    private MasteryCheckResult mc2;

    /**
     * Constructs a {@code MasteryCheckStatus}
     *
     * @param mc1 Result for mastery check 1
     * @param mc2 Result for mastery check 2
     */
    public MasteryCheckStatus(MasteryCheckResult mc1, MasteryCheckResult mc2) {
        this.mc1 = mc1;
        this.mc2 = mc2;
    }

    /**
     * Factory method to produce a default mastery check status (i.e. Ungraded/Failed for both mastery checks)
     *
     * @return The default mastery check status
     */
    public static MasteryCheckStatus getDefault() {
        return new MasteryCheckStatus(new MasteryCheckResult(1, false),
                new MasteryCheckResult(2, false));
    }

    public static boolean isValidMcNum(int mcNum) {
        return mcNum == 1 || mcNum == 2;
    }

    /**
     * Updates the result for either Mastery Check 1 or 2, depending on the given result.
     *
     * @param mcResult The given Mastery Check Result
     */
    public void updateMcResult(MasteryCheckResult mcResult) {
        int mcNum = mcResult.getMcNumber();
        if (mcNum == 1) {
            mc1 = mcResult;
        } else {
            mc2 = mcResult;
        }
    }

    public boolean didPassMc(int mcNum) {
        if (mcNum == 1) {
            return mc1.didPass();
        } else {
            return mc2.didPass();
        }
    }

    /**
     * Represents a student's result for a specific mastery check.
     */
    public static class MasteryCheckResult {
        private final int mcNumber;
        private final boolean isPass;

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

        public String toString() {
            String result = isPass ? "PASSED" : "FAILED/UNGRADED";

            return "Mastery Check " + mcNumber + ": " + result;
        }
    }
}