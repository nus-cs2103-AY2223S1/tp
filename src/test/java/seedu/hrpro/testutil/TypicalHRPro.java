package seedu.hrpro.testutil;

import seedu.hrpro.model.HRPro;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.task.Task;

/**
 * A utility class containing a list of Project and Task objects to be used in tests.
 */
public class TypicalHRPro {

    /**
     * Returns an {@code HRPro} with all the typical projects and tasks.
     */
    public static HRPro getTypicalHRPro() {
        HRPro ab = new HRPro();
        for (Project project : TypicalProjects.getTypicalProjects()) {
            ab.addProject(project);
        }
        for (Task task : TypicalTasks.getTypicalTask()) {
            ab.addTask(task);
        }
        return ab;
    }
}
