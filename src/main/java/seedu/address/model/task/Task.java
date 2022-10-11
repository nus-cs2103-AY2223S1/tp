package seedu.address.model.task;

import java.util.List;

/**
 * Represents a Task in the TaskList.
 */
public class Task {
    private Description description;
    private Deadline deadline;
    private boolean isDone;


    /**
     * A constructor that creates an instance of Task.
     * @param description The description of the task.
     */
    public Task(Description description) {
        this.isDone = false;
        this.description = description;
        // TODO: Edit after implementing Deadline class
        this.deadline = new Deadline("");
    }

    /**
     * Returns description of task.
     * @return Description Details of task.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Returns deadline of task.
     * @return deadline Deadline of task.
     */
    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns true if task is done, false if task is not done.
     * @return boolean indicating task completion status.
     */
    public boolean getTaskStatus() {
        return isDone;
    }

    /**
     * Marks task as done.
     */
    public void markTask() {
        isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void unmarkTask() {
        isDone = false;
    }

    /**
     * Returns true if task contains any of the description or deadline keywords.
     * By default, empty lists will return true.
     *
     * @param descriptionKeywords Possibly empty list containing keywords for {@code Description}.
     * @param deadlineKeywords Possibly empty list containing keywords for {@code Deadline}.
     * @return boolean indicating if task contains supplied keywords.
     */
    public boolean containsKeywords(List<Description> descriptionKeywords, List<Deadline> deadlineKeywords) {
        return (descriptionKeywords.isEmpty() || descriptionKeywords.contains(description))
                && (deadlineKeywords.isEmpty() || deadlineKeywords.contains(deadline));
    }
}
