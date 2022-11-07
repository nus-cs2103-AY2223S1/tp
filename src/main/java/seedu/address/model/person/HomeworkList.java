package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Person's homework list in the address book.
 * Guarantees: list is present, and list and objects list are not null.
 */

public class HomeworkList {
    public static final String MESSAGE_INVALID_HOMEWORK_INDEX = "The homework index provided is invalid!";

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
     * Clears the homework list.
     */
    public void clearList() {
        homeworkList.clear();
    }

    /**
     * Edits the homework at the given index with the new given homework.
     *
     * @param index of homework to be edited
     * @param homework that replaces the old homework
     */
    public void editAtIndex(Index index, Homework homework) {
        int indexToEdit = index.getZeroBased();
        if (indexToEdit >= homeworkList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        homeworkList.set(indexToEdit, homework);
    }

    /**
     * Homework object of the specified index in the Homework list will be marked
     *
     * @param index index in the Homework list to be marked
     */
    public void markAtIndex(Index index) {
        int indexToMark = index.getZeroBased();
        if (indexToMark >= homeworkList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        homeworkList.get(indexToMark).markAsDone();
    }

    /**
     * Homework object of the specified index in the Homework list will be marked
     *
     * @param index index in the Homework list to be marked
     */
    public void unmarkAtIndex(Index index) {
        int indexToMark = index.getZeroBased();
        if (indexToMark >= homeworkList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        homeworkList.get(indexToMark).markAsUndone();
    }

    /**
     *  Removes the homework at the given index.
     *  @param index of homework to be removed
     */
    public void removeAtIndex(Index index) {
        int indexToEdit = index.getZeroBased();
        if (indexToEdit >= homeworkList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        homeworkList.remove(indexToEdit);

    }

    /**
     * Returns true if a given {@code Index} is a valid index in the list.
     */
    public boolean isValidIndex(Index index) {
        return index.getZeroBased() < homeworkList.size();
    }

    /**
     * Returns a String description of the homework list. If homework list size is greater than two,
     * only the first two are shown.
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
     * Returns an integer of the total number of completed homework in the list
     *
     * @return total number of completed homework in the list
     */
    public int totalCompletedHw() {
        int totalNumberOfCompletedHw = 0;
        for (Homework hw : homeworkList) {
            if (hw.getIsCompleted()) {
                totalNumberOfCompletedHw++;
            }
        }
        return totalNumberOfCompletedHw;
    }

    /**
     * @return the size of the list
     */
    public int size() {
        return homeworkList.size();
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        if (homeworkList.isEmpty()) {
            description.append("No homework found!\n");
        } else {
            description.append(totalCompletedHw())
                    .append("/")
                    .append(size())
                    .append(" Completed!")
                    .append("\n");
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
