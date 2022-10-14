package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import seedu.address.model.customer.Customer;
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
    private final List<JsonAdaptedIteration> iterations = new ArrayList<>();
    private final Boolean isCompleted;

    /**
     * Constructs a {@code JsonAdaptedCommission} with the given commission details.
     */
    @JsonCreator
    public JsonAdaptedCommission(@JsonProperty("title") String title, @JsonProperty("description") String description,
                                 @JsonProperty("fee") Double fee,
                                 @JsonProperty("deadline") LocalDate deadline,
                                 @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                 @JsonProperty("iterations") List<JsonAdaptedIteration> iterations,
                                 @JsonProperty("isCompleted") Boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.deadline = deadline;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }

        if (iterations != null) {
            this.iterations.addAll(iterations);
        }

        this.isCompleted = isCompleted;
    }

    /**
     * Converts a given {@code Commission} into this class for Jackson use.
     */
    public JsonAdaptedCommission(Commission source) {
        title = source.getTitle().title;
        description = source.getDescription().orElse(Description.NO_DESCRIPTION).description;
        fee = source.getFee().fee;
        deadline = source.getDeadline().deadline;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        iterations.addAll(source.getIterations().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedIteration::new)
                .collect(Collectors.toList()));
        isCompleted = source.getCompletionStatus().isCompleted;
    }

    /**
     * Converts this Jackson-friendly adapted commission object into the model's {@code Commission} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted commission.
     */
    public Commission toModelType(Customer customer) throws IllegalValueException {
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

        if (isCompleted == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompletionStatus.class.getSimpleName()));
        }

        final CompletionStatus modelCompletionStatus = new CompletionStatus(isCompleted);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }

        Optional<Description> modelDescription;
        if (description.isEmpty()) {
            modelDescription = Optional.empty();
        } else {
            modelDescription = Optional.of(new Description(description));
        }

        final List<Tag> commissionTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            commissionTags.add(tag.toModelType());
        }

        final Set<Tag> modelTags = new HashSet<>(commissionTags);

        Commission.CommissionBuilder commissionBuilder = new Commission.CommissionBuilder(modelTitle, modelFee,
                modelDeadline, modelCompletionStatus, modelTags);
        modelDescription.ifPresent(commissionBuilder::setDescription);

        for (JsonAdaptedIteration iteration : iterations) {
            commissionBuilder.addIteration(iteration.toModelType());
        }

        return commissionBuilder.build(customer);
    }

}
