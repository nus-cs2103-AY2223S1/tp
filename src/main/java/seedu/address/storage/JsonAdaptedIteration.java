package seedu.address.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.iteration.Date;
import seedu.address.model.iteration.Feedback;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.IterationDescription;

/**
 * Jackson-friendly version of {@link Iteration}.
 */
public class JsonAdaptedIteration {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Iteration's %s field is missing!";
    public static final String MISSING_IMAGE_MESSAGE_FORMAT = "Iteration image could not be found!";

    private final LocalDate date;
    private final String description;
    private final String path;
    private final String feedback;

    /**
     * Constructs a {@code JsonAdaptedIteration} with the given iteration details.
     */
    @JsonCreator
    public JsonAdaptedIteration(@JsonProperty("date") LocalDate date, @JsonProperty("description") String description,
                                @JsonProperty("path") String path, @JsonProperty("feedback") String feedback) {
        this.date = date;
        this.description = description;
        this.path = path;
        this.feedback = feedback;
    }

    /**
     * Converts a given {@code Iteration} into this class for Jackson use.
     */
    public JsonAdaptedIteration(Iteration source) {
        date = source.getDate().date;
        description = source.getDescription().description;
        path = String.valueOf(source.getImagePath());
        feedback = source.getFeedback().feedback;
    }

    /**
     * Converts this Jackson-friendly adapted iteration object into the model's {@code Iteration} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted iteration.
     */
    public Iteration toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }

        final Date modelDate = new Date(date);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    IterationDescription.class.getSimpleName()));
        }

        final IterationDescription modelDescription = new IterationDescription(description);

        if (feedback == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Feedback.class.getSimpleName()));
        }
        if (path == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Path.class.getSimpleName()));
        }
        Path modelImagePath = Path.of(path);

        if (Files.notExists(modelImagePath)) {
            throw new IllegalValueException(MISSING_IMAGE_MESSAGE_FORMAT);
        }

        final Feedback modelFeedback = new Feedback(feedback);

        return new Iteration(modelDate, modelDescription, modelImagePath, modelFeedback);
    }
}
