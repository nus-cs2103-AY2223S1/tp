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

public class JsonAdaptedTodoTest {
    private static final String VALID_NAME = TypicalTaskBook.BENSON.getName().toString();
    private static final String INVALID_NAME = "R@chel";

    private static final String VALID_ASSIGNMENT = TypicalTaskBook.SLEEPING.getAssignment().name();
    private static final String VALID_DESCRIPTION = TypicalTaskBook.SLEEPING.getDescription().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalTaskBook.SLEEPING.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final String INVALID_ASSIGNMENT = "WITH";
    private static final String INVALID_DESCRIPTION = "Pay €5";
    private static final String INVALID_TAG = "#SLEEP";

    private static final String EMPTY_FIELD = "";

    @Test
    public void toModelType_validTodo_returnsTask() throws Exception {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTodo(TypicalTaskBook.SLEEPING);
        Assertions.assertEquals(TypicalTaskBook.SLEEPING, task.toModelType());
    }

    @Test
    public void toModelType_validTodoDetails_returnsTask() throws Exception {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTodo(VALID_NAME, VALID_ASSIGNMENT,
                VALID_DESCRIPTION, false, VALID_TAGS);
        Assertions.assertEquals(TypicalTaskBook.SLEEPING, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTodo(INVALID_NAME, VALID_ASSIGNMENT,
                VALID_DESCRIPTION, false, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTodo(null, VALID_ASSIGNMENT,
                VALID_DESCRIPTION, false, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT, "Name");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidAssignment_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTodo(VALID_NAME, INVALID_ASSIGNMENT,
                VALID_DESCRIPTION, false, VALID_TAGS);
        String expectedMessage = Assignment.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullAssignment_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTodo(VALID_NAME, null,
                VALID_DESCRIPTION, false, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT, "Assignment");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTodo(VALID_NAME, VALID_ASSIGNMENT,
                INVALID_DESCRIPTION, false, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTodo(VALID_NAME, VALID_ASSIGNMENT,
                null, false, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT, "Description");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task = new JsonAdaptedTodo(VALID_NAME, VALID_ASSIGNMENT,
                null, false, invalidTags);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
