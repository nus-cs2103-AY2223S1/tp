package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalTeachersPet;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Class;
import seedu.address.model.person.Mark;
import seedu.address.model.person.Money;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class MarkCommandTest {

    private static final Class VALID_CLASS = new Class(LocalDate.of(2022, 10, 22),
             LocalTime.of(13, 0), LocalTime.of(15, 0));

    private Model model = new ModelManager(getTypicalTeachersPet(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndexScheduledList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_SCHEDULE_INDEX);
    }

    @Test
    public void create_newMarkedPerson_success() throws CommandException {

        // initialize Amy to be not marked yet
        Person personToMark = new PersonBuilder(AMY).withMark(Boolean.FALSE).build();
        Person expectedMarkedPerson = new Person(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), VALID_CLASS.addDays(7), AMY.getMoneyOwed().addTo(AMY.getRatesPerClass()),
                AMY.getMoneyPaid(), AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags(),
                new Mark(Boolean.TRUE), VALID_CLASS);

        personToMark.setClass(VALID_CLASS);
        personToMark.setDisplayClass(VALID_CLASS);

        Person markedPerson = MarkCommand.createMarkedPerson(personToMark);
        assertEquals(expectedMarkedPerson, markedPerson);
    }

    @Test
    public void create_existingMarkedPerson_success() throws CommandException {
        // initialize Amy to be marked
        Person personToMark = new PersonBuilder(AMY).withMark(Boolean.TRUE).build();

        assertEquals(personToMark, MarkCommand.createMarkedPerson(personToMark));
    }

    @Test
    public void create_markedPersonWithHighDebt_failure() {
        Person heavyDebtor = new Person(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), VALID_CLASS, new Money(Integer.MAX_VALUE),
                AMY.getMoneyPaid(), AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags(),
                new Mark(Boolean.FALSE), VALID_CLASS);

        assertThrows(CommandException.class, () -> MarkCommand.createMarkedPerson(heavyDebtor));

    }

    @Test
    public void equals() {
        final MarkCommand standardCommand = new MarkCommand(INDEX_FIRST_PERSON);

        // same values -> returns true
        MarkCommand commandWithSameValues = new MarkCommand(INDEX_FIRST_PERSON);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_SECOND_PERSON)));
    }

}
