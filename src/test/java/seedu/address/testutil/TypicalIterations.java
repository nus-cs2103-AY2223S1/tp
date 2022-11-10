package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_FIRST_DAY_OF_YEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LAST_DAY_OF_YEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDBACK_AMAZING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDBACK_HORRIBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDBACK_UGLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_DESCRIPTION_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_DESCRIPTION_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_DESCRIPTION_REMOVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_IMAGEPATH_COLOR;

import java.time.LocalDate;

import seedu.address.model.iteration.Iteration;

/**
 * A utility class containing a list of {@code Iteration} objects to be used in tests.
 */
public class TypicalIterations {

    public static final Iteration FINALISED = new IterationBuilder()
            .withDate(LocalDate.parse(VALID_DATE_FIRST_DAY_OF_YEAR))
            .withDescription(VALID_ITERATION_DESCRIPTION_FINALISE)
            .withFeedback(VALID_FEEDBACK_HORRIBLE).build();
    public static final Iteration ADD_COLOR = new IterationBuilder()
            .withDate(LocalDate.parse(VALID_DATE_LAST_DAY_OF_YEAR))
            .withDescription(VALID_ITERATION_DESCRIPTION_COLOR)
            .withFeedback(VALID_FEEDBACK_UGLY)
            .withImagePath(VALID_ITERATION_IMAGEPATH_COLOR).build();
    public static final Iteration REMOVE_CHARACTER = new IterationBuilder()
            .withDescription(VALID_ITERATION_DESCRIPTION_REMOVE)
            .withFeedback(VALID_FEEDBACK_AMAZING).build();

    private TypicalIterations() {} // prevents instantiation
}
