package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Person's attendance list in the address book.
 */
public class AttendanceList {
    public static final String MESSAGE_INVALID_ATTENDANCE_INDEX = "The attendance index provided is invalid!";

    public final List<Attendance> attendanceList;

    /**
     * Constructs a {@code AttendanceList}.
     */
    public AttendanceList() {
        attendanceList = new ArrayList<>();
    }

    public AttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    /**
     * Adds an attendance to the attendance list.
     * @param attendance The attendance object to be added.
     */
    public void addAttendance(Attendance attendance) {
        attendanceList.add(attendance);
    }

    /**
     * Returns a description of the attendance list. If attendance list size is greater than two,
     * only the first two are shown.
     */
    public String shortDescription() {
        if (attendanceList.isEmpty()) {
            return this.toString();
        }
        if (attendanceList.size() <= 2) {
            return toString();
        }

        StringBuilder description = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            description.append(i + 1).append(". ").append(attendanceList.get(i)).append("\n");
        }
        description.append("...\n");
        return description.toString();
    }

    /**
     * Clears the attendance list.
     */
    public void clearList() {
        attendanceList.clear();
    }

    /**
     * Edits the attendance at the given index with the new given attendance.
     *
     * @param index of attendance to be edited
     * @param attendance that replaces the old attendance
     */
    public void editAtIndex(Index index, Attendance attendance) {
        int indexToEdit = index.getZeroBased();
        if (indexToEdit >= attendanceList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_ATTENDANCE_INDEX);
        }
        attendanceList.set(indexToEdit, attendance);
    }

    /**
     * Attendance object of the specified index in the Attendance list will be marked
     *
     * @param index index in the attendance list to be marked
     */
    public void markAtIndex(Index index) {
        int indexToMark = index.getZeroBased();
        if (indexToMark >= attendanceList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_ATTENDANCE_INDEX);
        }
        attendanceList.get(indexToMark).markAsPresent();
    }

    /**
     * Attendance object of the specified index in the Attendance list will be marked
     *
     * @param index index in the attendance list to be marked
     */
    public void unmarkAtIndex(Index index) {
        int indexToMark = index.getZeroBased();
        if (indexToMark >= attendanceList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_ATTENDANCE_INDEX);
        }
        attendanceList.get(indexToMark).markAsAbsent();
    }

    /**
     * Removes the attendance at the given index.
     * @param index of attendance to be removed
     */
    public void removeAtIndex(Index index) {
        int indexToRemove = index.getZeroBased();
        if (indexToRemove >= attendanceList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_ATTENDANCE_INDEX);
        }
        attendanceList.remove(indexToRemove);
    }

    /**
     * Returns true if a given {@code Index} is a valid index in the list.
     */
    public boolean isValidIndex(Index index) {
        return index.getZeroBased() < attendanceList.size();
    }

    /**
     * Returns size of list
     *
     * @return int size of attendanceList
     */
    public int size() {
        return attendanceList.size();
    }

    /**
     * Returns an integer of the total number of days present in the list
     *
     * @return total number of days present in the list
     */
    public int totalNumberOfDaysPresent() {
        int totalNumberOfDays = 0;
        for (Attendance att : attendanceList) {
            if (att.getIsPresent()) {
                totalNumberOfDays++;
            }
        }
        return totalNumberOfDays;
    }

    /**
     * Returns the percentage of days present, to the nearest integer
     *
     * @return int percentage of days present
     */
    public int percentagePresent() {
        float percentage = (totalNumberOfDaysPresent() * 100 / (float) size());
        return Math.round(percentage);
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        if (attendanceList.isEmpty()) {
            description.append("No attendance found!\n");
        } else {
            description.append(totalNumberOfDaysPresent())
                    .append("/")
                    .append(size())
                    .append(" Days Present,")
                    .append(" ")
                    .append(percentagePresent())
                    .append("%")
                    .append("\n");
        }
        for (int i = 0; i < attendanceList.size(); i++) {
            description.append(i + 1).append(". ").append(this.attendanceList.get(i)).append("\n");
        }
        return description.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AttendanceList
                && this.attendanceList.equals(((AttendanceList) other).attendanceList));
    }

    @Override
    public int hashCode() {
        return this.attendanceList.hashCode();
    }
}
