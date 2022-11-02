package seedu.classify.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.classify.commons.exceptions.IllegalValueException;
import seedu.classify.model.exam.Exam;

/**
 * Jackson-friendly version of {@link Exam}.
 */
class JsonAdaptedExam {

    private final String exam;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code exam}.
     */
    @JsonCreator
    public JsonAdaptedExam(String exam) {
        this.exam = exam;
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        exam = source.toString();
    }

    @JsonValue
    public String getExam() {
        return exam;
    }

    /**
     * Converts this Jackson-friendly adapted exam object into the model's {@code Exam} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exam.
     */
    public Exam toModelType() throws IllegalValueException {
        if (!Exam.isValidFormat(exam)) {
            throw new IllegalValueException(Exam.MESSAGE_CONSTRAINTS);
        }
        String[] args = exam.split("\\s+");
        String name = args[0];
        String score = args[1];
        if (!Exam.isValidName(name)) {
            throw new IllegalValueException(Exam.MESSAGE_NAME_CONSTRAINTS);
        }
        if (!Exam.isValidScore(score)) {
            throw new IllegalValueException(Exam.MESSAGE_SCORE_CONSTRAINTS);
        }
        return new Exam(exam);
    }

}
