package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand.RemovePersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.RemovePersonDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for Remove Command.
 */
public class RemoveCommandTest {

    private static final Index INDEX_FIRST_ITEM = Index.fromOneBased(1);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    public void setFullView() {
        model.setFullView();
    }

    public void setDayView() {
        model.setDayView();
    }

    public void setListView() {
        model.setListView();
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        setFullView();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RemovePersonDescriptor descriptor = new RemovePersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        RemoveCommand removeCommand = new RemoveCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(removeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        setFullView();
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        RemoveCommand removeCommand = new RemoveCommand(outOfBoundIndex,
                new RemovePersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(removeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_inListView_failure() {
        setListView();
        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_ITEM, new RemovePersonDescriptor());

        assertCommandFailure(removeCommand, model, RemoveCommand.MESSAGE_NOT_VIEW_MODE);
    }

    @Test
    public void execute_inDayView_failure() {
        setDayView();
        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_PERSON, new RemovePersonDescriptor());

        assertCommandFailure(removeCommand, model, RemoveCommand.MESSAGE_NOT_VIEW_MODE);
    }
}
