package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class LessonComparatorTest {

    public final Lesson lesson1 = new LessonBuilder().withDay("1").withStartTime("12:00").withEndTime("13:00").build();
    public final Lesson lesson2 = new LessonBuilder().withDay("1").withStartTime("13:00").withEndTime("14:00").build();
    public final Lesson lesson3 = new LessonBuilder().withDay("2").withStartTime("12:00").withEndTime("13:00").build();

    @Test
    public void compareTwoLessonsOnTheDifferentDays() {
        LessonComparator lc = new LessonComparator();
        int compare1 = lc.compare(lesson1, lesson3);
        int expected1 = -1;
        int compare2 = lc.compare(lesson3, lesson1);
        int expected2 = 1;
        assertEquals(expected1, compare1);
        assertEquals(expected2, compare2);
    }

    @Test
    public void compareTwoLessonsOnTheSameDay() {
        LessonComparator lc = new LessonComparator();
        int compare1 = lc.compare(lesson1, lesson2);
        int expected1 = -1;
        int compare2 = lc.compare(lesson2, lesson1);
        int expected2 = 1;
        assertEquals(expected1, compare1);
        assertEquals(expected2, compare2);
    }

    @Test
    public void compareTwoSameLessons() {
        LessonComparator lc = new LessonComparator();
        int compare = lc.compare(lesson1, lesson1);
        int expected = 0;
        assertEquals(expected, compare);
    }
}
