package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * This class represents a Jackson friendly version of the Exam.
 */
public class JsonAdaptedExam {
    public static final String MISSING_EXAM_DESCRIPTION = "Exam description is not present";
    public static final String MISSING_MODULE_CODE = "Module code is not present";
    public static final String MISSING_EXAM_DATE = "Exam date is not present";
    private final String description;
    private final String moduleCode;
    private final String date;

    /**
     * Builds a {@code JsonAdaptedExam} with the description and module code and date.
     *
     * @param description The description of the exam.
     * @param moduleCode The module code of the exam.
     * @param date The date of the exam.
     */
    public JsonAdaptedExam(@JsonProperty("description") String description,
                           @JsonProperty("modCode") String moduleCode, @JsonProperty("date") String date) {
        this.description = description;
        this.moduleCode = moduleCode;
        this.date = date;
    }

    /**
     * Converts an existing exam into {@code JsonAdaptedExam} object
     *
     * @param exam The exam object being converted.
     */
    public JsonAdaptedExam(Exam exam) {
        description = exam.getDescription().description;
        moduleCode = exam.getModule().getModuleCode().moduleCode;
        date = exam.getExamDate().dateWithoutFormatting;
    }

    /**
     * Converts this Jackson-friendly exam object into a Exam object for the model.
     *
     * @return The Exam object which has been created.
     * @throws IllegalValueException if the exam has invalid fields.
     */
    public Exam toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(MISSING_EXAM_DESCRIPTION);
        }
        if (moduleCode == null) {
            throw new IllegalValueException(MISSING_MODULE_CODE);
        }
        if (date == null) {
            throw new IllegalValueException(MISSING_EXAM_DATE);
        }
        if (!ExamDescription.isValidDescription(description)) {
            throw new IllegalValueException(ExamDescription.DESCRIPTION_CONSTRAINTS);
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MODULE_CODE_CONSTRAINTS);
        }

        final ExamDescription examDescription = new ExamDescription(description);
        final ModuleCode modCode = new ModuleCode(moduleCode);
        final Module module = new Module(modCode);
        final ExamDate examDate = new ExamDate(date);
        return new Exam(module, examDescription, examDate);
    }

}
