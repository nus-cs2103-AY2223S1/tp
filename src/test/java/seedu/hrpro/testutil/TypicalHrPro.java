package seedu.hrpro.testutil;

import seedu.hrpro.model.HrPro;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.task.Task;

/**
 * A utility class containing a list of Project and Task objects to be used in tests.
 */
public class TypicalHrPro {

    /**
     * Returns an {@code HrPro} with all the typical projects and tasks.
     */
    public static HrPro getTypicalHrPro() {
        HrPro ab = new HrPro();
        for (Project project : TypicalProjects.getTypicalProjects()) {
            ab.addProject(project);
        }
        for (Task task : TypicalTasks.getTypicalTask()) {
            ab.addTask(task);
        }
        return ab;
    }
}
