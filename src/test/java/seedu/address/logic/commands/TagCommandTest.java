package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.TAG_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.TAG_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TagCommand.EditPersonTags;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditPersonTagsBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class TagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Task taggedTask = new PersonBuilder(taskToTag).withTags(VALID_TAG_HIGH_PRIORITY).build();
        EditPersonTags editPersonTags = new EditPersonTagsBuilder(taggedTask).build();
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, editPersonTags);

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_PERSON_SUCCESS, taggedTask);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTask(taskToTag, taggedTask);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Task taggedTask = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, new EditPersonTagsBuilder().build());

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_PERSON_SUCCESS, taggedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTask(taggedTask, taggedTask);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Task taskInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Task taggedTask = new PersonBuilder(taskInFilteredList).withTags(VALID_TAG_HIGH_PRIORITY).build();
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON,
                new EditPersonTagsBuilder().withTags(VALID_TAG_HIGH_PRIORITY).build());

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_PERSON_SUCCESS, taggedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTask(model.getFilteredPersonList().get(0), taggedTask);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        TagCommand.EditPersonTags editPersonTags = new EditPersonTagsBuilder().build();
        TagCommand tagCommand = new TagCommand(outOfBoundIndex, editPersonTags);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        TagCommand tagCommand = new TagCommand(outOfBoundIndex,
                new EditPersonTagsBuilder().build());

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_PERSON, TAG_ALPHA);

        // same values -> returns true
        EditPersonTags copyTag = new EditPersonTags(TAG_ALPHA);
        TagCommand commandWithSameValues = new TagCommand(INDEX_FIRST_PERSON, copyTag);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_SECOND_PERSON, TAG_ALPHA)));

        // different tag -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_FIRST_PERSON, TAG_BETA)));
    }

}
