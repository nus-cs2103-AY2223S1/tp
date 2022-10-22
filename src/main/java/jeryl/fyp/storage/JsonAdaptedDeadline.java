package jeryl.fyp.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import jeryl.fyp.commons.exceptions.IllegalValueException;
import jeryl.fyp.logic.parser.ParserUtil;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.DeadlineName;

/**
 * Jackson-friendly version of {@link Deadline}.
 */
class JsonAdaptedDeadline {

    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedDeadline} with the given {@code deadline}.
     */
    @JsonCreator
    public JsonAdaptedDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Deadline} into this class for Jackson use.
     */
    public JsonAdaptedDeadline(Deadline deadline) {
        this.deadline = deadline.toString();
    }

    @JsonValue
    public String getDeadline() {
        return deadline;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Deadline} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted deadline.
     */
    public Deadline toModelType() throws IllegalValueException {
        String[] deadlineComponents = deadline.split(", deadline:");
        if (deadlineComponents.length != 2) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        if (!DeadlineName.isValidDeadlineName(deadlineComponents[0])) {
            throw new IllegalValueException(DeadlineName.MESSAGE_CONSTRAINTS);
        }
        try {
            DeadlineName deadlineName = ParserUtil.parseDeadlineName(deadlineComponents[0]);
            LocalDateTime deadlineDateTime = ParserUtil.parseDeadlineDatetime(deadlineComponents[1]);
            return new Deadline(deadlineName, deadlineDateTime);
        } catch (Exception e) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
    }

}
