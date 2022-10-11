package jarvis.model;

/**
 * Represents a student's consolidated results for their mastery checks 1 and 2.
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

    /**
     * @param mcNum The mastery check number
     * @return True if the result for the given mastery check is a pass
     */
    public boolean didPassMc(int mcNum) {
        if (mcNum == 1) {
            return mc1.didPass();
        } else {
            return mc2.didPass();
        }
    }

    @Override
    public String toString() {
        String passed = "✓";
        String failed = "X";
        String passMc1 = didPassMc(1) ? passed : failed;
        String passMc2 = didPassMc(2) ? passed : failed;
        return "Mastery Check 1: " + passMc1 + "\n" + "Mastery Check 2: " + passMc2;
    }
}
