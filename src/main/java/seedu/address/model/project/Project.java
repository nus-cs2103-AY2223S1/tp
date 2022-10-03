package seedu.address.model.project;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represent a project in HR Pro Max++.
 */
public class Project {

    //Identity fields
    private final ProjectBudget projectBudget;
    private final ProjectClient projectClient;
    private final ProjectDeadline projectDeadline;
    private final ProjectName projectName;

    //Additional info
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Project(ProjectBudget projectBudget, ProjectClient projectClient,
                   ProjectDeadline projectDeadline, ProjectName projectName, Set<Tag> tags) {
        this.projectBudget = projectBudget;
        this.projectClient = projectClient;
        this.projectDeadline = projectDeadline;
        this.projectName = projectName;
        this.tags.addAll(tags);
    }

    public ProjectBudget getProjectBudget() {
        return projectBudget;
    }

    public ProjectClient getProjectClient() {
        return projectClient;
    }

    public ProjectDeadline getProjectDeadline() {
        return projectDeadline;
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectName())
                .append("; Deadline: ")
                .append(getProjectDeadline())
                .append("; Client: ")
                .append(getProjectClient())
                .append("; Budget: ")
                .append(getProjectBudget());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
