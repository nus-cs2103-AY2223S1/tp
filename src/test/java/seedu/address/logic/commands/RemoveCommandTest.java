package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_REM;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_REM;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_LESSON_PLAN;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_NAME;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_PHONE;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_TAGS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.setModelFullView;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand.RemovePersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.RemovePersonDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for Remove Command.
 */
public class RemoveCommandTest {

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
    public void execute_allFieldsSpecifiedFilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(0);

        setFullView(INDEX_FIRST_PERSON);
        PersonBuilder blankPerson = new PersonBuilder();
        Person removedPerson = blankPerson.withName(FIRST_PERSON_NAME)
                .withPhone(FIRST_PERSON_PHONE).withLessonPlan(FIRST_PERSON_LESSON_PLAN)
                .withTags(FIRST_PERSON_TAGS).build();
        RemovePersonDescriptor descriptor = new RemovePersonDescriptorBuilder()
                .withHomeworkIndex(INDEX_FIRST_ITEM)
                .withAttendanceIndex(INDEX_FIRST_ITEM)
                .withSessionIndex(INDEX_FIRST_ITEM)
                .withGradeProgressIndex(INDEX_FIRST_ITEM).build();

        RemoveCommand removeCommand = new RemoveCommand(descriptor);

        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_PERSON_SUCCESS, removedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, removedPerson);
        setModelFullView(expectedModel, removedPerson.getName().fullName);

        assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        setFullView(INDEX_FIRST_PERSON);
        RemoveCommand removeCommand = new RemoveCommand(new RemovePersonDescriptor());
        Person removedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_PERSON_SUCCESS, removedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        setModelFullView(expectedModel, removedPerson.getName().fullName);

        assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_inListView_failure() {
        setListView();
        RemoveCommand removeCommand = new RemoveCommand(new RemovePersonDescriptor());

        assertCommandFailure(removeCommand, model, RemoveCommand.MESSAGE_NOT_VIEW_MODE);
    }

    @Test
    public void execute_inDayView_failure() {
        setDayView();
        RemoveCommand removeCommand = new RemoveCommand(new RemovePersonDescriptor());

        assertCommandFailure(removeCommand, model, RemoveCommand.MESSAGE_NOT_VIEW_MODE);
    }

    @Test
    public void equals() {
        final RemoveCommand standardCommand = new RemoveCommand(DESC_AMY_REM);

        RemovePersonDescriptor copyDescriptor = new RemovePersonDescriptor(DESC_AMY_REM);
        RemoveCommand commandWithSameValues = new RemoveCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        assertTrue(standardCommand.equals(standardCommand));

        assertFalse(standardCommand.equals(null));

        assertFalse(standardCommand.equals(new ClearCommand()));

        assertFalse(standardCommand.equals(new RemoveCommand(DESC_BOB_REM)));
    }
}
