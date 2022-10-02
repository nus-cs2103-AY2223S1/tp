package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.Deadline;
import seedu.address.model.commission.Fee;
import seedu.address.model.commission.Title;

/**
 * Jackson-friendly version of {@link Commission}.
 */
public class JsonAdaptedCommission {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Commission's %s field is missing!";

    private final String title;
    private final Double fee;
    private final LocalDate deadline;

    /**
     * Constructs a {@code JsonAdaptedTitle} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedCommission(@JsonProperty("title") String title, @JsonProperty("fee") Double fee,
                                 @JsonProperty("deadline") LocalDate deadline) {
        this.title = title;
        this.fee = fee;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedCommission(Commission source) {
        title = source.getTitle().title;
        fee = source.getFee().fee;
        deadline = source.getDeadline().deadline;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Commission toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }

        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }

        final Title modelTitle = new Title(title);

        if (fee == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Fee.class.getSimpleName()));
        }

        if (!Fee.isValidFee(fee)) {
            throw new IllegalValueException(Fee.MESSAGE_CONSTRAINTS);
        }

        final Fee modelFee = new Fee(fee);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }

        final Deadline modelDeadline = new Deadline(deadline);

        return new Commission(modelTitle, modelFee, modelDeadline);

    }

}
