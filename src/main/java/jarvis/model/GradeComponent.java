package jarvis.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a component of a GradeProfile in JARVIS.
 */
public class GradeComponent {

    private Assessment assessment;
    private boolean isGraded;
    private double marks;

    /**
     * Creates an ungraded GradeComponent object with the specified assessment.
     * @param assessment The assessment involved.
     */
    public GradeComponent(Assessment assessment) {
        this.assessment = assessment;
        isGraded = false;
        marks = 0;
    }

    /**
     * JSON Constructor for Jackson.
     */
    @JsonCreator
    public GradeComponent(@JsonProperty("assessment") Assessment assessment,
                          @JsonProperty("isGraded") boolean isGraded,
                          @JsonProperty("marks") int marks) {
        this.assessment = assessment;
        this.isGraded = isGraded;
        this.marks = marks;
    }

    public String getGradeString() {
        int totalMarks = assessment.getTotalMarks();
        if (!isGraded) {
            switch(assessment) {
            case MC1:
                // FALLTHROUGH
            case MC2:
                return "-";
            default:
                return String.format("-/%d", totalMarks);
            }
        }

        switch(assessment) {
        case MC1:
            // FALLTHROUGH
        case MC2:
            return marks == 1 ? "PASSED" : "FAILED";
        default:
            return String.format("%.1f/%d (%.2f%%)", marks, totalMarks, 100 * marks / totalMarks);
        }
    }

    public void setGrade(double marks) {
        isGraded = true;
        this.marks = marks;
    }

    public boolean isGraded() {
        return isGraded;
    }

    public double getMarks() {
        return marks;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof GradeComponent)) { // instanceof handles nulls
            return false;
        }

        GradeComponent gc = (GradeComponent) other;
        return assessment == gc.assessment && isGraded == gc.isGraded && marks == gc.marks;
    }
}

