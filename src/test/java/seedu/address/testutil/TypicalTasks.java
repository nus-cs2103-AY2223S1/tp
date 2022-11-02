package seedu.address.testutil;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.TypicalExams.getTypicalExams;
import static seedu.address.testutil.TypicalModules.getTypicalModules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASKONE = new TaskBuilder().withTaskDescription("Task one").withModule("CS2030S").build();
    public static final Task TASKTWO = new TaskBuilder().withTaskDescription("Task two").withModule("CS2030S").build();
    public static final Task TASKTHREE = new TaskBuilder().withTaskDescription("HW1").withModule("CS2030S").build();
    public static final Task TASKFOUR = new TaskBuilder().withTaskDescription("HW2").withModule("CS2040S").build();
    public static final Task TASKFIVE = new TaskBuilder().withTaskDescription("HW3").withModule("CS2030S").build();
    public static final Task HOMEWORK = new TaskBuilder().withTaskDescription("homework").withModule("CS2030s").build();
    public static final Task PYP = new TaskBuilder().withTaskDescription("PAST YEAR PAPER").withModule("CS2030s").build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module: getTypicalModules()) {
            ab.addModule(module);
        }
        for (Exam exam: getTypicalExams()) {
            ab.addExam(exam);
        }
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }

        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASKONE,TASKTWO,TASKTHREE, TASKFOUR, TASKFIVE, HOMEWORK,PYP));
    }
}
