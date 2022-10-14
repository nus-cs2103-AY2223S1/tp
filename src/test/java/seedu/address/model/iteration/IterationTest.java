package seedu.address.model.iteration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDBACK_AMAZING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_DESCRIPTION_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_DESCRIPTION_REMOVE;
import static seedu.address.testutil.TypicalCustomers.AMY;
import static seedu.address.testutil.TypicalIterations.ADD_COLOR;
import static seedu.address.testutil.TypicalIterations.FINALISED;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.IterationBuilder;

public class IterationTest {

    @Test
    public void isSameIteration() {
        // same object -> returns true
        assertTrue(FINALISED.isSameIteration(FINALISED));

        // null -> returns false
        assertFalse(FINALISED.isSameIteration(null));

        // same description -> returns true
        Iteration iterationSameDescription = new IterationBuilder()
                .withDescription(VALID_ITERATION_DESCRIPTION_FINALISE).build();
        assertTrue(FINALISED.isSameIteration(iterationSameDescription));

        // different description -> returns false
        assertFalse(FINALISED.isSameIteration(ADD_COLOR));
    }

    @Test
    public void testHashCode() {
        // same object -> returns true
        assertEquals(FINALISED.hashCode(), FINALISED.hashCode());

        // all fields same -> returns true
        Iteration copiedIteration = new IterationBuilder(FINALISED).build();
        assertEquals(FINALISED.hashCode(), copiedIteration.hashCode());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(FINALISED.equals(FINALISED));

        // different type -> returns false
        assertFalse(FINALISED.equals(AMY));

        // null -> returns false
        assertFalse(FINALISED.equals(null));

        // same values (copy) -> returns true
        Iteration copiedIteration = new IterationBuilder(FINALISED).build();
        assertTrue(FINALISED.equals(copiedIteration));

        // different date -> returns false
        Iteration differentDateIteration = new IterationBuilder(FINALISED)
                .withDate(LocalDate.of(2020, 1, 1)).build();
        assertFalse(FINALISED.equals(differentDateIteration));

        // different description -> returns false
        Iteration differentDescriptionIteration = new IterationBuilder(FINALISED)
                .withDescription(VALID_ITERATION_DESCRIPTION_REMOVE).build();
        assertFalse(FINALISED.equals(differentDescriptionIteration));

        // different feedback -> returns false
        Iteration differentFeedbackIteration = new IterationBuilder(FINALISED)
                .withFeedback(VALID_FEEDBACK_AMAZING).build();
        assertFalse(FINALISED.equals(differentFeedbackIteration));

        // all fields different -> returns false
        assertFalse(FINALISED.equals(ADD_COLOR));
    }
}
