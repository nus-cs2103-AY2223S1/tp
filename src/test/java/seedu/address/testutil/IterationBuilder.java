package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.iteration.Date;
import seedu.address.model.iteration.Feedback;
import seedu.address.model.iteration.ImagePath;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.IterationDescription;

/**
 * A utility class to help with building Iteration objects.
 */
public class IterationBuilder {
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2022, 10, 13);
    public static final String DEFAULT_DESCRIPTION = "Draft sketch without colour";
    public static final String DEFAULT_FEEDBACK = "Looks good, but I want the lines to be thicker";
    public static final String DEFAULT_IMAGE_PATH = "/images/placeholderart.png";

    private Date date;
    private IterationDescription description;
    private Feedback feedback;
    private ImagePath imagePath;

    /**
     * Creates a {@code IterationBuilder} with the default details.
     */
    public IterationBuilder() {
        date = new Date(DEFAULT_DATE);
        description = new IterationDescription(DEFAULT_DESCRIPTION);
        feedback = new Feedback(DEFAULT_FEEDBACK);
        imagePath = new ImagePath(DEFAULT_IMAGE_PATH);
    }

    /**
     * Initialises the IterationBuilder with the data of {@code iterationToCopy}.
     */
    public IterationBuilder(Iteration iterationToCopy) {
        date = iterationToCopy.getDate();
        description = iterationToCopy.getDescription();
        feedback = iterationToCopy.getFeedback();
        imagePath = iterationToCopy.getImagePath();
    }

    /**
     * Sets the {@code Date} of the {@code Iteration} that we are building.
     */
    public IterationBuilder withDate(LocalDate date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Iteration} that we are building.
     */
    public IterationBuilder withDescription(String description) {
        this.description = new IterationDescription(description);
        return this;
    }

    /**
     * Sets the {@code Feedback} of the {@code Iteration} that we are building.
     */
    public IterationBuilder withFeedback(String feedback) {
        this.feedback = new Feedback(feedback);
        return this;
    }

    /**
     * Sets the {@code ImagePath} of the {@code Iteration} that we are building.
     */
    public IterationBuilder withImagePath(String imagePath) {
        this.imagePath = new ImagePath(imagePath);
        return this;
    }

    /**
     * Builds an Iteration with the given details.
     */
    public Iteration build() {
        return new Iteration(date, description, imagePath, feedback);
    }
}
