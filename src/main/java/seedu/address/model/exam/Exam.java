package seedu.address.model.exam;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.EditExamCommand;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * Exam class represents an exam which stores the module code, the
 * description and the date of the exam.
 */
public class Exam {
    private final Module module;
    private final ExamDescription examDescription;
    private final ExamDate examDate;
    private final List<Task> tasksLinked;


    /**
     * The constructor of the Exam class. Sets the module and
     * description, and exam date of the task.
     *
     * @param module The module being added.
     * @param examDescription The description of the exam.
     * @param examDate The date of the exam.
     *
     */
    public Exam(Module module, ExamDescription examDescription, ExamDate examDate) {
        this.module = module;
        this.examDescription = examDescription;
        this.examDate = examDate;
        this.tasksLinked = new ArrayList<>();
    }

    /**
     * The constructor of the Exam class. Sets the module,
     * exam description, exam date and the tasks associated with the exam.
     *
     * @param module The module being added.
     * @param examDescription The description of the exam.
     * @param examDate The date of the exam.
     * @param tasksLinked The list of task the exam is linked to.
     *
     */
    public Exam(Module module, ExamDescription examDescription, ExamDate examDate,
            List<Task> tasksLinked) {
        this.module = module;
        this.examDescription = examDescription;
        this.examDate = examDate;
        this.tasksLinked = tasksLinked;
    }

    public ExamDescription getDescription() {
        return examDescription;
    }

    public Module getModule() {
        return module;
    }

    public ExamDate getExamDate() {
        return examDate;
    }

    public List<Task> getTasksLinked() {
        return tasksLinked;
    }


    /**
     * Returns true if both exams have the same data fields.
     */
    public boolean isSameExam(Exam otherExam) {
        return this.equals(otherExam);
    }

    /**
     * Links the exam to the task in the task list.
     *
     * @param task The task which will be linked to the exam.
     * @return The {@code Exam} object which contains the newly linked task.
     */
    public Exam linkExam(Task task) {
        requireNonNull(task);
        tasksLinked.add(task);
        return new Exam(module, examDescription, examDate, tasksLinked);
    }


    /**
     * Creates and returns a {@code Exam} with the details of {@code this}
     * edited with {@code editExamDescriptor}.
     */
    public Exam edit(EditExamCommand.EditExamDescriptor editExamDescriptor) {
        requireNonNull(editExamDescriptor);
        Module updatedModule = editExamDescriptor.getModule().orElse(module);
        ExamDescription updatedDescription = editExamDescriptor.getDescription().orElse(examDescription);
        ExamDate updatedExamDate = editExamDescriptor.getExamDate().orElse(examDate);
        return new Exam(updatedModule, updatedDescription, updatedExamDate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exam)) {
            return false;
        }

        Exam otherExam = (Exam) other;
        return otherExam.getDescription().equals(getDescription())
                && otherExam.getModule().equals(getModule())
                && otherExam.getExamDate().equals(getExamDate());
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Module: ")
                .append(getModule())
                .append("; ExamDescription: ")
                .append(getDescription())
                .append("; ExamDate: ")
                .append(getExamDate());
        return builder.toString();
    }
}
