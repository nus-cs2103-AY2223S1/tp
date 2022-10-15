package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Person's GradeProgress list in the address book.
 * Guarantees: Mutable
 */

public class GradeProgressList {
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
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        if (gradeProgressList.isEmpty()) {
            description.append("No Grade Progress found!\n");
        } else {
            description.append("[");
        }
        for (int i = 0; i < gradeProgressList.size(); i++) {
            description.append(gradeProgressList.get(i));
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
