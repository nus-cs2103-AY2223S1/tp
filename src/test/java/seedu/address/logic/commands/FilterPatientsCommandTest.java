package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_RESULTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AppointmentOfFilteredPersonsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterPatientsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FilterPatientsCommand filterFirstCommand = new FilterPatientsCommand(firstPredicate);
        FilterPatientsCommand filterSecondCommand = new FilterPatientsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterPatientsCommand filterFirstCommandCopy = new FilterPatientsCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 0, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterPatientsCommand command = new FilterPatientsCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        List<Person> validPersons = expectedModel.getFilteredPersonList();
        AppointmentOfFilteredPersonsPredicate appointmentPredicate =
                new AppointmentOfFilteredPersonsPredicate(validPersons);
        expectedModel.updateFilteredAppointmentList(appointmentPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 4, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz Benson");
        FilterPatientsCommand command = new FilterPatientsCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        List<Person> validPersons = expectedModel.getFilteredPersonList();
        AppointmentOfFilteredPersonsPredicate appointmentPredicate =
                new AppointmentOfFilteredPersonsPredicate(validPersons);
        expectedModel.updateFilteredAppointmentList(appointmentPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void execute_filterByTag_success() {
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 3, 1);
        Predicate<Person> predicate = new TagContainsKeywordsPredicate("friends");
        FilterPatientsCommand command = new FilterPatientsCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        List<Person> validPersons = expectedModel.getFilteredPersonList();
        AppointmentOfFilteredPersonsPredicate appointmentPredicate =
                new AppointmentOfFilteredPersonsPredicate(validPersons);
        expectedModel.updateFilteredAppointmentList(appointmentPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
