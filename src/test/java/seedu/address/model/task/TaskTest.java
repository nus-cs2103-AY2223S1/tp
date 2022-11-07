package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DO_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_B;
import static seedu.address.testutil.TypicalTasks.TASK_D;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.exceptions.DeadlineTagAlreadyExistsException;
import seedu.address.model.tag.exceptions.DeadlineTagDoesNotExistException;
import seedu.address.model.tag.exceptions.DeadlineTagUnchangedException;
import seedu.address.model.tag.exceptions.PriorityTagAlreadyExistsException;
import seedu.address.model.tag.exceptions.PriorityTagDoesNotExistException;
import seedu.address.model.tag.exceptions.PriorityTagUnchangedException;
import seedu.address.model.task.exceptions.TaskAlreadyMarkedException;
import seedu.address.model.task.exceptions.TaskAlreadyUnmarkedException;
import seedu.address.testutil.DeadlineTagBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.PriorityTagBuilder;
import seedu.address.testutil.TaskBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TaskTest {

    @Test
    public void mark_success() {
        Task expectedTask = new TaskBuilder(TASK_A).withStatus("complete").build();
        assertTrue(TASK_A.mark().hasAllSameFields(expectedTask));
    }

    @Test
    public void mark_markedTask_throwsTaskAlreadyMarkedException() {
        assertThrows(TaskAlreadyMarkedException.class, () -> TASK_D.mark());
    }

    @Test
    public void unmark_success() {
        Task expectedTask = new TaskBuilder(TASK_D).withStatus("incomplete").build();
        assertTrue(TASK_D.unmark().hasAllSameFields(expectedTask));
    }

    @Test
    public void unmark_unmarkedTask_throwsTaskAlreadyUnmarkedException() {
        assertThrows(TaskAlreadyUnmarkedException.class, () -> TASK_A.unmark());
    }

    @Test
    public void isSameTask_nullTask_returnsFalse() {
        Task task = new TaskBuilder().build();
        assertFalse(task.isSameTask(null));
    }

    @Test
    public void setPriorityTag_taskAlreadyHasPriorityTag_throwPriorityTagAlreadyExistsException() {
        Task task = new TaskBuilder().withPriorityTag(new PriorityTagBuilder()
                .build()).build();
        PriorityTag priorityTag = new PriorityTagBuilder().withStatus("LOW").build();
        assertThrows(PriorityTagAlreadyExistsException.class, () -> task.setPriorityTag(priorityTag));
    }

    @Test
    public void replacePriorityTag_taskPriorityTagIsUnchanged_throwsPriorityTagUnchangedException() {
        Task task = new TaskBuilder().withPriorityTag(new PriorityTagBuilder()
                .build()).build();
        PriorityTag priorityTag = new PriorityTagBuilder().build();
        assertThrows(PriorityTagUnchangedException.class, () -> task.replacePriorityTag(priorityTag));
    }

    @Test
    public void deletePriorityTag_taskPriorityTagNotPresent_throwsPriorityTagDoesNotExistException() {
        Task task = new TaskBuilder().withPriorityTag(null).build();
        assertThrows(PriorityTagDoesNotExistException.class, task::deletePriorityTag);
    }

    @Test
    public void setDeadlineTag_taskAlreadyHasDeadlineTag_throwDeadlineTagAlreadyExistsException() {
        Task task = new TaskBuilder().withDeadlineTag(new DeadlineTagBuilder()
                .build()).build();
        LocalDate date = LocalDate.parse("25-12-2023",  DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        DeadlineTag deadlineTag = new DeadlineTagBuilder().withDeadline(date).build();
        assertThrows(DeadlineTagAlreadyExistsException.class, () -> task.setDeadlineTag(deadlineTag));
    }

    @Test
    public void replaceDeadlineTag_taskDeadlineTagIsUnchanged_throwsDeadlineTagUnchangedException() {
        Task task = new TaskBuilder().withDeadlineTag(new DeadlineTagBuilder()
                .build()).build();
        DeadlineTag deadlineTag = new DeadlineTagBuilder().build();
        assertThrows(DeadlineTagUnchangedException.class, () -> task.replaceDeadlineTag(deadlineTag));
    }

    @Test
    public void deleteDeadlineTag_taskDeadlineTagNotPresent_throwsDeadlineTagDoesNotExistException() {
        Task task = new TaskBuilder().withDeadlineTag(null).build();
        assertThrows(DeadlineTagDoesNotExistException.class, task::deleteDeadlineTag);
    }

    @Test
    public void edit_withEditTaskDescriptor() {

        // null descriptor
        assertThrows(NullPointerException.class, () -> TASK_A.edit(null));

        // different module
        EditTaskCommand.EditTaskDescriptor descriptorModule = new EditTaskDescriptorBuilder()
            .withModule(VALID_MODULE_CS2030).build();
        Task taskDModuleEdited = new TaskBuilder(TASK_D).withModule(VALID_MODULE_CS2030).withExam(null).build();
        assertTrue(TASK_D.edit(descriptorModule).hasAllSameFields(taskDModuleEdited));

        // different description
        EditTaskCommand.EditTaskDescriptor descriptorDescription = new EditTaskDescriptorBuilder()
            .withDescription(VALID_DESCRIPTION_DO_TUTORIAL).build();
        Task taskADescriptionEdited = new TaskBuilder(TASK_A)
            .withTaskDescription(VALID_DESCRIPTION_DO_TUTORIAL).build();
        assertTrue(TASK_A.edit(descriptorDescription).hasAllSameFields(taskADescriptionEdited));
    }

    @Test
    public void edit_withModuleAndTaskDescriptionWithoutUnlinkingExam() {
        // null module and description
        assertThrows(NullPointerException.class, () -> TASK_A
                .editWithoutUnlinkingExam(null, null));

        // module edited
        Module module = new Module(new ModuleCode(VALID_MODULE_CS2030));
        Task taskDModuleEdited = new TaskBuilder(TASK_D).withModule(VALID_MODULE_CS2030).build();
        assertTrue(TASK_D.editWithoutUnlinkingExam(module, null).hasAllSameFields(taskDModuleEdited));

        // description edited
        TaskDescription description = new TaskDescription(VALID_DESCRIPTION_DO_TUTORIAL);
        Task taskADescriptionEdited = new TaskBuilder(TASK_A)
            .withTaskDescription(VALID_DESCRIPTION_DO_TUTORIAL).build();
        assertTrue(TASK_A.editWithoutUnlinkingExam(null, description)
                .hasAllSameFields(taskADescriptionEdited));
    }

    @Test
    public void isEqual() {
        Task taskA = TASK_A;
        Task taskB = TASK_B;
        Task taskD = TASK_D;

        // same object -> returns true
        assertTrue(taskA.equals(taskA));

        // same module and description -> return true
        Task taskACopy = new TaskBuilder(TASK_A).build();
        assertTrue(taskA.equals(taskACopy));

        // same module and description but with other different fields -> return false
        Task taskDCopy = new TaskBuilder(TASK_D).withStatus("incomplete").withExam(null).build();
        assertFalse(taskD.equals(taskDCopy));

        // different types -> returns false
        assertFalse(taskA.equals(5));

        // null -> returns false
        assertFalse(taskA.equals(null));

        // different description -> returns false
        assertFalse(taskA.equals(taskB));

        // different module -> returns false
        Task taskAWithDifferentModule = new TaskBuilder(TASK_A).withModule("cs2040").build();
        assertFalse(taskA.equals(taskAWithDifferentModule));
    }

}
