package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.PREPARE_SLIDES;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Id;

public class JsonAdaptedTaskTest {
    private static final String INVALID_DESCRIPTION = "R@chel";
    private static final String INVALID_DEADLINE = "+651234";
    private static final String INVALID_ID = "-1";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_DESCRIPTION = PREPARE_SLIDES.getDescription().toString();
    private static final String VALID_DEADLINE = PREPARE_SLIDES.getDeadline().toString();
    private static final Boolean VALID_COMPLETION_STATUS = PREPARE_SLIDES.getCompletionStatus();
    private static final Boolean VALID_ARCHIVAL_STATUS = PREPARE_SLIDES.getArchivalStatus();
    private static final String VALID_ID = PREPARE_SLIDES.getId().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = PREPARE_SLIDES.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(PREPARE_SLIDES);
        assertEquals(PREPARE_SLIDES, task.toModelType());
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_DESCRIPTION, INVALID_DEADLINE, VALID_COMPLETION_STATUS, VALID_ARCHIVAL_STATUS,
                 VALID_TAGS, VALID_ID);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, null, VALID_COMPLETION_STATUS,
            VALID_ARCHIVAL_STATUS, VALID_TAGS, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_DESCRIPTION, VALID_DEADLINE, VALID_COMPLETION_STATUS, VALID_ARCHIVAL_STATUS,
                        VALID_TAGS, VALID_ID);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DEADLINE, VALID_COMPLETION_STATUS,
            VALID_ARCHIVAL_STATUS, VALID_TAGS, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullCompletionStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_DEADLINE, null,
                VALID_ARCHIVAL_STATUS, VALID_TAGS, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Boolean.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullArchivalStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_DEADLINE, VALID_COMPLETION_STATUS,
                null, VALID_TAGS, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Boolean.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_DEADLINE, VALID_COMPLETION_STATUS,
                VALID_ARCHIVAL_STATUS, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }



    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG, 0));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_DESCRIPTION, VALID_DEADLINE, VALID_COMPLETION_STATUS, VALID_ARCHIVAL_STATUS,
                invalidTags, VALID_ID);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

}
