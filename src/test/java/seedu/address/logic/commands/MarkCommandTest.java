package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_MARK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.setModelFullView;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code MarkCommand}.
 */
public class MarkCommandTest {

    private static final Index INDEX_FIRST_ITEM = Index.fromOneBased(1);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    public void setFullView(Index index) {
        model.setFullView();
        showPersonAtIndex(model, index);
    }

    public void setDayView() {
        model.setDayView();
    }

    public void setListView() {
        model.setListView();
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        setFullView(INDEX_FIRST_PERSON);
        MarkCommand markCommand = new MarkCommand(new MarkPersonDescriptor());
        Person markedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARKED_PERSON_SUCCESS, markedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        setModelFullView(expectedModel, markedPerson.getName().fullName);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_inListView_failure() {
        setListView();
        MarkCommand markCommand = new MarkCommand(new MarkPersonDescriptor());

        assertCommandFailure(markCommand, model, MarkCommand.MESSAGE_NOT_VIEW_MODE);
    }

    @Test
    public void execute_inDayView_failure() {
        setDayView();
        MarkCommand markCommand = new MarkCommand(new MarkPersonDescriptor());

        assertCommandFailure(markCommand, model, MarkCommand.MESSAGE_NOT_VIEW_MODE);
    }



    @Test
    public void equals() {
        setFullView(INDEX_FIRST_ITEM);
        final MarkCommand standardCommand = new MarkCommand(DESC_AMY_MARK);
        MarkPersonDescriptor copyDescriptor = new MarkPersonDescriptor(DESC_AMY_MARK);
        MarkCommand commandWithSameValues = new MarkCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

    }
}
