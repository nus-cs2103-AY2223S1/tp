package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.project.Budget;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_PROJECT_NAME = "CS2103T TP";
    public static final String DEFAULT_BUDGET = "200";
    public static final String DEFAULT_DEADLINE = "2022-01-01";

    private ProjectName projectName;
    private Budget budget;
    private Deadline deadline;
    private Set<Tag> tags;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        projectName = new ProjectName(DEFAULT_PROJECT_NAME);
        budget = new Budget(DEFAULT_BUDGET);
        deadline = new Deadline(DEFAULT_DEADLINE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        projectName = projectToCopy.getProjectName();
        budget = projectToCopy.getBudget();
        deadline = projectToCopy.getDeadline();
        tags = new HashSet<>(projectToCopy.getTags());
    }

    /**
     * Sets the {@code ProjectName} of the {@code Project} that we are building.
     */
    public ProjectBuilder withName(String name) {
        this.projectName = new ProjectName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code Project} that we are building.
     */
    public ProjectBuilder withBudget(String budget) {
        this.budget = new Budget(budget);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    public Project build() {
        return new Project(projectName, budget, deadline, tags);
    }

}
