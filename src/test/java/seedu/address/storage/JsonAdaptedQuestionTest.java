package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.question.Description;
import static seedu.address.testutil.TypicalQuestions.Q1;

import static seedu.address.testutil.Assert.assertThrows;

public class JsonAdaptedQuestionTest {

    private static final String VALID_IMPORTANT_TAG = Q1.getImportantTag().toString();

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion(null, VALID_IMPORTANT_TAG);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

}
