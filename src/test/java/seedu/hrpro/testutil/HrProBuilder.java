package seedu.hrpro.testutil;

import seedu.hrpro.model.HrPro;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code HrPro ab = new HrProBuilder().withProject("John", "Doe").build();}
 */
public class HrProBuilder {

    private HrPro hrPro;

    public HrProBuilder() {
        hrPro = new HrPro();
    }

    public HrProBuilder(HrPro hrPro) {
        this.hrPro = hrPro;
    }

    /**
     * Adds a new {@code Project} to the {@code HrPro} that we are building.
     */
    public HrProBuilder withProject(Project project) {
        hrPro.addProject(project);
        return this;
    }

    /**
     * Adds a new {@code Staff} to the {@code HrPro} that we are building.
     */
    public HrProBuilder withStaff(Staff staff) {
        hrPro.addStaff(staff);
        return this;
    }

    /**
     * Adds a new {@code Task} to the {@code HrPro} that we are building.
     */
    public HrProBuilder withTask(Task task) {
        hrPro.addTask(task);
        return this;
    }

    public HrPro build() {
        return hrPro;
    }
}
