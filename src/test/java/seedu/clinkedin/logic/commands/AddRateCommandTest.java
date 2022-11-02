package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Rating;


public class AddRateCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRateCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRateCommand(null, new Rating("5")));
    }

    @Test
    public void execute_validIndexUnfilteredList_throwsCommandException() {
        Rating rating = new Rating("6");
        AddRateCommand rateCommand = new AddRateCommand(Index.fromOneBased(1), rating);
        assertThrows(CommandException.class, () -> rateCommand.execute(model));
    }

    @Test
    public void execute_noChangeInValue_throwsCommandException() {
        Person personToEdit = model.getFilteredPersonList().get(0);
        AddRateCommand rateCommand = new AddRateCommand(Index.fromOneBased(1), personToEdit.getRating());
        assertThrows(CommandException.class, () -> rateCommand.execute(model));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Rating rating = new Rating("6");
        AddRateCommand rateCommand = new AddRateCommand(Index.fromOneBased(100), rating);
        assertThrows(CommandException.class, () -> rateCommand.execute(model));
    }

    @Test
    public void execute_personHasRatingAlready_throwsCommandException() {
        Rating rating = new Rating("6");
        AddRateCommand rateCommand = new AddRateCommand(Index.fromOneBased(1), rating);
        assertThrows(CommandException.class, () -> rateCommand.execute(model));
    }

    @Test
    public void execute_validIndex_success() {
        Person personToEdit = model.getFilteredPersonList().get(1);
        Rating rating = new Rating("6");
        AddRateCommand rateCommand = new AddRateCommand(Index.fromOneBased(2), rating);
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTagTypeMap(),
                personToEdit.getStatus(), personToEdit.getNote(), rating, personToEdit.getLinks());

        String expectedMessage = String.format(AddRateCommand.MESSAGE_ADD_RATING_SUCCESS, editedPerson);

        expectedModel.setPerson(personToEdit, editedPerson);
        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_sameObject() {
        AddRateCommand command1 = new AddRateCommand(Index.fromOneBased(1), new Rating("3"));
        assertTrue(command1.equals(command1));

    }

    @Test
    public void equals_diffObjectSameParameters() {
        AddRateCommand command1 = new AddRateCommand(Index.fromOneBased(1), new Rating("3"));
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
