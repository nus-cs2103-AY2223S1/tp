package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Person's GradeProgress list in the address book.
 * Guarantees: Mutable
 */

public class GradeProgressList {
    public static final String MESSAGE_INVALID_GRADE_PROGRESS_INDEX = "The grade index provided is invalid!";

    public final List<GradeProgress> gradeProgressList;

    /**
     * Constructs a {@code GradeProgressList}.
     */
    public GradeProgressList() {
        gradeProgressList = new ArrayList<>();
    }

    public GradeProgressList(List<GradeProgress> gradeProgressList) {
        this.gradeProgressList = gradeProgressList;
    }

    /**
     * Adds grade progress to the grade progress list.
     *
     * @param gradeProgress The gradeProgress object to be added.
     */
    public void addGradeProgress(GradeProgress gradeProgress) {
        gradeProgressList.add(gradeProgress);
    }

    public void clearList() {
        gradeProgressList.clear();
    }

    /**
     * Edits the grade at the given index with the new given grade.
     *
     * @param index of grade to be edited
     * @param gradeProgress that replaces the old grade
     */
    public void editAtIndex(Index index, GradeProgress gradeProgress) {
        int indexToEdit = index.getZeroBased();
        if (indexToEdit >= gradeProgressList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_GRADE_PROGRESS_INDEX);
        }
        gradeProgressList.set(indexToEdit, gradeProgress);
    }

    /**
     * Returns true if a given {@code Index} is a valid index in the list.
     */
    public boolean isValidIndex(Index index) {
        return index.getZeroBased() < gradeProgressList.size();
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Grade Progress: ");
        if (gradeProgressList.isEmpty()) {
            description.append("No Grade Progress found!\n");
        } else {
            description.append("[");
        }
        for (int i = 0; i < gradeProgressList.size(); i++) {
            description.append(i + 1).append(". ").append(gradeProgressList.get(i));
            if (i != gradeProgressList.size() - 1) {
                description.append(", ");
            } else {
                description.append("]");
            }
        }
        return description.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeProgressList // instanceof handles nulls
                && gradeProgressList.equals(((GradeProgressList) other).gradeProgressList)); // state check
    }

    @Override
    public int hashCode() {
        return gradeProgressList.hashCode();
    }
}
