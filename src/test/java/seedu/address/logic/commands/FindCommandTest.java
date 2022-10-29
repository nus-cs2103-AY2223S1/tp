package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

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
                .withNamesList(Collections.singletonList("name1")).withEmailsList(Collections.singletonList("email1"))
                .withGenderList(Collections.singletonList("gender1")).withLocationsList(Collections.singletonList("location1"))
                .withModulesList(Collections.singleton("module1"), true).withPhonesList(Collections.singletonList("111"))
                .withOfficeHoursList(Collections.singletonList("officeHour1")).withRatingsList(Collections.singletonList("1"))
                .withSpecList(Collections.singletonList("spec1")).withTypesList(Collections.singletonList("type1"))
                .withTagsList(Collections.singleton("tag1"), true).withYearsList(Collections.singletonList("year1"))
                .withUserNamesList(Collections.singletonList("username1")).build();

        PersonMatchesPredicate secondPredicate = new PersonMatchesPredicateBuilder()
                .withNamesList(Collections.singletonList("name2")).withEmailsList(Collections.singletonList("email2"))
                .withGenderList(Collections.singletonList("gender2")).withLocationsList(Collections.singletonList("location2"))
                .withModulesList(Collections.singleton("module2"), true).withPhonesList(Collections.singletonList("222"))
                .withOfficeHoursList(Collections.singletonList("officeHour2")).withRatingsList(Collections.singletonList("2"))
                .withSpecList(Collections.singletonList("spec2")).withTypesList(Collections.singletonList("type2"))
                .withTagsList(Collections.singleton("tag2"), true).withYearsList(Collections.singletonList("year2"))
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


}
