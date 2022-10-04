package seedu.address.model.person;

import java.util.ArrayList;

/**
 * Represents a Person's homework list in the address book.
 * Guarantees: -
 */

public class HomeworkList {
    public final ArrayList<Homework> homeworkList;

    /**
     * Constructs a {@code HomeworkList}.
     */
    public HomeworkList() {
        homeworkList = new ArrayList<>();
    }

    /**
     * Adds a homework to the homework list.
     *
     * @param homework The homework object to be added.
     */
    public void addHomework(Homework homework) {
        homeworkList.add(homework);
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Homework:\n");
        for (int i = 0; i < homeworkList.size(); i++) {
            description.append(i + 1).append(". ").append(homeworkList.get(i)).append("\n");
        }
        return description.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HomeworkList // instanceof handles nulls
                && homeworkList.equals(((HomeworkList) other).homeworkList)); // state check
    }

    @Override
    public int hashCode() {
        return homeworkList.hashCode();
    }
}
