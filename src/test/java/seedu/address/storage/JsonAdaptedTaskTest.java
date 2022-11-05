package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.INVALID_FIELD_MESSAGE_FORMAT;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.DisplayItemUtil.INVALID_NAME_RACHEL;
import static seedu.address.testutil.TypicalTasks.FIX_BUG;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attribute.Name;

public class JsonAdaptedTaskTest {

    private static final String VALID_NAME = FIX_BUG.getName().fullName;
    private static final String VALID_DESCRIPTION = FIX_BUG.getDescription().getAttributeContent();
    private static final String VALID_DATETIME = FIX_BUG.getCompletedTime().toString();
    private static final String VALID_UID = FIX_BUG.getUid().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = FIX_BUG.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAbstractAttribute> VALID_ATTRIBUTES = FIX_BUG
            .getAttributes().stream().map(JsonAdaptedAbstractAttribute::new).collect(Collectors.toList());

    private static final String INVALID_NAME = INVALID_NAME_RACHEL;
    private static final String INVALID_DATETIME = "34871";

    @Test
    public void toModelType_validTaskDetails_returnsTag() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(FIX_BUG);
        assertEquals(FIX_BUG, task.toModelType());
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, INVALID_DATETIME,
                VALID_NAME, VALID_UID, VALID_TAGS, VALID_ATTRIBUTES);
        String expectedMessage = String.format(INVALID_FIELD_MESSAGE_FORMAT,
                LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, null,
                VALID_NAME, VALID_UID, VALID_TAGS, VALID_ATTRIBUTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_DATETIME,
                INVALID_NAME, VALID_UID, VALID_TAGS, VALID_ATTRIBUTES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_DATETIME,
                null, VALID_UID, VALID_TAGS, VALID_ATTRIBUTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
