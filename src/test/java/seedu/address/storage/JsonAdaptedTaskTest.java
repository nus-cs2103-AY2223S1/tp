package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.storage.JsonAdaptedTask.MISSING_TASK_DESCRIPTION;
import static seedu.address.storage.JsonAdaptedTask.MISSING_TASK_MODULE;
import static seedu.address.storage.JsonAdaptedTask.MISSING_TASK_STATUS;
import static seedu.address.storage.JsonAdaptedTask.WRONG_EXAM_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_D;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskStatus;
import seedu.address.testutil.TaskBuilder;

public class JsonAdaptedTaskTest {

    private static final String INVALID_MODULE = "12345";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_STATUS = "incom";
    private static final String INVALID_PRIORITY_TAG = "highlow";
    private static final String INVALID_DEADLINE_TAG = "29 dec 2022";
    private static final String INVALID_EXAM_DATE = "29 dec 2022";
    private static final String INVALID_EXAM_DESCRIPTION = "";

    private static final String VALID_MODULE = TASK_D.getModule().toString();
    private static final String VALID_DESCRIPTION = TASK_D.getDescription().toString();
    private static final String VALID_STATUS = TASK_D.getStatus().toString();
    private static final String VALID_PRIORITY_TAG = TASK_D.getPriorityTag().toString();
    private static final String VALID_DEADLINE_TAG = TASK_D.getDeadlineTag().toString();
    private static final String VALID_EXAM_DATE = TASK_D.getExam().getExamDate().toString();
    private static final String VALID_EXAM_DESCRIPTION = TASK_D.getExam().getDescription().toString();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(TASK_D);
        assertEquals(TASK_D, task.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_DESCRIPTION, INVALID_MODULE, VALID_STATUS,
            VALID_PRIORITY_TAG, VALID_DEADLINE_TAG, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        String expectedMessage = TaskDescription.DESCRIPTION_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_MODULE, VALID_STATUS, VALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        String expectedMessage = MISSING_TASK_DESCRIPTION;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, INVALID_MODULE, VALID_STATUS, VALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        String expectedMessage = ModuleCode.MODULE_CODE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, null, VALID_STATUS, VALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        String expectedMessage = MISSING_TASK_MODULE;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, INVALID_STATUS, VALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        String expectedMessage = TaskStatus.STATUS_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, null, VALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        String expectedMessage = MISSING_TASK_STATUS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidPriorityTag_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, VALID_STATUS, INVALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        String expectedMessage = PriorityTag.PRIORITY_TAG_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullPriorityTag_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, VALID_STATUS, null,
            VALID_DEADLINE_TAG, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        Task expectedTask = new TaskBuilder(TASK_D).withPriorityTag(null).build();
        assertTrue(expectedTask.hasAllSameFields(task.toModelType()));
    }

    @Test
    public void toModelType_invalidDeadlineTag_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, VALID_STATUS, VALID_PRIORITY_TAG,
            INVALID_DEADLINE_TAG, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        String expectedMessage = DeadlineTag.DEADLINE_TAG_FORMAT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadlineTag_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, VALID_STATUS,
            VALID_PRIORITY_TAG, null, VALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        Task expectedTask = new TaskBuilder(TASK_D).withDeadlineTag(null).build();
        assertTrue(expectedTask.hasAllSameFields(task.toModelType()));
    }

    @Test
    public void toModelType_invalidExamDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, VALID_STATUS, VALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, INVALID_EXAM_DATE, VALID_EXAM_DESCRIPTION);
        String expectedMessage = ExamDate.DATE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullExamDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, VALID_STATUS, VALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, null, VALID_EXAM_DESCRIPTION);
        String expectedMessage = WRONG_EXAM_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidExamDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, VALID_STATUS, VALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, VALID_EXAM_DATE, INVALID_EXAM_DESCRIPTION);
        String expectedMessage = ExamDescription.DESCRIPTION_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullExamDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_MODULE, VALID_STATUS, VALID_PRIORITY_TAG,
            VALID_DEADLINE_TAG, VALID_EXAM_DATE, null);
        String expectedMessage = WRONG_EXAM_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
