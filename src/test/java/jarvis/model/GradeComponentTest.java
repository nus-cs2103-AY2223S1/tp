package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.exceptions.InvalidMarkException;

class GradeComponentTest {

    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GradeComponent(null));
    }

    @Test
    void getGradeString() {
        GradeComponent gradeFirstComponent = new GradeComponent(Assessment.RA1);
        GradeComponent gradeSecondComponent = new GradeComponent(Assessment.RA1);
        GradeComponent gradeThirdComponent = new GradeComponent(Assessment.MC1);
        GradeComponent gradeFourthComponent = new GradeComponent(Assessment.MC1);

        gradeFirstComponent.setGrade(15);
        gradeSecondComponent.setGrade(15);
        gradeThirdComponent.setGrade(1);
        gradeFourthComponent.setGrade(1);

        // Same assessments and marks return the same grade string
        assertTrue(gradeFirstComponent.getGradeString().equals(gradeSecondComponent.getGradeString()));
        assertTrue(gradeThirdComponent.getGradeString().equals(gradeFourthComponent.getGradeString()));

        gradeSecondComponent.setGrade(1);
        // Mastery check should not return same grade string as other assessment types despite same mark
        assertFalse(gradeSecondComponent.getGradeString().equals(gradeThirdComponent.getGradeString()));

        gradeThirdComponent.setGrade(0);
        // Same assessment different mark should not return same grade string
        assertFalse(gradeFirstComponent.getGradeString().equals(gradeSecondComponent.getGradeString()));
        assertFalse(gradeThirdComponent.getGradeString().equals(gradeFourthComponent.getGradeString()));
    }

    @Test
    void setGrade() {
        GradeComponent gradecomponent = new GradeComponent(Assessment.MC1);

        // Should set isGraded to true
        gradecomponent.setGrade(1);
        assertTrue(gradecomponent.isGraded());

        // Invalid mark throws InvalidMarkException
        assertThrows(InvalidMarkException.class, () -> gradecomponent.setGrade(-1));
        assertThrows(InvalidMarkException.class, () -> gradecomponent.setGrade(2));
    }

    @Test
    void testEquals() {
        GradeComponent gradeFirstComponent = new GradeComponent(Assessment.RA1);
        GradeComponent gradeSecondComponent = new GradeComponent(Assessment.MC2);
        gradeFirstComponent.setGrade(13);

        // same object -> returns true
        assertTrue(gradeFirstComponent.equals(gradeFirstComponent));

        // same fields -> returns true
        GradeComponent gradeFirstComponentCopy = new GradeComponent(Assessment.RA1);
        gradeFirstComponentCopy.setGrade(13);

        assertTrue(gradeFirstComponent.equals(gradeFirstComponentCopy));

        // different types -> returns false
        assertFalse(gradeFirstComponent.equals(1));

        // null -> returns false
        assertFalse(gradeFirstComponent.equals(null));

        // different fields -> returns false
        assertFalse(gradeFirstComponent.equals(gradeSecondComponent));
    }
}
