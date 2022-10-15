package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Person's homework list in the address book.
 * Guarantees: list is present, and list and objects list are not null.
 */

public class HomeworkList {
    public final List<Homework> homeworkList;

    /**
     * Constructs a {@code HomeworkList}.
     */
    public HomeworkList() {
        homeworkList = new ArrayList<>();
    }

    /**
     * Constructs a {@code HomeworkList} from a given List.
     */
    public HomeworkList(List<Homework> homeworkList) {
        requireNonNull(homeworkList);
        this.homeworkList = homeworkList;
    }

    /**
     * Adds a homework to the homework list.
     *
     * @param homework The homework object to be added.
     */
    public void addHomework(Homework homework) {
        requireNonNull(homework);
        homeworkList.add(homework);
    }

    /**
     * Returns a String description of the homework list. If homework list size is greater than two,
     * only the first two are shown.
     *
     * @return a truncated homework list.
     */
    public String shortDescription() {
        if (homeworkList.size() <= 2) {
            return toString();
        }

        StringBuilder description = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            description.append(i + 1).append(". ").append(homeworkList.get(i)).append("\n");
        }
        description.append("...\n");
        return description.toString();
    }

    /**
     * Clears the homework list.
     */
    public void clearList() {
        homeworkList.clear();
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        if (homeworkList.isEmpty()) {
            description.append("No homework found!\n");
        }
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
