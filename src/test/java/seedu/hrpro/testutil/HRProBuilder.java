package seedu.hrpro.testutil;

import seedu.hrpro.model.HRPro;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code HRPro ab = new HRProBuilder().withProject("John", "Doe").build();}
 */
public class HRProBuilder {

    private HRPro hrPro;

    public HRProBuilder() {
        hrPro = new HRPro();
    }

    public HRProBuilder(HRPro hrPro) {
        this.hrPro = hrPro;
    }

    /**
     * Adds a new {@code Project} to the {@code HRPro} that we are building.
     */
    public HRProBuilder withProject(Project project) {
        hrPro.addProject(project);
        return this;
    }

    /**
     * Adds a new {@code Staff} to the {@code HRPro} that we are building.
     */
    public HRProBuilder withStaff(Staff staff) {
        hrPro.addStaff(staff);
        return this;
    }

    /**
     * Adds a new {@code Task} to the {@code HRPro} that we are building.
     */
    public HRProBuilder withTask(Task task) {
        hrPro.addTask(task);
        return this;
    }

    public HRPro build() {
        return hrPro;
    }
}
