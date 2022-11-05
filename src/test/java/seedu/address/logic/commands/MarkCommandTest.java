package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_MARK;
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
    public void execute_oneFieldSpecifiedFilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(0);

        setFullView(INDEX_FIRST_PERSON);
        PersonBuilder blankPerson = new PersonBuilder();
        Person markedPerson = blankPerson.withName(FIRST_PERSON_NAME)
                .withPhone(FIRST_PERSON_PHONE).withLessonPlan(FIRST_PERSON_LESSON_PLAN)
                .withTags(FIRST_PERSON_TAGS).withAttendance(FIRST_PERSON_ATTENDANCE)
                .withGradeProgress(FIRST_PERSON_GRADE_PROGRESS)
                .withSession(FIRST_PERSON_SESSIONS)
                .withHomework(FIRST_PERSON_HOMEWORK).build();

        // checking for homework field
        markedPerson.getHomeworkList().markAtIndex(INDEX_FIRST_ITEM);

        MarkPersonDescriptor descriptor = new MarkPersonDescriptorBuilder()
                .withHomeworkIndex(INDEX_FIRST_ITEM)
                .build();

        MarkCommand markCommand = new MarkCommand(descriptor);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARKED_PERSON_SUCCESS, markedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, markedPerson);
        setModelFullView(expectedModel, markedPerson.getName().fullName);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
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

        // Checking of markPersonDescriptor
        assertEquals(copyDescriptor, copyDescriptor);

        // checking of null objects
        assertFalse(copyDescriptor.equals(null));

        // same object
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

    }
}
