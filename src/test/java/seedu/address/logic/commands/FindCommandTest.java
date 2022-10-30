package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_LIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAMES_LIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ANDERSON;
import static seedu.address.testutil.TypicalPersons.BEN;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CABE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.COLIN;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonMatchesPredicate;
import seedu.address.testutil.PersonMatchesPredicateBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonMatchesPredicate firstPredicate = new PersonMatchesPredicateBuilder()
                .withNamesList(Collections.singletonList("name1"))
                .withEmailsList(Collections.singletonList("email1"))
                .withGenderList(Collections.singletonList("gender1"))
                .withLocationsList(Collections.singletonList("location1"))
                .withModulesSet(Collections.singleton("module1"), true)
                .withPhonesList(Collections.singletonList("111"))
                .withOfficeHoursList(Collections.singletonList("officeHour1"))
                .withRatingsList(Collections.singletonList("1"))
                .withSpecList(Collections.singletonList("spec1"))
                .withTypesList(Collections.singletonList("type1"))
                .withTagsSet(Collections.singleton("tag1"), true)
                .withYearsList(Collections.singletonList("year1"))
                .withUserNamesList(Collections.singletonList("username1")).build();

        PersonMatchesPredicate secondPredicate = new PersonMatchesPredicateBuilder()
                .withNamesList(Collections.singletonList("name2"))
                .withEmailsList(Collections.singletonList("email2"))
                .withGenderList(Collections.singletonList("gender2"))
                .withLocationsList(Collections.singletonList("location2"))
                .withModulesSet(Collections.singleton("module2"), true)
                .withPhonesList(Collections.singletonList("222"))
                .withOfficeHoursList(Collections.singletonList("officeHour2"))
                .withRatingsList(Collections.singletonList("2"))
                .withSpecList(Collections.singletonList("spec2"))
                .withTypesList(Collections.singletonList("type2"))
                .withTagsSet(Collections.singleton("tag2"), true)
                .withYearsList(Collections.singletonList("year2"))
                .withUserNamesList(Collections.singletonList("username1")).build();

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        assertTrue(findSecondCommand.equals(findSecondCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        FindCommand findSecondCommandCopy = new FindCommand(secondPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        assertTrue(findSecondCommand.equals(findSecondCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroArguments_noPersonFound() {
        PersonMatchesPredicate predicate;
        FindCommand command;
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        //no names provided
        predicate = new PersonMatchesPredicateBuilder().withNamesList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no emails provided
        predicate = new PersonMatchesPredicateBuilder().withEmailsList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no phones provided
        predicate = new PersonMatchesPredicateBuilder().withPhonesList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no types provided
        predicate = new PersonMatchesPredicateBuilder().withTypesList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no ratings provided
        predicate = new PersonMatchesPredicateBuilder().withRatingsList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no locations provided
        predicate = new PersonMatchesPredicateBuilder().withLocationsList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no genders provided
        predicate = new PersonMatchesPredicateBuilder().withGenderList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no specialisations provided
        predicate = new PersonMatchesPredicateBuilder().withSpecList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no years provided
        predicate = new PersonMatchesPredicateBuilder().withYearsList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no office hours provided
        predicate = new PersonMatchesPredicateBuilder().withOfficeHoursList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no usernames provided
        predicate = new PersonMatchesPredicateBuilder().withUserNamesList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        //no types provided
        predicate = new PersonMatchesPredicateBuilder().withTypesList(EMPTY_LIST).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNames_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withNamesList(VALID_NAMES_LIST).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePhones_multiplePersonsFound() {
        List<String> phonesList = List.of("94351253", "98765432", "87652533");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);

        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withPhonesList(phonesList).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleEmails_multiplePersonsFound() {
        List<String> emailList = List.of("werner@example.com", "lydia@example.com", "anna@example.com");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withEmailsList(emailList).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleGenders_multiplePersonsFound() {
        List<String> gendersList = List.of("F");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withGenderList(gendersList).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleLocations_multiplePersonsFound() {
        List<String> locationsList = List.of("SMU", "NUS", "NTU");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withLocationsList(locationsList).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleUsernames_multiplePersonsFound() {
        List<String> usernamesList = List.of("ben10", "callin", "cabe");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withUserNamesList(usernamesList).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, BEN, COLIN, CABE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_multiplePersonsFound() {
        //some tags
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withTagsSet(new HashSet<>(List.of("cool", "smart")), false).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, ELLE), model.getFilteredPersonList());

        //ALL tags
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        predicate = new PersonMatchesPredicateBuilder()
                .withTagsSet(new HashSet<>(List.of("cool", "smart")), true).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleModules_multiplePersonsFound() {
        //some modules match
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withModulesSet(new HashSet<>(List.of("CS1000", "CS9999")), false).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, GEORGE), model.getFilteredPersonList());

        //ALL modules match
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        predicate = new PersonMatchesPredicateBuilder()
                .withModulesSet(new HashSet<>(List.of("CS1000", "CS9999")), true).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleOfficeHours_multiplePersonsFound() {
        List<String> officeHoursList = List.of("1-11:00-2", "2-12:00-2");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withOfficeHoursList(officeHoursList).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BEN, COLIN), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleSpecialisations_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withSpecList(List.of("Networks")).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANDERSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleRatings_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withRatingsList(List.of("1", "2")).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleYears_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withYearsList(List.of("3", "4")).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }
}
