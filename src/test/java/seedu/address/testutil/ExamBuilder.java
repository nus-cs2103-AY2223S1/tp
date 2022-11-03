package seedu.address.testutil;

import seedu.address.model.exam.Exam;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * A utility class to help with building Exam objects.
 */
public class ExamBuilder {

    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_DESCRIPTION = "Midterms";
    public static final String DEFAULT_DATE = "29-12-2022";

    private Module module;
    private ExamDescription examDescription;
    private ExamDate examDate;

    /**
     * Creates a {@code ExamBuilder} with the default details.
     */
    public ExamBuilder() {
        module = new Module(new ModuleCode(DEFAULT_MODULE));
        examDescription = new ExamDescription(DEFAULT_DESCRIPTION);
        examDate = new ExamDate(DEFAULT_DATE);
    }

    /**
     * Initializes the ExamBuilder with the data of {@code examToCopy}.
     */
    public ExamBuilder(Exam examToCopy) {
        module = examToCopy.getModule();
        examDescription = examToCopy.getDescription();
        examDate = examToCopy.getExamDate();
    }

    /**
     * Sets the {@code Module} of the {@code Exam} that we are building.
     */
    public ExamBuilder withModule(String module) {
        this.module = new Module(new ModuleCode(module));
        return this;
    }

    /**
     * Sets the {@code ExamDescription} of the {@code Exam} that we are building.
     */
    public ExamBuilder withDescription(String description) {
        this.examDescription = new ExamDescription(description);
        return this;
    }

    /**
     * Sets the {@code ExamDate} of the {@code Exam} that we are building.
     */
    public ExamBuilder withDate(String date) {
        this.examDate = new ExamDate(date);
        return this;
    }

    public Exam build() {
        return new Exam(module, examDescription, examDate);
    }
}
