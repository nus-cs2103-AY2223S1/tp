package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jarvis.commons.core.index.Index;
import jarvis.model.exceptions.DuplicateLessonException;
import jarvis.model.exceptions.LessonClashException;
import jarvis.model.exceptions.LessonNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of lessons that enforces uniqueness between its elements and does not allow nulls.
 * A lesson is considered unique by comparing using {@code Lesson#equals(Object)}.
 * As such, adding and updating of lesson uses Lesson#equals(Object) for equality to ensure that the
 * lesson being added or updated is unique in terms of identity in the UniqueLessonList.The removal of a
 * lesson also uses Lesson#equals(Object).
 *
 * Supports a minimal set of list operations.
 *
 * @see Lesson#equals(Object)
 */
public class UniqueLessonList implements Iterable<Lesson> {

    private static final Comparator<Lesson> LESSON_COMPARATOR = (l1, l2) -> {
        if (l1.isCompleted() != l2.isCompleted()) {
            return l1.isCompleted() ? 1 : -1;
        }
        if (l1.isCompleted()) {
            return l2.startDateTime().compareTo(l1.startDateTime());
        } else {
            return l1.startDateTime().compareTo(l2.startDateTime());
        }
    };

    private final ObservableList<Lesson> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lesson> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent lesson as the given argument.
     */
    public boolean contains(Lesson toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains a lesson that will have a clash in time slot with
     * the lesson as given argument.
     */
    public boolean hasPeriodClash(Lesson toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::hasTimingConflict);
    }

    /**
     * Adds a lesson to the list.
     * The lesson must not already exist in the list.
     * The time period must not clash with existing lessons as well.
     */
    public void add(Lesson toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLessonException();
        }

        if (hasPeriodClash(toAdd)) {
            throw new LessonClashException();
        }

        internalList.add(toAdd);
        FXCollections.sort(internalList, LESSON_COMPARATOR);
    }

    /**
     * Returns the index of a lesson in the list.
     * @param target Lesson to find the index of.
     * @return the {@code Index} of the lesson.
     */
    public Index indexOf(Lesson target) {
        requireNonNull(target);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LessonNotFoundException();
        }
        return Index.fromZeroBased(index);
    }

    /**
     * Replaces the lesson {@code target} in the list with {@code editedLesson}.
     * {@code target} must exist in the list.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in the list.
     */
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireAllNonNull(target, editedLesson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LessonNotFoundException();
        }

        if (!target.equals(editedLesson) && contains(editedLesson)) {
            throw new DuplicateLessonException();
        }

        internalList.set(index, editedLesson);
        FXCollections.sort(internalList, LESSON_COMPARATOR);
    }

    /**
     * Removes the equivalent lesson from the list.
     * The lesson must exist in the list.
     */
    public void remove(Lesson toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LessonNotFoundException();
        }
        FXCollections.sort(internalList, LESSON_COMPARATOR);
    }

    public void setLessons(UniqueLessonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        FXCollections.sort(internalList, LESSON_COMPARATOR);
    }

    /**
     * Replaces the contents of this list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<? extends Lesson> lessons) {
        requireAllNonNull(lessons);
        if (!lessonsAreUnique(lessons)) {
            throw new DuplicateLessonException();
        }

        internalList.setAll(lessons);
        FXCollections.sort(internalList, LESSON_COMPARATOR);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lesson> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lesson> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLessonList // instanceof handles nulls
                && internalList.equals(((UniqueLessonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code lessons} contains only unique lessons.
     */
    private boolean lessonsAreUnique(List<? extends Lesson> lessons) {
        for (int i = 0; i < lessons.size() - 1; i++) {
            for (int j = i + 1; j < lessons.size(); j++) {
                if (lessons.get(i).equals(lessons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
