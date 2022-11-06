package seedu.watson.model.student.subject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GradesTest {

    @Test
    public void returnsCorrectPercentageForOneAssessment() {
        //setup variables
        Grades testGrade = new Grades();
        testGrade.updateAssessment(new Assessment("test", 10.0, 10.0,
                1.0, 5.0));
        assertTrue(testGrade.getCurrentPercentage() == 100.0);
    }

    @Test
    public void returnsCorrectPercentageForMultipleAssessments() {
        Grades testGrade = new Grades();
        testGrade.updateAssessment(new Assessment("test", 10.0, 10.0,
                0.5, 5.0));
        testGrade.updateAssessment(new Assessment("test2", 0, 10.0,
                0.5, 5.0));
        assertTrue(testGrade.getCurrentPercentage() == 50.0);
    }

    @Test
    public void returnsCorrectToString() {
        Grades testGrade = new Grades();
        testGrade.updateAssessment(new Assessment("test", 1.0, 10.0,
                0.5, 5.0));
        assertTrue(testGrade.toString().equals("Grade = 10.00")); // with one assessment
        Grades emptyGrade = new Grades();
        assertTrue(emptyGrade.toString().equals("There is currently no grade.")); //empty grade
    }

    @Test
    public void returnsCorrectDataString() {
        Grades testGrade = new Grades();
        testGrade.updateAssessment(new Assessment("test", 1.0, 10.0,
                0.5, 5.0));
        assertTrue(testGrade.dataString().equals("test:[1.0, 10.0, 0.5, 5.0]")); // with one assessment
        Grades emptyGrade = new Grades();
        assertTrue(emptyGrade.dataString().equals("")); //empty grade
    }

}
