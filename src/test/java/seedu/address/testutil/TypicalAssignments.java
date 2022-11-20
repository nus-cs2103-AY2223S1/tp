package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {
    public static final Task FIRST = new AssignmentBuilder().withTitle("CS2030S Homework Assignment")
            .withDescription("Due on 23rd March").withStudents("Adam, Bob").build();
    public static final Task SECOND = new AssignmentBuilder().withTitle("Tutorial 3").withDescription("URGENT")
            .withStudents("Adam, Bob, Charles, Estelle").build();
    public static final Task THIRD = new AssignmentBuilder().withTitle("Lab 33")
            .withDescription("Low priority").withStudents("Adam").build();
    public static final Task FOURTH = new AssignmentBuilder().withTitle("Assignment 1").withDescription("Easy")
            .withStudents("Adam, Bob, Charles, Estelle, Fred").build();
    public static final Task FIFTH = new AssignmentBuilder().withTitle("Assignment 2").withDescription("Hard")
            .withStudents("Adam, Charles, Fred").build();

    private TypicalAssignments() {
    }

    /**
     * Returns a {@code TaskBook} with all the typical tasks.
     *
     * @return
     */
    public static TaskBook getTypicalTaskBookWithAssignments() {
        TaskBook tb = new TaskBook();
        for (Task task : getTypicalTasks()) {
            tb.addTask(task);
        }
        return tb;
    }

    private static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH));
    }
}
