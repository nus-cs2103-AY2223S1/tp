package jarvis.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a component of a GradeProfile in JARVIS.
 */
public class GradeComponent {

    private Assessment assessment;
    private boolean isGraded;
    private int marks;
    private int totalMarks;

    /**
     * Creates an ungraded GradeComponent object with the specified assessment.
     * @param assessment The assessment involved.
     */
    public GradeComponent(Assessment assessment) {
        this.assessment = assessment;
        isGraded = false;
        marks = 0;
        totalMarks = 0;
    }

    /**
     * JSON Constructor for Jackson.
     */
    @JsonCreator
    public GradeComponent(@JsonProperty("assessment") Assessment assessment,
                          @JsonProperty("isGraded") boolean isGraded,
                          @JsonProperty("marks") int marks, @JsonProperty("totalMarks") int totalMarks) {
        this.assessment = assessment;
        this.isGraded = isGraded;
        this.marks = marks;
        this.totalMarks = totalMarks;
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

