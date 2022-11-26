package seedu.address.model.task;

import java.util.function.Predicate;

import seedu.address.model.exam.Exam;

/**
 * Tests that a {@code Task} fulfils the given predicate.
 */
public class TaskLinkedToExamPredicate implements Predicate<Task> {
    private final Exam examToCheck;

    /**
     * Tests that a {@code Task} is linked to a specific exam.
     * @param exam Exam to be checked if task is linked with.
     */
    public TaskLinkedToExamPredicate(Exam exam) {
        this.examToCheck = exam;
    }

    @Override
    public boolean test(Task task) {
        return task.isLinked() && task.getExam().equals(examToCheck);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskLinkedToExamPredicate // instanceof handles nulls
                && examToCheck.equals(((TaskLinkedToExamPredicate) other).examToCheck)); // state check
    }
}
