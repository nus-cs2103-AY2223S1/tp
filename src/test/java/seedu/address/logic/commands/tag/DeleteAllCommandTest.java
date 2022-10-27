package seedu.address.logic.commands.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.NEW_LINE_CHARACTER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.DeleteContactCommand;
import seedu.address.logic.commands.task.DeleteTaskCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteAllCommand}.
 */
public class DeleteAllCommandTest {

    private Model model = new ModelManager();
    private Tag tagToDelete2101 = new Tag(VALID_TAG_2101);
    private Tag tagToDelete2103 = new Tag(VALID_TAG_2103);
    private Set<Tag> tagsToDelete = new HashSet<>();

    @Test
    public void execute_oneTagRemoveOneTag_emptyModel() {
        Person personToDelete = new PersonBuilder().withTags(VALID_TAG_2103).build();
        Task taskToDelete = new TaskBuilder().withTags(VALID_TAG_2103).build();
        tagsToDelete.add(tagToDelete2103);
        model.addPerson(personToDelete);
        model.addTask(taskToDelete);

        model.addTag(tagToDelete2103);
        model.addTagCount(tagToDelete2103);

        DeleteAllCommand deleteAllCommand = new DeleteAllCommand(tagsToDelete);

        String expectedMessage =
                String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete2103)
                + NEW_LINE_CHARACTER
                + String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete)
                + NEW_LINE_CHARACTER
                + String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete)
                + NEW_LINE_CHARACTER;

        ModelManager expectedModel = new ModelManager();

        assertCommandSuccess(deleteAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleTagsRemoveOneTag_taskAndPersonWithOneTag() {
        Person personToDelete = new PersonBuilder().withTags(VALID_TAG_2103, VALID_TAG_2101).build();
        Person expectedPerson = new PersonBuilder().withTags(VALID_TAG_2103).build();
        Task expectedTask = new TaskBuilder().withTags(VALID_TAG_2103).build();
        tagsToDelete.add(tagToDelete2101);
        model.addPerson(personToDelete);
        model.addTask(expectedTask);

        model.addTag(tagToDelete2103);
        model.addTagCount(tagToDelete2103);
        model.addTag(tagToDelete2101);

        DeleteAllCommand deleteAllCommand = new DeleteAllCommand(tagsToDelete);

        String expectedMessage =
                String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete2101)
                + NEW_LINE_CHARACTER;

        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        expectedModel.addTask(expectedTask);

        expectedModel.addTag(tagToDelete2103);
        expectedModel.addTagCount(tagToDelete2103);

        assertCommandSuccess(deleteAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeTagThatDoNotExist_throwsCommandException() {
        Task taskToDelete = new TaskBuilder().withTags(VALID_TAG_2103).build();
        tagsToDelete.add(tagToDelete2101);
        model.addTask(taskToDelete);

        model.addTag(tagToDelete2103);

        DeleteAllCommand deleteAllCommand = new DeleteAllCommand(tagsToDelete);

        String expectedMessage = DeleteAllCommand.MESSAGE_TAGS_DO_NOT_EXIST;

        assertCommandFailure(deleteAllCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Set<Tag> tagsToDeleteTwo = new HashSet<>();
        tagsToDelete.add(tagToDelete2101);
        tagsToDeleteTwo.add(tagToDelete2103);

        DeleteAllCommand deleteFirstCommand = new DeleteAllCommand(tagsToDelete);
        DeleteAllCommand deleteSecondCommand = new DeleteAllCommand(tagsToDeleteTwo);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAllCommand deleteFirstCommandCopy = new DeleteAllCommand(tagsToDelete);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
