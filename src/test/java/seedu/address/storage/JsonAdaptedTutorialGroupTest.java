package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTutorialGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_1;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_2;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.TutorialGroup;



public class JsonAdaptedTutorialGroupTest {
    private static final String INVALID_NAME_A = "T!@";

    private static final String INVALID_NAME_B = "Tx3";

    private static final String INVALID_NAME_C = "t56";

    @Test
    public void toModelType_validTutorialDetails_returnsTutorial() throws Exception {
        JsonAdaptedTutorialGroup tutorialGroupA = new JsonAdaptedTutorialGroup(TUT_1);
        assertEquals(TUT_1, tutorialGroupA.toModelType());

        JsonAdaptedTutorialGroup tutorialGroupB = new JsonAdaptedTutorialGroup(TUT_2);
        assertEquals(TUT_2, tutorialGroupB.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalArgumentException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(INVALID_NAME_A);
        String expectedMessage = TutorialGroup.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, tutorialGroup::toModelType);

        tutorialGroup =
                new JsonAdaptedTutorialGroup(INVALID_NAME_B);
        expectedMessage = TutorialGroup.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, tutorialGroup::toModelType);

        tutorialGroup =
                new JsonAdaptedTutorialGroup(INVALID_NAME_C);
        expectedMessage = TutorialGroup.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup = new JsonAdaptedTutorialGroup((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name");
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

}
