package friday.model.grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import friday.model.grades.Grade;
import friday.model.grades.GradesList;

public class GradesListTest {

    @Test
    public void equals() {
        GradesList gradesList = new GradesList();
        Grade grade = new Grade("RA1", "90");
        GradesList.editGrade(gradesList, grade);

        // same object -> returns true
        assertTrue(gradesList.equals(gradesList));

        // same values -> returns true
        GradesList gradesListCopy = new GradesList();
        GradesList.editGrade(gradesListCopy, grade);
        assertTrue(gradesList.equals(gradesListCopy));

        // different types -> returns false
        assertFalse(gradesList.equals(1));

        // null -> returns false
        assertFalse(gradesList.equals(null));

        // gradesList with more grades -> returns false
        GradesList differentGradeList = new GradesList();
        Grade differentGrade = new Grade("RA2", "80");
        GradesList.editGrade(differentGradeList, grade);
        GradesList.editGrade(differentGradeList, differentGrade);

        assertFalse(gradesList.equals(differentGradeList));

        // grade with fewer grades -> returns false
        GradesList emptyGradesList = new GradesList();
        assertFalse(gradesList.equals(emptyGradesList));

        // grade with same grade but different score -> returns false
        GradesList differentGradesListScore = new GradesList();
        Grade differentGradeScore = new Grade("RA1", "15.00");
        GradesList.editGrade(differentGradesListScore, differentGradeScore);
        assertFalse(gradesList.equals(differentGradesListScore));
    }
}
