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

    public static final String DEFAULT_EXAM_DESCRIPTION = "Final Exam";
    public static final String DEFAULT_MODULE = "CS2030S";
    public static final String DEFAULT_EXAM_DATE = "01-11-2023";

    private ExamDescription examDescription;
    private Module module;
    private ExamDate examDate;

    /**
     * Creates a {@code ExamBuilder} with the default details.
     */
    public ExamBuilder() {
        Module m = new Module(new ModuleCode(DEFAULT_MODULE));
        DistinctModuleList list =  new DistinctModuleList();
        list.addModule(m);
        examDescription = new ExamDescription(DEFAULT_EXAM_DESCRIPTION);
        examDate = new ExamDate(DEFAULT_EXAM_DATE);
        module = new Module(new ModuleCode(DEFAULT_MODULE));
    }


    public ExamBuilder(Exam examToCopy) {
        examDescription = examToCopy.getDescription();
        module = examToCopy.getModule();
        examDate = examToCopy.getExamDate();
    }


    public ExamBuilder withDescription(String examDescription) {
        this.examDescription = new ExamDescription(examDescription);
        return this;
    }

    public ExamBuilder withModule(String moduleCode) {
        this.module = new Module(new ModuleCode(moduleCode));
        return this;
    }
    public ExamBuilder withDate(String date) {
        this.examDate = new ExamDate(date);
        return this;
    }

    public Exam build() {
        return new Exam(module, examDescription,examDate);
    }

}
