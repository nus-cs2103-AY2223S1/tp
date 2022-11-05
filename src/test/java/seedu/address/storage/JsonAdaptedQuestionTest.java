package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalQuestions.Q1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.question.Description;


public class JsonAdaptedQuestionTest {

    private static final String VALID_IMPORTANT_TAG = Q1.getImportantTag().toString();
    private static final String INVALID_DESCRIPTION_EMPTY_STRING = "";
    private static final String INVALID_DESCRIPTION_SPACE_ONLY = " ";

    @Test
    public void toModelType_validQuestion_returnQuestion() throws Exception {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(Q1);
        assertEquals(Q1, question.toModelType());
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(null, VALID_IMPORTANT_TAG);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(INVALID_DESCRIPTION_SPACE_ONLY, VALID_IMPORTANT_TAG);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, question::toModelType);

        JsonAdaptedQuestion question2 = new JsonAdaptedQuestion(INVALID_DESCRIPTION_EMPTY_STRING, VALID_IMPORTANT_TAG);
        assertThrows(IllegalArgumentException.class, expectedMessage, question2::toModelType);
    }

}
