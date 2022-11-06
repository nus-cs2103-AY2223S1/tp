package jarvis.model;

import jarvis.logic.commands.exceptions.InvalidMarkException;
import org.junit.jupiter.api.Test;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GradeProfileTest {

    @Test
    void grade() {
        GradeProfile gp = new GradeProfile();

        // Valid assessment and mark
        gp.grade(Assessment.RA1, 15.5);
        assertTrue(gp.getMarks(Assessment.RA1) == 15.5);

        // Null assessment throws NullPointerException
        assertThrows(NullPointerException.class, () -> gp.grade(null, 15.5));

        // Invalid mark throws InvalidMarkException
        assertThrows(InvalidMarkException.class, () -> gp.grade(Assessment.RA1, -1));
        assertThrows(InvalidMarkException.class, () -> gp.grade(Assessment.RA1, 999));

    }

    @Test
    void updateGrades() {
        GradeProfile gradeProfile = new GradeProfile();
        GradeProfile updatedGradeProfile = new GradeProfile();
        updatedGradeProfile.setRa1(15);
        updatedGradeProfile.setStudioAttendance(12);

        gradeProfile.updateGrades(updatedGradeProfile);

        // Both grade profiles should have identical grade maps
        assertTrue(gradeProfile.getGradeMap().equals(updatedGradeProfile.getGradeMap()));
    }

    @Test
    void testEquals() {
        GradeProfile gradeFirstProfile = new GradeProfile();
        GradeProfile gradeSecondProfile = new GradeProfile();
        gradeFirstProfile.grade(Assessment.RA1, 15);
        gradeSecondProfile.grade(Assessment.MIDTERM, 40);

        // same object -> returns true
        assertTrue(gradeFirstProfile.equals(gradeFirstProfile));

        // same fields -> returns true
        GradeProfile gradeFirstProfileCopy = new GradeProfile();
        gradeFirstProfileCopy.updateGrades(gradeFirstProfile);

        assertTrue(gradeFirstProfile.equals(gradeFirstProfileCopy));

        // different types -> returns false
        assertFalse(gradeFirstProfile.equals(1));

        // null -> returns false
        assertFalse(gradeFirstProfile.equals(null));

        // different fields -> returns false
        assertFalse(gradeFirstProfile.equals(gradeSecondProfile));
    }
}