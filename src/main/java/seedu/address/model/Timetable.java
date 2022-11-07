package seedu.address.model;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.module.Lesson;
import seedu.address.model.module.LessonComparator;

/**
 * Converts set of lessons to timetable in days and sorted by time.
 */
public class Timetable {
    private static String[] days = new String[]
        {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    private Set<Lesson> lessons;

    public Timetable(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    /**
     * Sorts the User's or Person's lessons in chronological order.
     *
     * @return An ArrayList of the User's or Person's lessons in chronological order.
     */
    public ArrayList<Lesson> sort() {
        ArrayList<Lesson> lessonsList = new ArrayList<>(lessons);
        lessonsList.sort(new LessonComparator());
        return lessonsList;
    }

    /**
     * Sorts the User's or Person's lessons in chronological order then categorising the lessons by day of the week.
     *
     * @return an array separating the User's or Person's lessons by each day of the week.
     */
    public ArrayList<Lesson>[] toWeek() {
        assert (!lessons.equals(null)) : "Timetable not initialised";

        ArrayList<Lesson> lessonsList = this.sort();

        @SuppressWarnings("unchecked")
        ArrayList<Lesson>[] week = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            week[i] = new ArrayList<Lesson>();
        }

        lessonsList.forEach(lesson -> {
            week[lesson.getDay() - 1].add(lesson);
        });

        return week;
    }

    @Override
    public String toString() {
        String str = "";
        ArrayList<Lesson>[] week = this.toWeek();
        for (int i = 0; i < 7; i++) {
            str = str + days[i] + ": \n";
            Object[] lessonsOnDay = week[i].toArray();
            if (lessonsOnDay.length == 0) {
                str += "No classes on this day. \n";
            }
            for (Object lesson : lessonsOnDay) {
                str += lesson.toString();
                str += "\n";
            }
            str += "\n";
        }
        return str;
    }
}
