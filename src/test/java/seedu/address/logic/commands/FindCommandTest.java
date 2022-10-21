package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_RESULTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicateWithOnlyDateTime;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicateWithOnlyReason;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicate;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicateWithOnlyName;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicateWithOnlyPhone;
import static seedu.address.testutil.PredicateGeneratorUtil.generateEmptyCombinedAppointmentPredicate;
import static seedu.address.testutil.PredicateGeneratorUtil.generateEmptyCombinedPersonPredicate;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_BENSON;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_CARL;
import static seedu.address.testutil.TypicalAppointments.SECOND_APPOINTMENT_CARL;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.GEORGE;
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
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.CombinedAppointmentPredicate;
import seedu.address.model.person.predicates.CombinedPersonPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CombinedPersonPredicate firstCombinedPersonPredicate =
                new CombinedPersonPredicate("first", "", "", "" ,
                        Collections.singletonList("test"));
        CombinedAppointmentPredicate firstCombinedAppointmentPredicate =
                new CombinedAppointmentPredicate("reason1", LocalDateTime.MIN, LocalDateTime.MAX);

        CombinedPersonPredicate secondCombinedPersonPredicate =
                new CombinedPersonPredicate("second", "", "", "" ,
                        Collections.singletonList("test"));
        CombinedAppointmentPredicate secondCombinedAppointmentPredicate =
                new CombinedAppointmentPredicate("reason2", LocalDateTime.MIN, LocalDateTime.MAX);

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
    public void execute_zeroKeywords_allPersonsAndAppointmentsListed() {
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size(),
                model.getFilteredAppointmentList().size());
        CombinedPersonPredicate personPredicate = generateEmptyCombinedPersonPredicate();
        CombinedAppointmentPredicate appointmentPredicate = generateEmptyCombinedAppointmentPredicate();
        FindCommand command = new FindCommand(personPredicate, appointmentPredicate, false);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_findAllAppointments_onlyPersonsWithAppointmentsListed() {
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 2, 3);
        CombinedPersonPredicate personPredicate = generateEmptyCombinedPersonPredicate();
        CombinedAppointmentPredicate appointmentPredicate = generateEmptyCombinedAppointmentPredicate();
        FindCommand command = new FindCommand(personPredicate, appointmentPredicate, true);

        Predicate<Person> atLeastOneAppointment = person -> !person.getAppointments().isEmpty();
        expectedModel.updateFilteredPersonList(personPredicate.and(atLeastOneAppointment));
        expectedModel.updateFilteredAppointmentList(appointmentPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_findPersonName_onlyAppointmentsFromFoundPersonsListed() {
        // Search for patients whose names contain "e".
        // Should only find Alice, Benson, Daniel, Elle and George, and display Benson's appointment.
        String searchName = "e";
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 5, 1);
        CombinedPersonPredicate personPredicate = generateCombinedPersonPredicateWithOnlyName(searchName);
        CombinedAppointmentPredicate appointmentPredicate = generateEmptyCombinedAppointmentPredicate();
        FindCommand command = new FindCommand(personPredicate, appointmentPredicate, false);

        Predicate<Appointment> onlyBensonAppointment = appointment -> appointment.getPatient().isSamePerson(BENSON);
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredAppointmentList(onlyBensonAppointment);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, ELLE, GEORGE), model.getFilteredPersonList());
        assertEquals(List.of(APPOINTMENT_BENSON), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_findAppointmentReason_onlyPersonsFromFoundAppointmentsListed() {
        // Search for appointment with reason "cough".
        // Should only find Carl, and display only the cough appointment.
        String searchReason = "cough";
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 1, 1);
        CombinedPersonPredicate personPredicate = generateEmptyCombinedPersonPredicate();
        CombinedAppointmentPredicate appointmentPredicate =
                generateCombinedAppointmentPredicateWithOnlyReason(searchReason);
        FindCommand command = new FindCommand(personPredicate, appointmentPredicate, true);

        Predicate<Person> justCarl = person -> person.isSamePerson(CARL);
        expectedModel.updateFilteredPersonList(justCarl);
        expectedModel.updateFilteredAppointmentList(appointmentPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(CARL), model.getFilteredPersonList());
        assertEquals(List.of(APPOINTMENT_CARL), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_findAppointmentReason_onlyRelevantAppointmentsListed() {
        // Search for appointment with reason "throat".
        // Should find Carl and Benson, and only display the Sore throat appointment from each of them
        // (Benson's first and Carl's second appointment).
        String searchReason = "throat";
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 2, 2);
        CombinedPersonPredicate personPredicate = generateEmptyCombinedPersonPredicate();
        CombinedAppointmentPredicate appointmentPredicate =
                generateCombinedAppointmentPredicateWithOnlyReason(searchReason);
        FindCommand command = new FindCommand(personPredicate, appointmentPredicate, true);

        Predicate<Person> justCarlAndBenson = person -> person.isSamePerson(BENSON) || person.isSamePerson(CARL);
        Predicate<Appointment> correctAppointment = appointment -> appointment.equals(APPOINTMENT_BENSON)
                || appointment.equals(SECOND_APPOINTMENT_CARL);
        expectedModel.updateFilteredPersonList(justCarlAndBenson);
        expectedModel.updateFilteredAppointmentList(correctAppointment);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON, CARL), model.getFilteredPersonList());
        assertEquals(List.of(APPOINTMENT_BENSON, SECOND_APPOINTMENT_CARL), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_personAndAppointmentFind_onlyRelevantResultsListed() {
        // Search for phones containing the number "3" and appointments before 2015.
        // Should only find Carl, and only display the cough appointment from Carl (his first appointment).
        String searchPhone = "3";
        String searchDateEnd = "2015-01-01 00:00";
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 1, 1);
        CombinedPersonPredicate personPredicate = generateCombinedPersonPredicateWithOnlyPhone(searchPhone);
        CombinedAppointmentPredicate appointmentPredicate =
                generateCombinedAppointmentPredicateWithOnlyDateTime(EMPTY_STRING, searchDateEnd);
        FindCommand command = new FindCommand(personPredicate, appointmentPredicate, true);

        Predicate<Person> justCarl = person -> person.isSamePerson(CARL);
        Predicate<Appointment> onlyCarlFirstAppointment =
                appointment -> appointment.equals(APPOINTMENT_CARL);
        expectedModel.updateFilteredPersonList(justCarl);
        expectedModel.updateFilteredAppointmentList(onlyCarlFirstAppointment);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(CARL), model.getFilteredPersonList());
        assertEquals(List.of(APPOINTMENT_CARL), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_findTagsAndEmail_onlyRelevantResultsListed() {
        // Search for persons with tag "friends" and email containing "li".
        // Should only find Alice and Daniel, and no appointments.
        String searchEmail = "li";
        String searchTag = "nose";
        String expectedMessage = String.format(MESSAGE_RESULTS_LISTED_OVERVIEW, 2, 0);
        CombinedPersonPredicate personPredicate =
                generateCombinedPersonPredicate(EMPTY_STRING, EMPTY_STRING, searchEmail, EMPTY_STRING, searchTag);
        CombinedAppointmentPredicate appointmentPredicate = generateEmptyCombinedAppointmentPredicate();
        FindCommand command = new FindCommand(personPredicate, appointmentPredicate, false);

        Predicate<Person> onlyAliceAndDaniel = person -> person.isSamePerson(ALICE) || person.isSamePerson(DANIEL);
        Predicate<Appointment> alwaysFalse = unused -> false;
        expectedModel.updateFilteredPersonList(onlyAliceAndDaniel);
        expectedModel.updateFilteredAppointmentList(alwaysFalse);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ALICE, DANIEL), model.getFilteredPersonList());
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
