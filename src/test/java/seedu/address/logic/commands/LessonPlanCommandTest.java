package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_PLAN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_PLAN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class LessonPlanCommandTest {
    private static final String LESSON_PLAN_STUB = "Some lesson plan";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addLessonPlanUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withLessonPlan(LESSON_PLAN_STUB).build();

        LessonPlanCommand lessonPlanCommand = new LessonPlanCommand(INDEX_FIRST_PERSON, new LessonPlan(
                editedPerson.getLessonPlan().value));

        String expectedMessage = String.format(LessonPlanCommand.MESSAGE_ADD_LESSON_PLAN_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(lessonPlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addLessonPlanFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withLessonPlan(LESSON_PLAN_STUB).build();

        LessonPlanCommand lessonPlanCommand = new LessonPlanCommand(INDEX_FIRST_PERSON,
                new LessonPlan(LESSON_PLAN_STUB));

        String expectedMessage = String.format(LessonPlanCommand.MESSAGE_ADD_LESSON_PLAN_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(lessonPlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withLessonPlan(LESSON_PLAN_STUB).build();

        LessonPlanCommand lessonPlanCommand = new LessonPlanCommand(
                INDEX_FIRST_PERSON, new LessonPlan(editedPerson.getLessonPlan().value));

        String expectedMessage = String.format(LessonPlanCommand.MESSAGE_ADD_LESSON_PLAN_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(lessonPlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        LessonPlanCommand lessonPlanCommand = new LessonPlanCommand(
                outOfBoundIndex, new LessonPlan(VALID_LESSON_PLAN_BOB));

        assertCommandFailure(lessonPlanCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        LessonPlanCommand lessonPlanCommand = new LessonPlanCommand(
                outOfBoundIndex, new LessonPlan(VALID_LESSON_PLAN_BOB));
        assertCommandFailure(lessonPlanCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_inDayView_failure() {
        model.setDayView();
        LessonPlanCommand lessonPlanCommand = new LessonPlanCommand(
                INDEX_FIRST_PERSON, new LessonPlan(VALID_LESSON_PLAN_BOB));

        assertCommandFailure(lessonPlanCommand, model, LessonPlanCommand.MESSAGE_IN_DAY_MODE);
    }

    @Test
    public void equals() {
        final LessonPlanCommand standardCommand = new LessonPlanCommand(INDEX_FIRST_PERSON,
                new LessonPlan(VALID_LESSON_PLAN_AMY));
        // same values -> returns true
        LessonPlanCommand commandWithSameValues = new LessonPlanCommand(INDEX_FIRST_PERSON,
                new LessonPlan(VALID_LESSON_PLAN_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new LessonPlanCommand(INDEX_SECOND_PERSON,
                new LessonPlan(VALID_LESSON_PLAN_AMY))));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new LessonPlanCommand(INDEX_FIRST_PERSON,
                new LessonPlan(VALID_LESSON_PLAN_BOB))));
    }
}
