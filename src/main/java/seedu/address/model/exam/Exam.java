package seedu.address.model.exam;

import seedu.address.model.module.Module;


/**
 * Exam class represents an exam which stores the module code, the
 * description and the date of the exam.
 */
public class Exam {
    private final Module module;
    private final ExamDescription examDescription;
    private final ExamDate examDate;


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
