package longtimenosee.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import longtimenosee.commons.exceptions.IllegalValueException;
import longtimenosee.model.policy.AssignedPolicy;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.PolicyDate;
import longtimenosee.model.policy.Premium;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedAssignedPolicy {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "AssignedPolicy's %s field is missing!";

    public final JsonAdaptedPolicy assigned;
    public final String premium;
    public final String startDate;
    public final String endDate;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedAssignedPolicy(@JsonProperty("assigned") JsonAdaptedPolicy assigned,
                                     @JsonProperty("premium") String premium,
                                     @JsonProperty("startDate") String startDate,
                                     @JsonProperty("endDate") String endDate) {
        this.assigned = assigned;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedAssignedPolicy(AssignedPolicy source) {
        assigned = new JsonAdaptedPolicy(source.getPolicy());
        premium = source.getPremium().toString();
        startDate = source.getStartDate().value;
        endDate = source.getEndDate().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public AssignedPolicy toModelType() throws IllegalValueException {
        final Policy modelPolicy = assigned.toModelType();

        if (premium == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Premium.class.getSimpleName()));
        }
        if (!Premium.isValidPremium(premium)) {
            throw new IllegalValueException(Premium.MESSAGE_CONSTRAINTS);
        }
        final Premium modelPremium = new Premium(premium);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PolicyDate.class.getSimpleName()));
        }
        if (!PolicyDate.isValidDate(startDate)) {
            throw new IllegalValueException(PolicyDate.MESSAGE_FORMAT_CONSTRAINTS);
        }
        final PolicyDate modelStartDate = new PolicyDate(startDate);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PolicyDate.class.getSimpleName()));
        }
        if (!PolicyDate.isValidDate(endDate)) {
            throw new IllegalValueException(PolicyDate.MESSAGE_FORMAT_CONSTRAINTS);
        }
        final PolicyDate modelEndDate = new PolicyDate(endDate);

        return new AssignedPolicy(modelPolicy, modelPremium, modelStartDate, modelEndDate);
    }

}
