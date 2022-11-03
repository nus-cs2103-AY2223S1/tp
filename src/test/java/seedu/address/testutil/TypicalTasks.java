package seedu.address.testutil;

import static seedu.address.testutil.TypicalExams.getTypicalExams;
import static seedu.address.testutil.TypicalModules.getTypicalModules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK1 = new TaskBuilder().withModule("cs2001")
        .withTaskDescription("description 1")
        .withStatus("incomplete")
        .build();

    public static final Task TASK_A = new TaskBuilder().withModule("cs2030")
        .withTaskDescription("Task A")
        .withStatus("incomplete")
        .build();
    public static final Task TASK_B = new TaskBuilder().withModule("cs2030")
        .withTaskDescription("Task B")
        .withStatus("incomplete")
        .build();
    public static final Task TASK_C = new TaskBuilder().withModule("cs2040")
        .withTaskDescription("Task C")
        .withStatus("incomplete")
        .build();
    public static final Task TASK_D = new TaskBuilder().withModule("cs2040")
        .withTaskDescription("Task D")
        .withStatus("complete")
        .withExam(getTypicalExams().get(0))
        .build();
    public static final Task TASK_E = new TaskBuilder().withModule("cs2100")
        .withTaskDescription("Task E")
        .withStatus("complete")
        .build();
    public static final Task TASK_F = new TaskBuilder().withModule("cs2100")
        .withTaskDescription("Task F")
        .withStatus("complete")
        .build();

    public static final Task TASK_G = new TaskBuilder().withModule("cs2100")
            .withTaskDescription("Task G")
            .withPriorityTag(new PriorityTagBuilder().build())
            .build();

    public static final Task TASK_H = new TaskBuilder().withModule("cs2103t")
            .withTaskDescription("Task H")
            .withDeadlineTag(new DeadlineTagBuilder().build())
            .build();

    public static final Task TASK_I = new TaskBuilder().withModule("cs2103t")
            .withTaskDescription("Task I")
            .build();
    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks, typical exams and typical modules.
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
        return new ArrayList<>(Arrays.asList(TASK_A, TASK_B, TASK_C, TASK_D, TASK_E, TASK_F,
                TASK_G, TASK_H, TASK_I));
    }
}
