package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.STUDY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;




public class JsonAdaptedTaskTest {
    private static final String DATE_FORMAT = "dd-MM-uuuu";
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);

    private static final String INVALID_NAME = "";
    private static final String INVALID_DEADLINE_ABOVE_LIMIT = "12-12-2101";
    private static final String INVALID_DEADLINE_BELOW_LIMIT = "12-12-1899";

    private static final String VALID_NAME = STUDY.getName().toString();
    private static final String VALID_DEADLINE_STRING = "30-06-2001";
    private static final LocalDate VALID_DEADLINE =
            LocalDate.parse(VALID_DEADLINE_STRING, DATE_TIME_FORMAT);
    private static final boolean VALID_ISDONE = false;

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(STUDY);
        assertEquals(STUDY, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_NAME, VALID_DEADLINE, VALID_ISDONE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DEADLINE, VALID_ISDONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadlineYearAboveLimit_throwsIllegalValueException() {
        LocalDate invalidDeadline = LocalDate.parse(INVALID_DEADLINE_ABOVE_LIMIT, DATE_TIME_FORMAT);
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, invalidDeadline, VALID_ISDONE);
        String expectedMessage = Task.MESSAGE_INVALID_DATE_VALUE;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadlineYearBelowLimit_throwsIllegalValueException() {
        LocalDate invalidDeadline = LocalDate.parse(INVALID_DEADLINE_BELOW_LIMIT, DATE_TIME_FORMAT);
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, invalidDeadline, VALID_ISDONE);
        String expectedMessage = Task.MESSAGE_INVALID_DATE_VALUE;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

}
