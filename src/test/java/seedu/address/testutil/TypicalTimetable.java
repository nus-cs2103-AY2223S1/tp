package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.Lesson;

/**
 * A utility class containing a list of {@code Lesson} objects to be used in tests.
 */
public class TypicalTimetable {

    // test lesson list to store the unsorted lessons
    private Set<Lesson> unsortedLessonList;
    // lesson list to store the correctly sorted lessons
    private ArrayList<Lesson> sortedLessonList;
    // 8 lessons, listed in chronological order from 1 to 8
    // CS2101 Tutorial Tuesday 12:00 - 14:00
    private final Lesson lesson1 = new LessonBuilder().withType("tut").withModule("CS2101")
            .withDay("2").withStartTime("12:00").withEndTime("14:00").build();
    // CS2100 Recitation Tuesday 16:00 - 18:00
    private final Lesson lesson2 = new LessonBuilder().withType("rec").withModule("CS2100")
            .withDay("2").withStartTime("16:00").withEndTime("18:00").build();
    // CS2109S Tutorial Wednesday 17:00 - 18:00
    private final Lesson lesson3 = new LessonBuilder().withType("tut").withModule("CS2109S")
            .withDay("3").withStartTime("17:00").withEndTime("18:00").build();
    // CS2103T Tutorial Thursday 14:00 - 15:00
    private final Lesson lesson4 = new LessonBuilder().withType("tut").withModule("CS2103T")
            .withDay("4").withStartTime("14:00").withEndTime("15:00").build();
    // CS2100 Lab Thursday 16:00 - 17:00
    private final Lesson lesson5 = new LessonBuilder().withType("lab").withModule("CS2100")
            .withDay("4").withStartTime("16:00").withEndTime("17:00").build();
    // CS2100 Tutorial Thursday 17:00 - 18:00
    private final Lesson lesson6 = new LessonBuilder().withType("tut").withModule("CS2100")
            .withDay("4").withStartTime("17:00").withEndTime("18:00").build();
    // CS2109S Lecture Friday 10:00 - 12:00
    private final Lesson lesson7 = new LessonBuilder().withType("lec").withModule("CS2109S")
            .withDay("5").withStartTime("10:00").withEndTime("12:00").build();
    // CS2101 Tutorial Friday 12:00 - 14:00
    private final Lesson lesson8 = new LessonBuilder().withType("tut").withModule("CS2101")
            .withDay("5").withStartTime("12:00").withEndTime("14:00").build();

    /**
     * A constructor that initialises a sorted and unsorted lesson list to be used for testing.
     */
    public TypicalTimetable() {
        this.unsortedLessonList = new HashSet<>();
        this.sortedLessonList = new ArrayList<>();
        unsortedLessonList.add(lesson4);
        unsortedLessonList.add(lesson6);
        unsortedLessonList.add(lesson2);
        unsortedLessonList.add(lesson3);
        unsortedLessonList.add(lesson1);
        unsortedLessonList.add(lesson8);
        unsortedLessonList.add(lesson5);
        unsortedLessonList.add(lesson7);
        sortedLessonList.add(lesson1);
        sortedLessonList.add(lesson2);
        sortedLessonList.add(lesson3);
        sortedLessonList.add(lesson4);
        sortedLessonList.add(lesson5);
        sortedLessonList.add(lesson6);
        sortedLessonList.add(lesson7);
        sortedLessonList.add(lesson8);
    }

    public Set<Lesson> getUnsortedLessonSet() {
        return this.unsortedLessonList;
    }

    public ArrayList<Lesson> getSortedLessonList() {
        return this.sortedLessonList;
    }

}
