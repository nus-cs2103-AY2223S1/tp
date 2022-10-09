package seedu.address.model.task;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task {

    // Fields (to be added): name, description, priority, category, deadline, person, status

    // Constructor method (to be updated)
    public Task() {

    }

    // Setter methods for all fields (to be updated)
    public void setName() {

    }

    public void setDescription() {

    }

    public void setPriority() {

    }

    public void setCategory() {

    }

    public void setDeadline() {

    }

    public void assignPerson() {

    }

    public void isDone() {

    }

    // Getter methods for all fields (to be updated)
    public void getName() {

    }

    public void getDescription() {

    }

    public void getPriority() {

    }

    public void getCategory() {

    }

    public void getDeadline() {

    }

    public void getPerson() {

    }

    public void getStatus() {

    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     * @param otherTask Task of comparison
     * @return true if both tasks are the same, and false otherwise
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        // return otherTask != null && otherTask.getName().equals(getName());
        return false;
    }

    /**
     * Returns true if both tasks have the same fields.
     * This defines a stronger notion of equality between two tasks.
     * @param other Object of comparison
     * @return true if both tasks have the same fields, and false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
//        return otherTask.getName().equals(getName())
//                && otherTask.getCategory().equals(getCategory())
//                && otherTask.getDeadline().equals(getDeadline())
//                && otherTask.getDescription().equals(getDescription())
//                && otherTask.getPerson().equals(getPerson())
//                && otherTask.getPriority().equals(getPriority())
//                && otherTask.getStatus().equals(getStatus());
        return false;
    }

    /**
     * Method for custom fields hashing.
     * @return a hash code value for the object
     */
    @Override
    public int hashCode() {
//        return Objects.hash(name, description, priority, category, deadline, person, status);
        return 1;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
//        builder.append(getName())
//                .append("; Description: ")
//                .append(getDescription())
//                .append("; Priority: ")
//                .append(getPriority())
//                .append("; Category: ")
//                .append(getCategory())
//                .append("; Deadline: ")
//                .append(getDeadline())
//                .append("; Assigned to: ")
//                .append(getPerson())
//                .append("; Status: ")
//                .append(getStatus());
        return builder.toString();
    }

}
