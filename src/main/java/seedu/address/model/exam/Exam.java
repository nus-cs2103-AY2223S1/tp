package seedu.address.model.exam;

import seedu.address.model.module.Module;

/**
 * Exam class represents an exam which stores the module code, the
 * description and the date of the exam.
 */
public class Exam {

    private static final String MESSAGE_NO_TASKS_FOR_EXAM = "You have no tasks for this exam";

    private final Module module;
    private final ExamDescription examDescription;
    private final ExamDate examDate;
    private int totalNumOfTasks;
    private int numOfCompletedTasks;

    /**
     * The constructor of the Exam class. Sets the module,
     * exam description and exam date.
     *
     * @param module The module associated with the exam.
     * @param examDescription The description of the exam.
     * @param examDate The date of the exam.
     *
     */
    public Exam(Module module, ExamDescription examDescription, ExamDate examDate) {
        this.module = module;
        this.examDescription = examDescription;
        this.examDate = examDate;
        this.totalNumOfTasks = 0;
        this.numOfCompletedTasks = 0;
    }

    /**
     * The constructor of the Exam class. Sets the module,
     * exam description, exam date, the total number of tasks
     * and number of completed tasks linked to the exam.
     *
     * @param module The module being added.
     * @param examDescription The description of the exam.
     * @param examDate The date of the exam.
     * @param totalNumOfTasks The total number of tasks linked to the exam.
     * @param numOfCompletedTasks The number of completed tasks linked to the exam.
     *
     */
    public Exam(Module module, ExamDescription examDescription, ExamDate examDate,
                int totalNumOfTasks, int numOfCompletedTasks) {
        this.module = module;
        this.examDescription = examDescription;
        this.examDate = examDate;
        this.totalNumOfTasks = totalNumOfTasks;
        this.numOfCompletedTasks = numOfCompletedTasks;
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

    /**
     * Returns true if both exams have the same data fields.
     */
    public boolean isSameExam(Exam otherExam) {
        return this.equals(otherExam);
    }

    public Exam setTotalNumOfTasks(Integer totalNumOfTasks) {
        return new Exam(this.module, this.examDescription, this.examDate, totalNumOfTasks,
            this.numOfCompletedTasks);
    }

    public Exam setNumOfCompletedTasks(Integer numOfCompletedTasks) {
        return new Exam(this.module, this.examDescription, this.examDate, this.totalNumOfTasks,
            numOfCompletedTasks);
    }

    /**
     * Returns the percentage of tasks completed for the exam.
     */
    public double getPercentageCompleted() {
        return (double) numOfCompletedTasks / (double) totalNumOfTasks;
    }

    /**
     * Returns a string representation of the number of completed tasks and number of total tasks linked to the exam.
     */
    public String generateProgressMessage() {
        if (totalNumOfTasks == 0) {
            return MESSAGE_NO_TASKS_FOR_EXAM;
        } else {
            return numOfCompletedTasks + " / " + totalNumOfTasks + " task(s) completed";
        }
    }

    public boolean hasTasks() {
        return !(totalNumOfTasks == 0);
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
