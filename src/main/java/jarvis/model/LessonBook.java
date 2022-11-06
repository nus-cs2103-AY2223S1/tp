package jarvis.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all lesson data in JARVIS
 * Duplicates are not allowed (by .equal comparison)
 */
public class LessonBook implements ReadOnlyLessonBook {

    private final UniqueLessonList lessons;

    {
        lessons = new UniqueLessonList();
    }

    public LessonBook() {}

    /**
     * Creates a LessonBook using the Lessons in the {@code toBeCopied}
     */
    public LessonBook(ReadOnlyLessonBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the lesson list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<? extends Lesson> lessons) {
        this.lessons.setLessons(lessons);
    }

    /**
     * Resets the existing data of this {@code LessonBook} with {@code newData}.
     */
    public void resetData(ReadOnlyLessonBook newData) {
        requireNonNull(newData);

        setLessons(newData.getLessonList());
    }

    //// lesson-level operations

    /**
     * Returns true if a lesson with the same identity as {@code lesson} exists in the lesson book.
     */
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.contains(lesson);
    }

    /**
     * Adds a lesson to the lesson book.
     * The lesson must not already exist in the lesson book.
     */
    public void addLesson(Lesson p) {
        lessons.add(p);
    }

    /**
     * Checks if lesson has a time period clash with existing lessons in lesson book.
     */
    public boolean hasPeriodClash(Lesson l) {
        return lessons.hasPeriodClash(l);
    }

    /**
     * Replaces the given lesson {@code targetLesson} in the list with {@code editedLesson}.
     * {@code targetLesson} must exist in the lesson book.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in the lesson
     * book.
     */
    public void setLesson(Lesson targetLesson, Lesson editedLesson) {
        requireNonNull(editedLesson);

        lessons.setLesson(targetLesson, editedLesson);
    }

    /**
     * Removes {@code key} from this {@code LessonBook}.
     * {@code key} must exist in the lesson book.
     */
    public void removeLesson(Lesson key) {
        lessons.remove(key);
    }

    public void setStudent(Student targetStudent, Student editedStudent) {
        for (Lesson l: lessons) {
            if (!l.hasStudent(targetStudent)) {
                continue;
            }
            l.setStudent(targetStudent, editedStudent);
            lessons.setLesson(l, l);
        }
    }

    //// util methods

    @Override
    public String toString() {
        return lessons.asUnmodifiableObservableList().size() + " lessons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        return lessons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonBook // instanceof handles nulls
                && lessons.equals(((LessonBook) other).lessons));
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }
}
