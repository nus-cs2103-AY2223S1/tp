package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.CompletionStatus;
import seedu.address.model.commission.Deadline;
import seedu.address.model.commission.Description;
import seedu.address.model.commission.Fee;
import seedu.address.model.commission.Title;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Commission}.
 */
public class JsonAdaptedCommission {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Commission's %s field is missing!";

    private final String title;
    private final String description;
    private final Double fee;
    private final LocalDate deadline;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final Boolean isCompleted;

    /**
     * Constructs a {@code JsonAdaptedTitle} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedCommission(@JsonProperty("title") String title, @JsonProperty("description") String description,
                                 @JsonProperty("fee") Double fee,
                                 @JsonProperty("deadline") LocalDate deadline,
                                 @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                 @JsonProperty("isCompleted") Boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.deadline = deadline;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.isCompleted = isCompleted;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedCommission(Commission source) {
        title = source.getTitle().title;
        description = source.getDescription().description;
        fee = source.getFee().fee;
        deadline = source.getDeadline().deadline;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        isCompleted = source.getCompletionStatus().isCompleted;
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

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }

        final Description modelDescription = new Description(description);

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

        final List<Tag> commissionTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            commissionTags.add(tag.toModelType());
        }

        final Set<Tag> modelTags = new HashSet<>(commissionTags);

        if (isCompleted == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompletionStatus.class.getSimpleName()));
        }

        final CompletionStatus modelCompletionStatus = new CompletionStatus(isCompleted);

        return new Commission(modelTitle, modelDescription, modelFee, modelDeadline, modelTags, modelCompletionStatus);

    }

}
