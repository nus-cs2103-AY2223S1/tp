package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_RESULTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.PredicateUtil.generateCombinedAppointmentPredicate;
import static seedu.address.testutil.PredicateUtil.generateCombinedPersonPredicate;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AppointmentOfFilteredPersonsPredicate;
import seedu.address.model.person.CombinedAppointmentPredicate;
import seedu.address.model.person.CombinedPersonPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PredicateUtil;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final String empty = "";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CombinedPersonPredicate firstCombinedPersonPredicate =
                new CombinedPersonPredicate("first", "", "", "" ,
                        Collections.singletonList("test"));
        CombinedAppointmentPredicate firstCombinedAppointmentPredicate
                = new CombinedAppointmentPredicate("reason1", LocalDateTime.MIN, LocalDateTime.MAX);

        CombinedPersonPredicate secondCombinedPersonPredicate =
                new CombinedPersonPredicate("second", "", "", "" ,
                        Collections.singletonList("test"));
        CombinedAppointmentPredicate secondCombinedAppointmentPredicate
                = new CombinedAppointmentPredicate("reason2", LocalDateTime.MIN, LocalDateTime.MAX);

        FindCommand findFirstCommand =
                new FindCommand(firstCombinedPersonPredicate, firstCombinedAppointmentPredicate,
                        true);
        FindCommand findSecondCommand =
                new FindCommand(secondCombinedPersonPredicate, secondCombinedAppointmentPredicate,
                        true);
        FindCommand findFirstCommandWithFalseFlag =
                new FindCommand(firstCombinedPersonPredicate, firstCombinedAppointmentPredicate,
                        false);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy =
                new FindCommand(firstCombinedPersonPredicate, firstCombinedAppointmentPredicate,
                        true);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different arguments -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // different boolean -> return false
        assertFalse(findFirstCommand.equals(findFirstCommandWithFalseFlag));
    }

    @Test
    public void execute_zeroKeywords_AllPersonsAndAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size(),
                model.getFilteredAppointmentList().size());
        CombinedPersonPredicate personPredicate = generateCombinedPersonPredicate(empty, empty, empty, empty, empty);
        CombinedAppointmentPredicate appointmentPredicate = generateCombinedAppointmentPredicate(empty, empty, empty);
        FindCommand command = new FindCommand(personPredicate, appointmentPredicate, false);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_findAllAppointments_OnlyPersonsWithAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 2, 3);
        CombinedPersonPredicate personPredicate = generateCombinedPersonPredicate(empty, empty, empty, empty, empty);
        CombinedAppointmentPredicate appointmentPredicate = generateCombinedAppointmentPredicate(empty, empty, empty);
        FindCommand command = new FindCommand(personPredicate, appointmentPredicate, true);

        Predicate<Person> atLeastOneAppointment = person -> !person.getAppointments().isEmpty();
        expectedModel.updateFilteredPersonList(personPredicate.and(atLeastOneAppointment));
        expectedModel.updateFilteredAppointmentList(appointmentPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /*
    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 4, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz Benson");
        FindCommand command = new FindCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        List<Person> validPersons = expectedModel.getFilteredPersonList();
        AppointmentOfFilteredPersonsPredicate appointmentPredicate =
                new AppointmentOfFilteredPersonsPredicate(validPersons);
        expectedModel.updateFilteredAppointmentList(appointmentPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

     */

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
