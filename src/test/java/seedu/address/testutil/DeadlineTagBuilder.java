package seedu.address.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.tag.DeadlineTag;

/**
 * DeadlineTagBuilder builds a {@code DeadlineTag}.
 */
public class DeadlineTagBuilder {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final LocalDate DEFAULT_DATE = LocalDate.parse("31-12-2022", dtf);
    private LocalDate deadline;


    /**
     * Initialises the DeadlineTagBuilder with the default date.
     */
    public DeadlineTagBuilder() {
        deadline = DEFAULT_DATE;
    }

    /**
     * Initialises the deadline of the DeadlineTagBuilder with the deadline tag given.
     *
     * @param deadlineTag The deadline tag given.
     */
    public DeadlineTagBuilder(DeadlineTag deadlineTag) {
        deadline = deadlineTag.deadline;
    }

    /**
     * Sets the deadline in DeadlineTagBuilder with the given deadline.
     *
     * @param deadline The deadline provided.
     * @return DeadlineTagBuilder containing the new deadline.
     */
    public DeadlineTagBuilder withDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }

    public DeadlineTag build() {
        return new DeadlineTag(deadline);
    }

}
