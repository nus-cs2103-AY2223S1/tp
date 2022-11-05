package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_MARK;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_ATTENDANCE;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_GRADE_PROGRESS;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_LESSON_PLAN;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_NAME;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_PHONE;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_SESSIONS;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_PERSON_TAGS;
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
import seedu.address.testutil.MarkPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code UnmarkCommand}.
 */
public class UnmarkCommandTest {

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
    public void execute_inListView_failure() {
        setListView();
        UnmarkCommand unmarkCommand = new UnmarkCommand(new MarkPersonDescriptor());

        assertCommandFailure(unmarkCommand, model, UnmarkCommand.MESSAGE_NOT_VIEW_MODE);
    }

    @Test
    public void execute_inDayView_failure() {
        setDayView();
        UnmarkCommand unmarkCommand = new UnmarkCommand(new MarkPersonDescriptor());

        assertCommandFailure(unmarkCommand, model, UnmarkCommand.MESSAGE_NOT_VIEW_MODE);
    }

    @Test
    public void execute_oneFieldSpecifiedFilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(0);

        setFullView(INDEX_FIRST_PERSON);
        PersonBuilder blankPerson = new PersonBuilder();
        Person unmarkedPerson = blankPerson.withName(FIRST_PERSON_NAME)
                .withPhone(FIRST_PERSON_PHONE).withLessonPlan(FIRST_PERSON_LESSON_PLAN)
                .withTags(FIRST_PERSON_TAGS).withAttendance(FIRST_PERSON_ATTENDANCE)
                .withGradeProgress(FIRST_PERSON_GRADE_PROGRESS)
                .withSession(FIRST_PERSON_SESSIONS)
                .withHomework(FIRST_PERSON_HOMEWORK).build();

        // checking for homework field
        unmarkedPerson.getHomeworkList().unmarkAtIndex(INDEX_FIRST_ITEM);

        MarkPersonDescriptor descriptor = new MarkPersonDescriptorBuilder()
                .withHomeworkIndex(INDEX_FIRST_ITEM)
                .build();

        UnmarkCommand unmarkCommand = new UnmarkCommand(descriptor);

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARKED_PERSON_SUCCESS, unmarkedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, unmarkedPerson);
        setModelFullView(expectedModel, unmarkedPerson.getName().fullName);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        setFullView(INDEX_FIRST_PERSON);
        UnmarkCommand unmarkCommand = new UnmarkCommand(new MarkPersonDescriptor());
        Person unmarkedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARKED_PERSON_SUCCESS, unmarkedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        setModelFullView(expectedModel, unmarkedPerson.getName().fullName);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }



    @Test
    public void equals() {
        setFullView(INDEX_FIRST_ITEM);
        final UnmarkCommand standardCommand = new UnmarkCommand(DESC_BOB_MARK);
        MarkPersonDescriptor copyDescriptor = new MarkPersonDescriptor(DESC_BOB_MARK);
        UnmarkCommand commandWithSameValues = new UnmarkCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

    }
}
