package jarvis.model;

/**
 * Represents a graded assessment in the CS1101S curriculum.
 */
public enum Assessment {
    MC1("mc1"),
    MC2("mc2"),
    RA1("ra1"),
    RA2("ra2"),
    MIDTERM("midterm"),
    PRACTICAL_ASSESSMENT("pa"),
    FINAL_ASSESSMENT("final"),
    STUDIO_ATTENDANCE("sa");

    private static int totalMarksMc1 = 1;
    private static int totalMarksMc2 = 1;
    private static int totalMarksRa1 = 18;
    private static int totalMarksRa2 = 18;
    private static int totalMarksMidterm = 65;
    private static int totalMarksPracticalAssessment = 40;
    private static int totalMarksFinalAssessment = 100;
    private static int totalMarksStudioAttendance = 12;

    private String name;

    Assessment(String name) {
        this.name = name;
    }

    public int getTotalMarks() {
        switch(name) {
        case "mc1":
            return totalMarksMc1;
        case "mc2":
            return totalMarksMc2;
        case "ra1":
            return totalMarksRa1;
        case "ra2":
            return totalMarksRa2;
        case "midterm":
            return totalMarksMidterm;
        case "pa":
            return totalMarksPracticalAssessment;
        case "final":
            return totalMarksFinalAssessment;
        case "sa":
            return totalMarksStudioAttendance;
        default:
            return 0;
        }
    }
}
