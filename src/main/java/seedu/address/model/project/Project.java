package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.deadline.Deadline;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.UniqueStaffList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Project in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project implements Comparable<Project> {
    //Identity fields
    private final ProjectName projectName;

    //Data fields
    private final Budget budget;
    private final Deadline deadline;
    private final Set<Tag> tags = new HashSet<>();
    private final UniqueStaffList staffList = new UniqueStaffList();

    /**
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName, Budget budget, Deadline deadline, Set<Tag> tags) {
        requireAllNonNull(projectName, budget, deadline, tags);
        this.projectName = projectName;
        this.budget = budget;
        this.deadline = deadline;
        this.tags.addAll(tags);
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public Budget getBudget() {
        return budget;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public UniqueStaffList getStaffList() {
        return staffList;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both projects have the same name.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getProjectName().equals(getProjectName());
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return otherProject.getProjectName().equals(getProjectName())
                && otherProject.getBudget().equals(getBudget())
                && otherProject.getDeadline().equals(getDeadline())
                && otherProject.getStaffList().equals(getStaffList())
                && otherProject.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(projectName, budget, deadline, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectName())
                .append("; Budget: ")
                .append(getBudget())
                .append("; Deadline: ")
                .append(getDeadline());

        Set<Tag> tags = getTags();
        ObservableList<Staff> staffList = getStaffList().asUnmodifiableObservableList();

        if (!staffList.isEmpty()) {
            builder.append("; Staff: ");
            staffList.forEach(builder::append);
        }

        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Compares projects by deadline.
     */
    @Override
    public int compareTo(Project other) {
        return this.getDeadline().compareTo(other.getDeadline());
    }
}
