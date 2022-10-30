package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

}
