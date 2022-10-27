package seedu.address.model.question;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_Q2;
import static seedu.address.testutil.TypicalQuestions.Q1;
import static seedu.address.testutil.TypicalQuestions.Q2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.QuestionBuilder;

public class QuestionTest {

    @Test
    public void isSameQuestion() {
        // same object -> returns true
        assertTrue(Q1.isSameQuestion(Q1));

        // null -> returns false
        assertFalse(Q1.isSameQuestion(null));

        // same description, important tags different -> returns true
        Question editedQ1 = new QuestionBuilder(Q1).withImportantTag(true).build();
        assertTrue(Q1.isSameQuestion(editedQ1));

        // different description, important tags same -> returns false
        editedQ1 = new QuestionBuilder(Q1).withDescription(VALID_DESCRIPTION_Q2).build();
        assertFalse(Q1.isSameQuestion(editedQ1));

        // description differs in case, important tags same -> returns false
        Question editedQ2 = new QuestionBuilder(Q2).withDescription(VALID_DESCRIPTION_Q2.toLowerCase()).build();
        assertFalse(Q2.isSameQuestion(editedQ2));

        // description has trailing spaces, important tags same -> returns false
        String descriptionWithTrailingSpaces = VALID_DESCRIPTION_Q2 + " ";
        editedQ2 = new QuestionBuilder(Q2).withDescription(descriptionWithTrailingSpaces).build();
        assertFalse(Q2.isSameQuestion(editedQ2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Question q1Copy = new QuestionBuilder(Q1).build();
        assertTrue(Q1.equals(q1Copy));

        // same object -> returns true
        assertTrue(Q1.equals(Q1));

        // null -> returns false
        assertFalse(Q1.equals(null));

        // different type -> returns false
        assertFalse(Q1.equals(5));

        // different description -> returns false
        assertFalse(Q1.equals(Q2));

        // different description after edit -> returns false
        Question editedQ1 = new QuestionBuilder(Q1).withDescription(VALID_DESCRIPTION_Q2).build();
        assertFalse(Q1.equals(editedQ1));

        // different important tags -> returns true
        editedQ1 = new QuestionBuilder(Q1).withImportantTag(true).build();
        assertTrue(Q1.equals(editedQ1));
    }
}
