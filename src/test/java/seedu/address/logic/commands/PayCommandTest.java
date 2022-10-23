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

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Money;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for PayCommand.
 */
public class PayCommandTest {

    private static final Money VALID_MONEY = new Money(300);
    private Model model = new ModelManager(getTypicalTeachersPet(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndexScheduledList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        PayCommand markCommand = new PayCommand(outOfBoundIndex, VALID_MONEY);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_SCHEDULE_INDEX);
    }

    @Test
    public void create_newPaidPerson_success() throws CommandException {
        Person personPaying = new PersonBuilder(AMY).withMoneyOwed(VALID_MONEY.value).build();
        Person expectedPaidPerson = new Person(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), AMY.getAClass(), new Money(0),
                AMY.getMoneyPaid().addTo(VALID_MONEY), AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags());

        Person paidPerson = PayCommand.createPaidPerson(personPaying, VALID_MONEY);
        assertEquals(expectedPaidPerson, paidPerson);
    }

    @Test
    public void create_paidPersonWithMaximumPaidAmount_failure() {
        Person person = new Person(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), AMY.getAClass(), new Money(Integer.MAX_VALUE),
                VALID_MONEY, AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags());

        assertThrows(CommandException.class, () -> PayCommand.createPaidPerson(person, new Money(Integer.MAX_VALUE)));
    }

    @Test
    public void create_paidPersonWithZeroDebt_failure() {
        Person person = new Person(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), AMY.getAClass(), new Money(0),
                VALID_MONEY, AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags());

        assertThrows(CommandException.class, () -> PayCommand.createPaidPerson(person, VALID_MONEY));
    }

    @Test
    public void create_paidPersonWithLowDebt_failure() {
        Person person = new Person(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), AMY.getAClass(), new Money(299),
                VALID_MONEY, AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags());

        assertThrows(CommandException.class, () -> PayCommand.createPaidPerson(person, VALID_MONEY));
    }

    @Test
    public void equals() {
        final PayCommand standardCommand = new PayCommand(INDEX_FIRST_PERSON, VALID_MONEY);

        // same values -> returns true
        PayCommand commandWithSameValues = new PayCommand(INDEX_FIRST_PERSON, VALID_MONEY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PayCommand(INDEX_SECOND_PERSON, VALID_MONEY)));

        // different amount paid -> returns false
        assertFalse(standardCommand.equals(new PayCommand(INDEX_FIRST_PERSON, new Money(100))));
    }
}