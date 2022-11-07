package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.person.Homework;

/**
 * Jackson-friendly version of {@link Homework}.
 */
class JsonAdaptedHomework {

    private final String homeworkName;

    /**
     * Constructs a {@code JsonAdaptedHomework} with the given {@code homeworkName}.
     */
    @JsonCreator
    public JsonAdaptedHomework(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    /**
     * Converts a given {@code Homework} into this class for Jackson use.
     */
    public JsonAdaptedHomework(Homework source) {
        homeworkName = source.toString();
    }

    @JsonValue
    public String getHomeworkName() {
        return homeworkName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Homework} object.
     */
    public Homework toModelType() {
        if (homeworkName.contains("[✓]")) {
            String newHw = homeworkName.replace(" [✓]", "");
            Homework hw = new Homework(newHw);
            hw.markAsDone();
            return hw;
        } else {
            String newHw = homeworkName.replace(" [ ]", "");
            return new Homework(newHw);
        }
    }

}
