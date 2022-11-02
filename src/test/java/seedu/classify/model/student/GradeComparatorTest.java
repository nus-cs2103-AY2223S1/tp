package seedu.classify.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.classify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.classify.model.student.exceptions.ExamNotFoundException;
import seedu.classify.testutil.StudentBuilder;

public class GradeComparatorTest {

    private Student s1 = new StudentBuilder().withClassName("4A").withExams("SA1 70").build();
    private Student s2 = new StudentBuilder().withClassName("4A").withExams("SA1 80").build();
    private Student s3 = new StudentBuilder().withClassName("4B").withExams("SA1 70").build();
    private Student s4 = new StudentBuilder().withClassName("4A").withExams("SA1 70").build();

    @Test
    public void compare_studentOneDifferentClass_returnsOne() {
        GradeComparator gradeComparator = new GradeComparator("SA1", new Class("4A"));
        assertEquals(1, gradeComparator.compare(s3, s1));
    }

    @Test
    public void compare_studentTwoDifferentClass_returnsNegative() {
        GradeComparator gradeComparator = new GradeComparator("SA1", new Class("4A"));
        assertEquals(-1, gradeComparator.compare(s1, s3));
    }

    @Test
    public void compare_differentGrades_returnsNegative() {
        GradeComparator gradeComparator = new GradeComparator("SA1", new Class("4A"));
        assertEquals(-1, gradeComparator.compare(s1, s2));
    }

    @Test
    public void compare_sameGrades_returnsZero() {
        GradeComparator gradeComparator = new GradeComparator("SA1", new Class("4A"));
        assertEquals(0, gradeComparator.compare(s1, s4));
    }

    @Test
    public void compare_differentExam_throwsExamNotFoundException() {
        GradeComparator gradeComparator = new GradeComparator("SA2", new Class("4A"));
        assertThrows(ExamNotFoundException.class, () -> gradeComparator.compare(s1, s2));
    }
}
