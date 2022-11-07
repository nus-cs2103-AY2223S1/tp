package seedu.hrpro.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.project.Budget;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.UniqueStaffList;
import seedu.hrpro.model.tag.Tag;
import seedu.hrpro.model.util.SampleDataUtil;

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
    private UniqueStaffList staffList = new UniqueStaffList();
    private Set<Tag> tags;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        projectName = new ProjectName(DEFAULT_PROJECT_NAME);
        budget = new Budget(DEFAULT_BUDGET);
        deadline = new Deadline(DEFAULT_DEADLINE);
        tags = new HashSet<>();
        staffList = new UniqueStaffList();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        projectName = projectToCopy.getProjectName();
        budget = projectToCopy.getBudget();
        deadline = projectToCopy.getDeadline();
        tags = new HashSet<>(projectToCopy.getTags());
        for (Staff staff : projectToCopy.getStaffList()) {
            Staff staffToCopy = new StaffBuilder(staff).build();
            this.staffList.add(staffToCopy);
        }
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

    /**
     * Sets the {@code StaffList} of the {@code Project} that we are building.
     */
    public ProjectBuilder withStaffList(UniqueStaffList staffList) {
        this.staffList = staffList;
        return this;
    }

    /**
     * Adds a staff to the {@code StaffList} of the {@code Project} that we are building.
     */
    public ProjectBuilder withStaff(Staff staff) {
        this.staffList.add(staff);
        return this;
    }

    /**
     * Builds a {@code Project} with the correct staff list field.
     */
    public Project build() {
        Project project = new Project(projectName, budget, deadline, tags);
        for (Staff staff : this.staffList) {
            project.getStaffList().add(staff);
        }
        return project;
    }
}
