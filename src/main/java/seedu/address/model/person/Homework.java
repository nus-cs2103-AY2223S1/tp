package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's homework in the address book.
 * Guarantees: immutable; is always valid
 */
public class Homework {
    public final String value;
    private boolean isCompleted;

    /**
     * Constructs an {@code Homework}.
     *
     * @param homework A description of the homework.
     */
    public Homework(String homework) {
        requireNonNull(homework);
        value = homework;
        isCompleted = false;
    }
    /**
     * Method to indicate homework as done
     *
     */
    public void markAsDone() {
        isCompleted = true;
    }

    /**
     * Method to indicate homework as undone
     *
     */
    public void markAsUndone() {
        isCompleted = false;
    }

    /**
     * Getter method for isCompleted
     * @return boolean value of isCompleted
     */
    public boolean getIsCompleted() {
        return isCompleted;
    }

    /**
     * Setter method for isCompleted
     * @param value value to set on isCompleted
     */
    public void setIsCompleted(boolean value) {
        isCompleted = value;
    }

    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder();
        desc.append(value);
        if (isCompleted) {
            desc.append(" [✓]");
        } else {
            desc.append(" [ ]");
        }
        return desc.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Homework // instanceof handles nulls
                && value.equalsIgnoreCase(((Homework) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
