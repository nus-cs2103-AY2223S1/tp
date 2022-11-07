package seedu.hrpro.testutil;

import seedu.hrpro.model.HrPro;

/**
 * A utility class containing a list of Project and Task objects to be used in tests.
 */
public class TypicalHrPro {

    /**
     * Returns an {@code HrPro} with all the typical projects and tasks.
     */
    public static HrPro getTypicalHrPro() {
        HrPro hp = new HrPro();

        hp.setProjects(TypicalProjects.getTypicalProjects());
        hp.setTasks(TypicalTasks.getTypicalTask());
        hp.setStaffList(TypicalStaff.getTypicalStaff());

        return hp;
    }
}
