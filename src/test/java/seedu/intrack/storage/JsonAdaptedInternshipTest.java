package seedu.intrack.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intrack.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalInternships.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Phone;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.internship.Website;

public class JsonAdaptedInternshipTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_POSITION = " ";
    private static final String INVALID_STATUS = " ";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_WEBSITE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TASK = "hahahaha";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_POSITION = BENSON.getPosition().toString();
    private static final String VALID_STATUS = BENSON.getStatus().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_WEBSITE = BENSON.getWebsite().toString();
    private static final List<JsonAdaptedTask> VALID_TASKS = BENSON.getTasks().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_REMARK = BENSON.getRemark().toString();

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(BENSON);
        assertEquals(BENSON, internship.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(INVALID_NAME, VALID_POSITION, VALID_STATUS, VALID_PHONE, VALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(null, VALID_POSITION, VALID_STATUS, VALID_PHONE, VALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidPosition_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, INVALID_POSITION, VALID_STATUS, VALID_PHONE, VALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = Position.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullPosition_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, null, VALID_STATUS, VALID_PHONE, VALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, INVALID_STATUS, VALID_PHONE, VALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, null, VALID_PHONE, VALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, VALID_STATUS, INVALID_PHONE, VALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, VALID_STATUS, null, VALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, VALID_STATUS, VALID_PHONE, INVALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, VALID_STATUS, VALID_PHONE, null,
                        VALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidWebsite_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, VALID_STATUS, VALID_PHONE, VALID_EMAIL,
                        INVALID_WEBSITE, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = Website.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullWebsite_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, VALID_STATUS, VALID_PHONE, VALID_EMAIL,
                        null, VALID_TASKS, VALID_TAGS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Website.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidTasks_throwsIllegalValueException() {
        List<JsonAdaptedTask> invalidTasks = new ArrayList<>(VALID_TASKS);
        invalidTasks.add(new JsonAdaptedTask(INVALID_TASK, ""));
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, VALID_STATUS, VALID_PHONE, VALID_EMAIL,
                        VALID_WEBSITE, invalidTasks, VALID_TAGS, VALID_REMARK);
        assertThrows(IllegalValueException.class, internship::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_POSITION, VALID_STATUS, VALID_PHONE, VALID_EMAIL,
                        VALID_WEBSITE, VALID_TASKS, invalidTags, VALID_REMARK);
        assertThrows(IllegalValueException.class, internship::toModelType);
    }
}
