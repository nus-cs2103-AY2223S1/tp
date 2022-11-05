package longtimenosee.logic.commands;

import static longtimenosee.logic.commands.CommandTestUtil.assertCommandFailure;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.logic.commands.PinCommand.MESSAGE_UNPIN_PERSON_SUCCESS;
import static longtimenosee.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import longtimenosee.commons.core.index.Index;
import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.predicate.PinnedPersonPredicate;


public class PinCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel;
    private PinnedPersonPredicate predicate;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        predicate = new PinnedPersonPredicate();
    }

    @Test
    public void execute_indexOutOfRange_failure() {
        String expectedMessage = "The person index provided is invalid";
        PinCommand command = new PinCommand(Index.fromOneBased(10000));
        assertCommandFailure(command, model, expectedMessage);
    }
    @Test
    public void execute_pinIndexAgainToUnpin_success() {
        Person personToPin = model.getFilteredPersonList().get(0);
        model.pinPerson(personToPin);
        String expectedMessage = String.format(MESSAGE_UNPIN_PERSON_SUCCESS, personToPin);
        PinCommand pinCommand = new PinCommand(Index.fromOneBased(1));
        model.updateFilteredPersonList(predicate); // should also be an empty list
        expectedModel.updateFilteredPersonList(predicate); //will be an empty list
        assertCommandSuccess(pinCommand, model, expectedMessage, expectedModel);
    }

}
