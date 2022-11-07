package seedu.watson.logic.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.watson.model.student.Attendance;
import seedu.watson.model.student.subject.Grades;

public class PredictionTest {

    private final double expectedResult = 78.90970887629169;

    @Test
    public void execute_validGradeInput_prediction() {
        double testResult = PredictionUtil.predictGrade(new GradeStub(), new AttendanceStub(), 2.5);
        assertEquals(expectedResult, testResult);
    }

    static class GradeStub extends Grades {

        private final ArrayList<String> assessments = new ArrayList<>(Set.of("FirstTest", "SecondTest", "ThirdTest"));

        @Override
        public ArrayList<String> getAllAssessments() {
            return assessments;
        }

        @Override
        public double[] getRawPercentages() {
            return new double[]{0.80625, 0.78, 0.8625};
        }

        @Override
        public double[] getDifficulties() {
            return new double[]{1.0, 2.5, 2.0};
        }

        @Override
        public double[] getGradeForAssessment(String assessment) {
            if (assessment.equals("FirstTest")) {
                return new double[]{64.5, 80, 0.1, 1.0};
            } else if (assessment.equals("SecondTest")) {
                return new double[]{78, 100, 0.3, 2.5};
            } else {
                return new double[]{69, 80, 0.1, 2.0};
            }
        }
    }

    static class AttendanceStub extends Attendance {

        @Override
        public int[] getAttendanceDetails() {
            return new int[]{0, 1, 1};
        }
    }
}
