package friday.model.grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import friday.model.grades.Grade;

public class GradeTest {

    @Test
    public void equals() {
        Grade grade = new Grade("RA1", "69.99");

        // same object -> returns true
        assertTrue(grade.equals(grade));

        // same values -> returns true
        Grade gradeCopy = new Grade(grade.examName, grade.score);
        assertTrue(grade.equals(gradeCopy));

        // different types -> returns false
        assertFalse(grade.equals(1));

        // null -> returns false
        assertFalse(grade.equals(null));

        // grade with different exam name -> returns false
        Grade differentGradeName = new Grade("RA2", "69.99");
        assertFalse(grade.equals(differentGradeName));

        // grade with different score, same name -> returns false
        Grade differentGradeScore = new Grade("RA1", "74.5");
        assertFalse(grade.equals(differentGradeScore));

        // grade with different grade -> returns false
        Grade differentGrade = new Grade("Practical", "54.65");
        assertFalse(grade.equals(differentGrade));
    }
}
