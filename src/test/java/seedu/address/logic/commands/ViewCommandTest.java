package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalSurvin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsAttributePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalSurvin(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSurvin(), new UserPrefs());

    @Test
    public void equals() {
        PersonContainsAttributePredicate firstPredicate =
                new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("male"), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>());
        PersonContainsAttributePredicate secondPredicate =
                new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("female"), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>());

        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));


    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsAttributePredicate testPredicate =
                new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("r4nd0m_inV4l1d_g3nd3r"), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>());
        ViewCommand command = new ViewCommand(testPredicate);
        expectedModel.updateFilteredPersonList(testPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonContainsAttributePredicate testPredicate =
                new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("female"), new ArrayList<>(),
                        new ArrayList<>(), List.of("christian"), new ArrayList<>(),
                        new ArrayList<>());
        ViewCommand command = new ViewCommand(testPredicate);
        expectedModel.updateFilteredPersonList(testPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredPersonList());
    }

}
