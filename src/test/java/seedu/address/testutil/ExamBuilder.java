package seedu.address.testutil;

import seedu.address.model.exam.Exam;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.DistinctModuleList;
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
    private int numOfCompletedTasks;
    private int totalNumOfTasks;

    /**
     * Creates a {@code ExamBuilder} with the default details.
     */
    public ExamBuilder() {
        Module m = new Module(new ModuleCode(DEFAULT_MODULE));
        DistinctModuleList list = new DistinctModuleList();
        list.addModule(m);
        examDescription = new ExamDescription(DEFAULT_DESCRIPTION);
        examDate = new ExamDate(DEFAULT_DATE);
        module = new Module(new ModuleCode(DEFAULT_MODULE));
        numOfCompletedTasks = 0;
        totalNumOfTasks = 0;
    }

    /**
     * Initializes the ExamBuilder with the data of {@code examToCopy}.
     */
    public ExamBuilder(Exam examToCopy) {
        module = examToCopy.getModule();
        examDescription = examToCopy.getDescription();
        examDate = examToCopy.getExamDate();
        numOfCompletedTasks = examToCopy.getNumOfCompletedTasks();
        totalNumOfTasks = examToCopy.getTotalNumOfTasks();
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

    /**
     * Sets the {@code numOfCompletedTasks} of the {@code Exam} that we are building.
     */
    public ExamBuilder withNumOfCompletedTasks(int numOfCompletedTasks) {
        this.numOfCompletedTasks = numOfCompletedTasks;
        return this;
    }

    /**
     * Sets the {@code totalNumOfTasks} of the {@code Exam} that we are building.
     */
    public ExamBuilder withTotalNumOfTasks(int totalNumOfTasks) {
        this.totalNumOfTasks = totalNumOfTasks;
        return this;
    }

    public Exam build() {
        return new Exam(module, examDescription, examDate, totalNumOfTasks, numOfCompletedTasks);
    }

}
