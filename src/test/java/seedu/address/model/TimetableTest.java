package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Lesson;
import seedu.address.testutil.TypicalTimetable;

public class TimetableTest {

    @Test
    public void sortFunctionSortsCorrectly() {
        TypicalTimetable typicalTimetable = new TypicalTimetable();
        Set<Lesson> unsortedLessons = typicalTimetable.getUnsortedLessonSet();
        ArrayList<Lesson> sortedLessons = typicalTimetable.getSortedLessonList();
        Timetable timetable = new Timetable(unsortedLessons);
        ArrayList<Lesson> expected = sortedLessons;
        assertEquals(expected, timetable.sort());
    }

    @Test
    public void toWeekFunctionAllocatesLessonsCorrectly() {
        TypicalTimetable typicalTimetable = new TypicalTimetable();
        Set<Lesson> unsortedLessons = typicalTimetable.getUnsortedLessonSet();
        ArrayList<Lesson> sortedLessons = typicalTimetable.getSortedLessonList();
        Timetable timetable = new Timetable(unsortedLessons);
        ArrayList<Lesson>[] timetableToWeek = timetable.toWeek();
        ArrayList<Lesson> expectedMonday = new ArrayList<>();
        ArrayList<Lesson> expectedTuesday = new ArrayList<>();
        expectedTuesday.add(sortedLessons.get(0));
        expectedTuesday.add(sortedLessons.get(1));
        ArrayList<Lesson> expectedWednesday = new ArrayList<>();
        expectedWednesday.add(sortedLessons.get(2));
        ArrayList<Lesson> expectedThursday = new ArrayList<>();
        expectedThursday.add(sortedLessons.get(3));
        expectedThursday.add(sortedLessons.get(4));
        expectedThursday.add(sortedLessons.get(5));
        ArrayList<Lesson> expectedFriday = new ArrayList<>();
        expectedFriday.add(sortedLessons.get(6));
        expectedFriday.add(sortedLessons.get(7));
        ArrayList<Lesson> expectedSaturday = new ArrayList<>();
        ArrayList<Lesson> expectedSunday = new ArrayList<>();
        assertEquals(expectedMonday, timetableToWeek[0]);
        assertEquals(expectedTuesday, timetableToWeek[1]);
        assertEquals(expectedWednesday, timetableToWeek[2]);
        assertEquals(expectedThursday, timetableToWeek[3]);
        assertEquals(expectedFriday, timetableToWeek[4]);
        assertEquals(expectedSaturday, timetableToWeek[5]);
        assertEquals(expectedSunday, timetableToWeek[6]);
    }

    @Test
    public void toWeekFunctionWithNoLessons_throwsNullPointerException() {
        Timetable timetable = new Timetable(null);
        assertThrows(NullPointerException.class, () -> timetable.toWeek());
    }

    @Test
    public void timetableToString_isFormattedCorrectly() {
        TypicalTimetable typicalTimetable = new TypicalTimetable();
        Set<Lesson> unsortedLessons = typicalTimetable.getUnsortedLessonSet();
        Timetable timetable = new Timetable(unsortedLessons);
        String expected = "Monday: \n"
                + "No classes on this day. \n"
                + "\n"
                + "Tuesday: \n"
                + "CS2101 Tutorial 12:00 to 14:00\n"
                + "CS2100 Recitation 16:00 to 18:00\n"
                + "\n"
                + "Wednesday: \n"
                + "CS2109S Tutorial 17:00 to 18:00\n"
                + "\n"
                + "Thursday: \n"
                + "CS2103T Tutorial 14:00 to 15:00\n"
                + "CS2100 Lab 16:00 to 17:00\n"
                + "CS2100 Tutorial 17:00 to 18:00\n"
                + "\n"
                + "Friday: \n"
                + "CS2109S Lecture 10:00 to 12:00\n"
                + "CS2101 Tutorial 12:00 to 14:00\n"
                + "\n"
                + "Saturday: \n"
                + "No classes on this day. \n"
                + "\n"
                + "Sunday: \n"
                + "No classes on this day. \n"
                + "\n";
        assertEquals(expected, timetable.toString());
    }

}
