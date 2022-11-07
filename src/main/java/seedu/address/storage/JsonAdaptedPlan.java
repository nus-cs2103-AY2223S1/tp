package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.portfolio.Plan;

/**
 * Jackson-friendly version of {@link Plan}.
 */
class JsonAdaptedPlan {

    private final String planName;

    /**
     * Constructs a {@code JsonAdaptedPlan} with the given {@code planName}.
     */
    @JsonCreator
    public JsonAdaptedPlan(String planName) {
        this.planName = planName;
    }

    /**
     * Converts a given {@code Plan} into this class for Jackson use.
     */
    public JsonAdaptedPlan(Plan source) {
        planName = source.value;
    }

    @JsonValue
    public String getTagName() {
        return planName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Plan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted plan.
     */
    public Plan toModelType() throws IllegalValueException {
        if (!Plan.isValidPlan(planName)) {
            throw new IllegalValueException(Plan.MESSAGE_CONSTRAINTS);
        }
        return new Plan(planName);
    }

}
