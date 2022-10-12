package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;

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
    public void editAtIndex(Index index, Homework homework) throws CommandException {
        int indexToEdit = index.getZeroBased();
        if (indexToEdit >= homeworkList.size()) {
            throw new CommandException(MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        homeworkList.set(indexToEdit, homework);
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

        StringBuilder description = new StringBuilder("Homework:\n");
        for (int i = 0; i < 2; i++) {
            description.append(i + 1).append(". ").append(homeworkList.get(i)).append("\n");
        }
        description.append("...\n");
        return description.toString();
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Homework:\n");
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
