package seedu.intrack.model.internship;

import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.intrack.model.tag.Tag;

/**
 * Represents an Internship in the internship tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {

    // Identity fields
    private final Name name;
    private final Position position;
    private final Email email;

    // Data fields
    private final Status status;
    private final Website website;
    private final List<Task> tasks = new ArrayList<>();
    private final Salary salary;
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;
    private final List<LocalDateTime> tasksDates = new ArrayList<>();

    //Determine if have upcoming tasks
    private boolean hasUpcomingTasks;

    /**
     * Every field must be present and not null.
     */

    public Internship(Name name, Position position, Status status, Email email, Website website,
                      List<Task> tasks, Salary salary, Set<Tag> tags, Remark remark) {
        requireAllNonNull(name, position, email, status, website, tasks, salary, tags, remark);
        this.name = name;
        this.position = position;
        this.status = status;
        this.email = email;
        this.website = website;
        this.tasks.addAll(tasks);
        this.salary = salary;
        this.tags.addAll(tags);
        this.remark = remark;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            LocalDateTime x = task.getTaskTime();
            tasksDates.add(x);
        }
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    public Salary getSalary() {
        return salary;
    }

    public Email getEmail() {
        return email;
    }

    public Website getWebsite() {
        return website;
    }

    /**
     * Returns an immutable task list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Checks if tasks list is empty or not
     * @return boolean indicating whether the tasks list is empty or not.
     */
    public boolean isTaskListEmpty() {
        return this.tasks.isEmpty();
    }


    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns true if both internships have the same name.
     * This defines a weaker notion of equality between two internships.
     */
    public boolean isSameInternship(Internship otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && otherInternship.getName().equals(getName())
                && otherInternship.getPosition().equals(getPosition());
    }

    /**
     * Returns the date and time of the task of the closest upcoming task, or earliest task if there aren't any
     * @return LocalDateTime of the nearest date and time
     */
    public LocalDateTime getNearestTaskDate() {
        int i = 0;
        Collections.sort(tasksDates);
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime nearestDate = tasksDates.get(i);
        while (nearestDate.isBefore(currentDate)) {
            if (i == tasksDates.size() - 1) {
                //just gets the latest task u got, even if expired
                nearestDate = tasksDates.get(i);
                break;
            }
            if (nearestDate.isAfter(currentDate)) {
                //once gets nearest time, breaks
                nearestDate = tasksDates.get(i);
                break;
            }
            i++;
        }
        return nearestDate;
    }

    /**
     * Adds a tag to the set of existing tags.
     * @param tag to be added.
     */
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    /**
     * Deletes a tag from the set of existing tags.
     * @param tag to be deleted.
     */
    public void deleteTag(Tag tag) {
        tags.remove(tag);
    }

    /**
     * Deletes all tags from the set of existing tags.
     */
    public void clearTag() {
        tags.clear();
    }

    /**
     * Checks if an internship have an upcoming task
     * @return a boolean determining if an internship have an upcoming task
     */
    public boolean isHasUpcomingTasks() {
        if (tasksDates.isEmpty()) {
            return false;
        } else {
            LocalDateTime earliestTime = getNearestTaskDate();
            LocalDateTime now = LocalDateTime.now();
            boolean hasUpcomeTask = earliestTime.isAfter(now);
            return hasUpcomeTask;
        }
    }

    /**
     * Returns the date and time of the task with the furthest date and time to the current time
     * @return LocalDateTime of the furthest date and time
     */
    public LocalDateTime getFurthestTaskDate() {
        int maxIndex = tasks.size() - 1;
        Collections.sort(tasksDates);
        LocalDateTime furthestDate = tasksDates.get(maxIndex);
        return furthestDate;
    }

    /**
     * Returns true if both internships have the same identity and data fields.
     * This defines a stronger notion of equality between two internships.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Internship)) {
            return false;
        }

        Internship otherInternship = (Internship) other;
        return otherInternship.getName().equals(getName())
                && otherInternship.getPosition().equals(getPosition())
                && otherInternship.getStatus().equals(getStatus())
                && otherInternship.getEmail().equals(getEmail())
                && otherInternship.getWebsite().equals(getWebsite())
                && otherInternship.getTasks().equals(getTasks())
                && otherInternship.getSalary().equals(getSalary())
                && otherInternship.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, position, email, website, salary, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Company: ")
                .append(getName())
                .append("; Position: ")
                .append(getPosition())
                .append("; Status: ")
                .append(getStatus())
                .append("; Email: ")
                .append(getEmail())
                .append("; Website: ")
                .append(getWebsite())
                .append("; Salary: ")
                .append(getSalary())
                .append("; ");

        List<Task> tasks = getTasks();
        if (!tasks.isEmpty()) {
            builder.append("Tasks: ");
            for (Task task : tasks) {
                builder.append(task + "; ");
            }
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("Tags: ");
            for (Tag tag : tags) {
                builder.append(tag + "; ");
            }
        }

        builder.append("Remark: ")
                .append(getRemark());

        return builder.toString();
    }

}
