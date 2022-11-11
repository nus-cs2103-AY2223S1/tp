package coydir.storage;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import coydir.commons.exceptions.IllegalValueException;
import coydir.model.person.Leave;


/**
 * Jackson-friendly version of {@link Leave}.
 */
class JsonAdaptedLeave {

    private final String startDate;
    private final String endDate;

    /**
     * Constructs a {@code JsonAdaptedLeave} with the given {@code startDate} and {@code endDate}.
     */
    @JsonCreator
    public JsonAdaptedLeave(@JsonProperty("startDate") String startDate, @JsonProperty("endDate") String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Converts a given {@code Leave} into this class for Jackson use.
     */
    public JsonAdaptedLeave(Leave source) {
        this.startDate = source.startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.endDate = source.endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    /**
     * Converts this Jackson-friendly adapted Leave object into the model's {@code Leave} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Leave.
     */
    public Leave toModelType() throws IllegalValueException {
        if (!Leave.isValidLeave(startDate, endDate)) {
            throw new IllegalValueException("Leave not valid");
        }
        return new Leave(startDate, endDate);
    }

}
