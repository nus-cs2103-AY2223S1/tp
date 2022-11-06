package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.DisplayItemSampleData.INVALID_NAME_RACHEL;
import static seedu.address.testutil.TypicalGroups.TEAM_ALPHA;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attribute.Name;

public class JsonAdaptedGroupTest {

    private static final String VALID_UID = TEAM_ALPHA.getUid().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TEAM_ALPHA.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());;
    private static final List<JsonAdaptedAbstractAttribute> VALID_ATTRIBUTES = TEAM_ALPHA
            .getAttributes().stream().map(JsonAdaptedAbstractAttribute::new).collect(Collectors.toList());

    private static final String INVALID_NAME = INVALID_NAME_RACHEL;

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        JsonAdaptedGroup group = new JsonAdaptedGroup(TEAM_ALPHA);
        assertEquals(TEAM_ALPHA, group.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedGroup group = new JsonAdaptedGroup(INVALID_NAME, VALID_UID, VALID_TAGS, VALID_ATTRIBUTES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedGroup group = new JsonAdaptedGroup(null, VALID_UID, VALID_TAGS, VALID_ATTRIBUTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }
}
