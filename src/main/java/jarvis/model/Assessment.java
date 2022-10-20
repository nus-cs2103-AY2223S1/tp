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
    STUDIO_ATTENDANCE("studio");

    private String name;

    public static int mc1_total_marks = 1;
    public static int mc2_total_marks = 1;
    public static int ra1_total_marks = 18;
    public static int ra2_total_marks = 18;
    public static int midterm_total_marks = 65;
    public static int practical_assessment_total_marks = 40;
    public static int final_assessment_total_marks = 100;

    Assessment(String name) {
        this.name = name;
    }

    public int getTotalMarks() {
        switch(name) {
        case "mc1":
            return mc1_total_marks;
        case "mc2":
            return mc2_total_marks;
        case "ra1":
            return ra1_total_marks;
        case "ra2":
            return ra2_total_marks;
        case "midterm":
            return midterm_total_marks;
        case "pa":
            return practical_assessment_total_marks;
        case "final":
            return final_assessment_total_marks;
        default:
            return 0;
        }
    }
}
