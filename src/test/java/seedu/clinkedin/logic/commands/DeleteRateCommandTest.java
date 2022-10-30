package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.testutil.PersonBuilder;



public class DeleteRateCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteRateCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Rating rating = new Rating("6");
        DeleteRateCommand rateCommand = new DeleteRateCommand(Index.fromOneBased(1));

        Person personToEdit = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(personToEdit).withRating("0").build();
        String expectedMessage = String.format(DeleteRateCommand.MESSAGE_DELETE_RATE_SUCCESS, editedPerson);

        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noChangeInValue_success() {
        Person personToEdit = model.getFilteredPersonList().get(0);
        DeleteRateCommand rateCommand = new DeleteRateCommand(Index.fromOneBased(1));

        Person editedPerson = new PersonBuilder(personToEdit).withRating("0").build();
        String expectedMessage = String.format(DeleteRateCommand.MESSAGE_DELETE_RATE_SUCCESS, editedPerson);

        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void equals_sameObject() {
        DeleteRateCommand command1 = new DeleteRateCommand(Index.fromOneBased(1));
        assertTrue(command1.equals(command1));

    }

    @Test
    public void equals_diffObjectSameParameters() {
        DeleteRateCommand command1 = new DeleteRateCommand(Index.fromOneBased(1));
        AddRateCommand command2 = new AddRateCommand(Index.fromOneBased(1), new Rating("3"));
        assertTrue(command1.equals(command2));
    }

    @Test
    public void notEqual_null() {
        AddRateCommand command1 = new AddRateCommand(Index.fromOneBased(1), new Rating("3"));
        assertFalse(command1.equals(null));
    }

    @Test
    public void notEqual_differentType() {
        AddRateCommand command1 = new AddRateCommand(Index.fromOneBased(1), new Rating("3"));
        assertFalse(command1.equals(5));
    }

}
