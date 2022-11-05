package taskbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taskbook.commons.exceptions.IllegalValueException;
import taskbook.model.TaskBook;
import taskbook.model.person.Name;
import taskbook.model.tag.Tag;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;
import taskbook.testutil.Assert;
import taskbook.testutil.TypicalTaskBook;

public class JsonAdaptedEventTest {
    private static final String VALID_NAME = TypicalTaskBook.CARL.getName().toString();
    private static final String INVALID_NAME = "R@chel";

    private static final String VALID_ASSIGNMENT = TypicalTaskBook.PARTYING.getAssignment().name();
    private static final String VALID_DESCRIPTION = TypicalTaskBook.PARTYING.getDescription().toString();
    private static final String VALID_EVENT = TypicalTaskBook.PARTYING.getDate().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalTaskBook.PARTYING.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final String INVALID_ASSIGNMENT = "WITH";
    private static final String INVALID_DESCRIPTION = "Pay €5";
    private static final String INVALID_Event = "2022-02-45";
    private static final String INVALID_TAG = "#SLEEP";

    private static final String EMPTY_FIELD = "";

    @Test
    public void toModelType_validEvent_returnsTask() throws Exception {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(TypicalTaskBook.PARTYING);
        Assertions.assertEquals(TypicalTaskBook.PARTYING, task.toModelType());
    }

    @Test
    public void toModelType_validEventDetails_returnsTask() throws Exception {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(VALID_NAME, VALID_ASSIGNMENT, VALID_DESCRIPTION, true, VALID_EVENT, VALID_TAGS);
        Assertions.assertEquals(TypicalTaskBook.PARTYING, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(INVALID_NAME, VALID_ASSIGNMENT, VALID_DESCRIPTION, true, VALID_EVENT, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(null, VALID_ASSIGNMENT, VALID_DESCRIPTION, true, VALID_EVENT, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT, "Name");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidAssignment_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(VALID_NAME, INVALID_ASSIGNMENT, VALID_DESCRIPTION, true, VALID_EVENT, VALID_TAGS);
        String expectedMessage = Assignment.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullAssignment_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(VALID_NAME, null, VALID_DESCRIPTION, true, VALID_EVENT, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT, "Assignment");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(VALID_NAME, VALID_ASSIGNMENT, INVALID_DESCRIPTION, true, VALID_EVENT, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(VALID_NAME, VALID_ASSIGNMENT, null, true, VALID_EVENT, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT, "Description");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidEventDate_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(VALID_NAME, VALID_ASSIGNMENT, VALID_DESCRIPTION, true, INVALID_Event, VALID_TAGS);
        String expectedMessage = "Event Date should be in supported date formats.";
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullEventDate_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        JsonAdaptedTask task = new JsonAdaptedEvent(VALID_NAME, VALID_ASSIGNMENT, VALID_EVENT, true, null, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT, "Event Date");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.CARL);
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task = new JsonAdaptedEvent(VALID_NAME, VALID_ASSIGNMENT, null, true, VALID_EVENT, invalidTags);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
