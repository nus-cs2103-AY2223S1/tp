package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attribute.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.DisplayItemUtil.INVALID_NAME_RACHEL;
import static seedu.address.testutil.DisplayItemUtil.INVALID_SAVE_ATTRIBUTE;
import static seedu.address.testutil.DisplayItemUtil.INVALID_TAG_SPECIAL;
import static seedu.address.testutil.TypicalTasks.FIX_BUG;

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
    private static final String INVALID_UID = "34198fje";
    private static final String INVALID_TAG = INVALID_TAG_SPECIAL;
    private static final Map<String, Object> INVALID_ATTRIBUTE = INVALID_SAVE_ATTRIBUTE;

    @Test
    public void toModelType_validTaskDetails_returnsTag() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(FIX_BUG);
        assertEquals(FIX_BUG, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_DATETIME,
                INVALID_NAME, VALID_UID, VALID_TAGS, VALID_ATTRIBUTES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
