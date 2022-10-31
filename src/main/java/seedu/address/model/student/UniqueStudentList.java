package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.student.exceptions.StudentNotFoundException;
import seedu.address.model.timerange.TimeRange;
import seedu.address.storage.ClassStorage;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(Student)}.
 * As such, adding and updating of students uses Student#isSameStudent(Student) for equality
 * to ensure that the student being added or updated is unique in terms of identity in the UniqueStudentList.
 * However, the removal of a student uses Student#equals(Object) so the student with exactly the same fields will be
 * removed.
 * Supports a minimal set of list operations.
 *
 * @see Student#isSameStudent(Student)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudent);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.isSameStudent(editedStudent) && contains(editedStudent)) {
            throw new DuplicateStudentException();
        }

        internalList.set(index, editedStudent);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public void setStudents(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(students);
    }

    /**
     * Sorts the {@code internalList} by the given {@code comparator}.
     */
    public void sortStudents(Comparator<Student> comparator) {
        requireNonNull(comparator);
        ArrayList<Student> sortedList = replaceSort(internalList, comparator);
        internalList.setAll(sortedList);
    }


    private static ArrayList<Student> replaceSort(
            ObservableList<Student> observableList, Comparator<Student> comparator) {
        ArrayList<Student> duplicatedList = new ArrayList<>();
        for (int i = 0; i < observableList.size(); i++) {
            duplicatedList.add(observableList.get(i));
        }
        duplicatedList.sort(comparator);
        return duplicatedList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the current list.
     */
    public ObservableList<Student> getInternalList() {
        return internalList;
    }

    /**
     * Returns the next first available class.
     *
     * @param tr a timeRange object containing the {@code startTime}, {@code endTime} and {@code duration}.
     * @return the next first available Class.
     */
    public Class findAvailableClass(TimeRange tr) {
        LocalDate currDate = LocalDate.now();
        List<Student> list = internalList
                .stream()
                .filter(student -> student.getAClass().startTime != null
                        && student.getAClass().endTime != null
                        && student.getAClass().date != null
                        && student.getAClass().date.compareTo(currDate) >= 0
                        && student.getAClass().startTime.compareTo(LocalTime.now()) >= 0)
                .sorted(Student::compareTo)
                .collect(Collectors.toList());
        Class newClass = new Class();
        if (list.size() == 0) {
            newClass = new Class(currDate, tr.startTimeRange,
                    tr.startTimeRange.plusMinutes(tr.duration));
            return newClass;
        } else if (list.size() == 1) {
            return findAvailableClassWithSingleRecord(tr, currDate, list);
        }

        for (int i = 0; i < list.size(); i++) {
            Class aFirstClass = list.get(i).getAClass();
            if (i == list.size() - 1) {
                /*
                    if the list.size() - 1, that means that you are only looking at the last element in the list.
                    in which case, you are looking at a few cases
                    Case 1: The startTimeRange is before the startTime of the class, in which case, you should
                            create a class from the start time and check to see if it exceeds the startTime of the
                            original class.
                    Case 2: If the startTimeRange is not before the startTime of the class, then it is either the same
                            or after the class. So you can try to create a class, from the end of the class.
                    Case 3: If it exceeds the endTimeRange then you look at the next day, but will be handled by next
                            iteration
                 */

                if (tr.startTimeRange.compareTo(aFirstClass.startTime) < 0) {
                    newClass = new Class(aFirstClass.date, tr.startTimeRange,
                            tr.startTimeRange.plusMinutes(tr.duration));
                    assert newClass.endTime != null;
                    if (newClass.endTime.compareTo(aFirstClass.startTime) <= 0) {
                        break;
                    }
                }
                newClass = new Class(aFirstClass.date, aFirstClass.endTime,
                        aFirstClass.endTime.plusMinutes(tr.duration));
                assert newClass.endTime != null;
                if (newClass.endTime.compareTo(tr.endTimeRange) <= 0) {
                    break;
                } else {
                    assert aFirstClass.date != null;
                    newClass = new Class(aFirstClass.date.plusDays(1),
                            tr.startTimeRange, tr.startTimeRange.plusMinutes(tr.duration));
                }
                break;
            }
            Class aSecondClass = list.get(i + 1).getAClass();

            // check whether a class before the first class is possible
            Class fromTrStartTime = new Class(aFirstClass.date, tr.startTimeRange,
                    tr.startTimeRange.plusMinutes(tr.duration));
            if (!ClassStorage.hasConflict(fromTrStartTime.startTime, fromTrStartTime.endTime,
                    aFirstClass.startTime, aFirstClass.endTime)
                    && !ClassStorage.hasConflict(fromTrStartTime.startTime, fromTrStartTime.endTime,
                    aSecondClass.startTime, aSecondClass.endTime)) {
                assert fromTrStartTime.endTime != null;
                newClass = fromTrStartTime;
            }

            assert aFirstClass.date != null;
            if (aFirstClass.date.equals(aSecondClass.date)) {
                /*
                 * That means they are on the same day
                 * 1st case: When they are side by side. Since the case where it is before the class has been handled,
                 *           we try finding a slot after the end of the secondClass.
                 * 2nd case: When there is a gap, but it is not big enough. If there is a gap, then it is actually
                 *           the same situation as the first case so, it becomes <= tr.duration rather than just == 0
                 *           for the initial first case.
                 * 3rd case: When there is a gap just nice or too big
                 */
                assert aFirstClass.endTime != null;
                assert aSecondClass.startTime != null;
                if (aFirstClass.endTime.until(aSecondClass.startTime, ChronoUnit.MINUTES) > tr.duration) {
                    // you are now handling the 3rd case, the 1st case does not matter since if it is outside of the
                    // duration, it is the next iteration's problem
                    newClass = new Class(aFirstClass.date, aFirstClass.endTime,
                            aFirstClass.endTime.plusMinutes(tr.duration));
                    break;
                }
            } else {
                /*
                 * That means they are not on the same day.
                 * Case 1: In which case, you try creating a class which starts after the end of the same class,
                 *         since there wouldn't be a conflict between the 2 class dates.
                 * Case 2: If there are a number of days gap, more than 1, and the timing clashes, then you can
                 *         definitely go to the next day.
                 */
                assert aFirstClass.endTime != null;
                newClass = new Class(aFirstClass.date, aFirstClass.endTime,
                        aFirstClass.endTime.plusMinutes(tr.duration));
                assert newClass.endTime != null;
                if (newClass.endTime.compareTo(tr.endTimeRange) <= 0) {
                    break;
                } else {
                    assert newClass.date != null;
                    assert aSecondClass.date != null;
                    if (newClass.date.until(aSecondClass.date, ChronoUnit.DAYS) > 1) {
                        newClass = new Class(aFirstClass.date.plusDays(1), tr.startTimeRange,
                                tr.startTimeRange.plusMinutes(tr.duration));
                        break;
                    }
                }
            }
        }

        return newClass;
    }

    private static Class findAvailableClassWithSingleRecord(TimeRange tr, LocalDate currDate, List<Student> list) {
        Class newClass;
        Class classToCompare = list.get(0).getAClass();
        // When the startTimeRange is before the earliest slot
        assert classToCompare.endTime != null;
        assert classToCompare.startTime != null;

        if (classToCompare.endTime.compareTo(tr.startTimeRange) <= 0
                || (classToCompare.startTime.compareTo(tr.startTimeRange) >= 0
                && tr.startTimeRange.plusMinutes(tr.duration).compareTo(classToCompare.startTime) <= 0)) {
            newClass = new Class(currDate, tr.startTimeRange,
                    tr.startTimeRange.plusMinutes(tr.duration));
        } else if (classToCompare.startTime.compareTo(tr.endTimeRange) >= 0
                || (classToCompare.endTime.plusMinutes(tr.duration).compareTo(tr.endTimeRange) <= 0)) {
            // When the startTimeRange is after the earliest slot
            newClass = new Class(currDate, classToCompare.endTime,
                    classToCompare.endTime.plusMinutes(tr.duration));
        } else {
            // Else go to the next day and find the next available slot
            newClass = new Class(currDate.plusDays(1), tr.startTimeRange,
                    tr.startTimeRange.plusMinutes(tr.duration));
        }
        return newClass;
    }


    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentList // instanceof handles nulls
                        && internalList.equals(((UniqueStudentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSameStudent(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
