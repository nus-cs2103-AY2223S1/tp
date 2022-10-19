package jarvis.model;

public class GradeComponent {

    private Assessment assessment;
    private boolean isGraded;
    private int marks;
    private int totalMarks;

    public GradeComponent(Assessment assessment) {
        this.assessment = assessment;
        isGraded = false;
        marks = 0;
        totalMarks = 0;
    }

    public String getGrade() {
        if (!isGraded) {
            return "-";
        }

        switch(assessment) {
            case MC1:
                // FALLTHROUGH
            case MC2:
                return marks == 1 ? "PASSED" : "FAILED";
            default:
                return "marks" + "/" + "totalMarks";
        }
    }

    public void setGrade(int marks, int totalMarks) {
        isGraded = true;
        this.marks = marks;
        this.totalMarks = totalMarks;
    }
}

