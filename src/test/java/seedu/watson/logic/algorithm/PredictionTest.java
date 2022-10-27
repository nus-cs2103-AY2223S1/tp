package seedu.watson.logic.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.watson.model.person.subject.Grades;

public class PredictionTest {

    @Test
    public void execute_validGradeInput_prediction() {
        double testResult = PredictionUtil.predictGrade(new GradeStub());
        assertEquals(12.0, testResult);
    }

    static class GradeStub extends Grades {

        private final ArrayList<String> assessments = new ArrayList<>(Set.of("FirstTest", "SecondTest"));

        public ArrayList<String> getAllAssessments() {
            return assessments;
        }

        public double[] getGradeForAssessment(String assessment) {
            if (assessment.equals("FirstTest")) {
                return new double[]{10, 10, 20};
            } else {
                return new double[]{14, 20, 20};
            }
        }
    }
}
