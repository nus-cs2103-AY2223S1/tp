package longtimenosee.logic.commands;

import static longtimenosee.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.logic.commands.CommandTestUtil.showPersonAtIndex;
import static longtimenosee.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static longtimenosee.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.predicate.PinnedPersonPredicate;

public class ViewPinCommandTest {
    private Model model;
    private Model expectedModel;
    private PinnedPersonPredicate predicate;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        predicate = new PinnedPersonPredicate();
    }

    @Test
    public void execute_listHasNoPinned_showsEmptyList() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ViewPinCommand command = new ViewPinCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredPersonList()); //asserts that model shown is empty
    }
    @Test
    public void execute_listHasOnePinned_showsOne() {
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ViewPinCommand command = new ViewPinCommand(predicate);
        Person personToPin = model.getFilteredPersonList().get(1);
        model.pinPerson(personToPin);
        model.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
