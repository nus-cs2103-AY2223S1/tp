package longtimenosee.logic.commands;

import static longtimenosee.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.testutil.TypicalEvents.WITH_ALICE;
import static longtimenosee.testutil.TypicalEvents.WITH_BENSON;
import static longtimenosee.testutil.TypicalEvents.WITH_CARL;
import static longtimenosee.testutil.TypicalEvents.WITH_ELLE;
import static longtimenosee.testutil.TypicalEvents.WITH_FIONA;
import static longtimenosee.testutil.TypicalEvents.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;
import longtimenosee.model.event.Event;
import longtimenosee.model.event.predicate.DescriptionContainsKeywordsPredicate;
import longtimenosee.model.event.predicate.EventDateMatchesInputPredicate;
import longtimenosee.model.event.predicate.NameInEventContainsKeywordsPredicate;

public class FindEventCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate("meeting with Alice");
        EventDateMatchesInputPredicate secondPredicate = new EventDateMatchesInputPredicate("2023-01-01");
        NameInEventContainsKeywordsPredicate thirdPredicate =
                new NameInEventContainsKeywordsPredicate(List.of("Alice", "Lee"));
        DescriptionContainsKeywordsPredicate fourthPredicate =
                new DescriptionContainsKeywordsPredicate("lunch break with Bernard");
        EventDateMatchesInputPredicate fifthPredicate = new EventDateMatchesInputPredicate("2024-05-18");
        NameInEventContainsKeywordsPredicate sixthPredicate =
                new NameInEventContainsKeywordsPredicate(List.of("Bernard", "Tan"));

        FindEventCommand firstFindEventCommand =
                new FindEventCommand(List.of(firstPredicate, secondPredicate, thirdPredicate));
        FindEventCommand secondFindEventCommand =
                new FindEventCommand(List.of(fourthPredicate, fifthPredicate, sixthPredicate));

        // EP: null value
        assertFalse(firstFindEventCommand.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstFindEventCommand.equals(firstFindEventCommand));

        // EP: same internal predicates
        FindEventCommand firstFindEventCommandCopy =
                new FindEventCommand(List.of(firstPredicate, secondPredicate, thirdPredicate));
        assertTrue(firstFindEventCommand.equals(firstFindEventCommandCopy));

        // EP: different internal predicates
        assertFalse(firstFindEventCommand.equals(secondFindEventCommand));
    }

    @Test
    public void execute_singlePredicate_noEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);

        // EP: Description Predicate
        DescriptionContainsKeywordsPredicate descriptionPredicate =
                new DescriptionContainsKeywordsPredicate("meeting with Alice");
        FindEventCommand findEventCommandWithDescriptionPredicate = new FindEventCommand(List.of(descriptionPredicate));
        expectedModel.updateFilteredEventList(descriptionPredicate);
        assertCommandSuccess(findEventCommandWithDescriptionPredicate, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());

        // EP: Date Predicate
        EventDateMatchesInputPredicate datePredicate = new EventDateMatchesInputPredicate("2023-05-05");
        FindEventCommand findEventCommandWithDatePredicate = new FindEventCommand(List.of(datePredicate));
        expectedModel.updateFilteredEventList(datePredicate);
        assertCommandSuccess(findEventCommandWithDatePredicate, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());

        // EP: Name Predicate
        NameInEventContainsKeywordsPredicate namePredicate =
                new NameInEventContainsKeywordsPredicate(List.of("Bernard"));
        FindEventCommand thirdFindEventCommand = new FindEventCommand(List.of(datePredicate));
        expectedModel.updateFilteredEventList(namePredicate);
        assertCommandSuccess(thirdFindEventCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_singlePredicate_oneEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);

        // EP: Description Predicate
        DescriptionContainsKeywordsPredicate descriptionPredicate =
                new DescriptionContainsKeywordsPredicate("lunch with Alice");
        FindEventCommand findEventCommandWithDescriptionPredicate = new FindEventCommand(List.of(descriptionPredicate));
        expectedModel.updateFilteredEventList(descriptionPredicate);
        assertCommandSuccess(findEventCommandWithDescriptionPredicate, model, expectedMessage, expectedModel);
        assertEquals(List.of(WITH_ALICE), model.getFilteredEventList());

        // EP: Date Predicate
        EventDateMatchesInputPredicate datePredicate = new EventDateMatchesInputPredicate("2023-12-05");
        FindEventCommand findEventCommandWithDatePredicate = new FindEventCommand(List.of(datePredicate));
        expectedModel.updateFilteredEventList(datePredicate);
        assertCommandSuccess(findEventCommandWithDatePredicate, model, expectedMessage, expectedModel);
        assertEquals(List.of(WITH_CARL), model.getFilteredEventList());

        // EP: Name Predicate
        NameInEventContainsKeywordsPredicate namePredicate = new NameInEventContainsKeywordsPredicate(List.of("ELLE"));
        FindEventCommand findEventCommandWithNamePredicate = new FindEventCommand(List.of(namePredicate));
        expectedModel.updateFilteredEventList(namePredicate);
        assertCommandSuccess(findEventCommandWithNamePredicate, model, expectedMessage, expectedModel);
        assertEquals(List.of(WITH_ELLE), model.getFilteredEventList());
    }

    @Test
    public void execute_singlePredicate_multipleEventsFound() {
        // EP: Description Predicate
        String firstExpectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        DescriptionContainsKeywordsPredicate descriptionPredicate = new DescriptionContainsKeywordsPredicate("coffee");
        FindEventCommand findEventCommandWithDescriptionPredicate = new FindEventCommand(List.of(descriptionPredicate));
        expectedModel.updateFilteredEventList(descriptionPredicate);
        assertCommandSuccess(findEventCommandWithDescriptionPredicate, model, firstExpectedMessage, expectedModel);
        assertEquals(List.of(WITH_BENSON, WITH_FIONA), model.getFilteredEventList());

        // EP: Date Predicate
        String secondExpectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        EventDateMatchesInputPredicate datePredicate = new EventDateMatchesInputPredicate("2023-12-20");
        FindEventCommand findEventCommandWithDatePredicate = new FindEventCommand(List.of(datePredicate));
        expectedModel.updateFilteredEventList(datePredicate);
        assertCommandSuccess(findEventCommandWithDatePredicate, model, secondExpectedMessage, expectedModel);
        assertEquals(List.of(WITH_ELLE, WITH_FIONA), model.getFilteredEventList());

        // EP: Name Predicate
        String thirdExpectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        NameInEventContainsKeywordsPredicate namePredicate =
                new NameInEventContainsKeywordsPredicate(List.of("benson", "kurz"));
        FindEventCommand findEventCommandWithNamePredicate = new FindEventCommand(List.of(namePredicate));
        expectedModel.updateFilteredEventList(namePredicate);
        assertCommandSuccess(findEventCommandWithNamePredicate, model, thirdExpectedMessage, expectedModel);
        assertEquals(List.of(WITH_BENSON, WITH_CARL), model.getFilteredEventList());
    }

    @Test
    public void execute_multiplePredicates_oneEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);

        // EP: Description and Date Predicates
        DescriptionContainsKeywordsPredicate firstDescriptionPredicate =
                new DescriptionContainsKeywordsPredicate("lunch with Alice");
        EventDateMatchesInputPredicate firstDatePredicate = new EventDateMatchesInputPredicate("2023-12-12");
        FindEventCommand firstFindEventCommand =
                new FindEventCommand(List.of(firstDescriptionPredicate, firstDatePredicate));
        Predicate<Event> firstFinalPredicate = firstDescriptionPredicate.and(firstDatePredicate);
        expectedModel.updateFilteredEventList(firstFinalPredicate);
        assertCommandSuccess(firstFindEventCommand, model, expectedMessage, expectedModel);
        assertEquals(List.of(WITH_ALICE), model.getFilteredEventList());

        // EP: Date and Name Predicates
        EventDateMatchesInputPredicate secondDatePredicate = new EventDateMatchesInputPredicate("2023-12-05");
        NameInEventContainsKeywordsPredicate secondNamePredicate =
                new NameInEventContainsKeywordsPredicate(List.of("CARL"));
        FindEventCommand secondFindEventCommand =
                new FindEventCommand(List.of(secondDatePredicate, secondNamePredicate));
        Predicate<Event> secondFinalPredicate = secondDatePredicate.and(secondNamePredicate);
        expectedModel.updateFilteredEventList(secondFinalPredicate);
        assertCommandSuccess(secondFindEventCommand, model, expectedMessage, expectedModel);
        assertEquals(List.of(WITH_CARL), model.getFilteredEventList());

        // EP: Name and Description Predicates
        NameInEventContainsKeywordsPredicate thirdNamePredicate =
                new NameInEventContainsKeywordsPredicate(List.of("ELLE"));
        DescriptionContainsKeywordsPredicate thirdDescriptionPredicate =
                new DescriptionContainsKeywordsPredicate("lunch with ELLE");
        FindEventCommand thirdFindEventCommand =
                new FindEventCommand(List.of(thirdNamePredicate, thirdDescriptionPredicate));
        Predicate<Event> thirdFinalPredicate = thirdNamePredicate.and(thirdDescriptionPredicate);
        expectedModel.updateFilteredEventList(thirdFinalPredicate);
        assertCommandSuccess(thirdFindEventCommand, model, expectedMessage, expectedModel);
        assertEquals(List.of(WITH_ELLE), model.getFilteredEventList());
    }
}
