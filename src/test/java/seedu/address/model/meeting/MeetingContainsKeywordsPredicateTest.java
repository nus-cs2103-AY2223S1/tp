package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;

public class MeetingContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MeetingContainsKeywordsPredicate firstPredicate = new
                MeetingContainsKeywordsPredicate(firstPredicateKeywordList, FindMeetingCommand.GET_DESCRIPTION);
        MeetingContainsKeywordsPredicate secondPredicate = new
                MeetingContainsKeywordsPredicate(secondPredicateKeywordList, FindMeetingCommand.GET_DESCRIPTION);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        MeetingContainsKeywordsPredicate firstPredicateCopy = new
                MeetingContainsKeywordsPredicate(firstPredicateKeywordList, FindMeetingCommand.GET_DESCRIPTION);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_meetingNameContainsKeywords_returnsTrue() {
        // One keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(
                Collections.singletonList("Alice"), FindMeetingCommand.GET_PEOPLE);

        assertTrue(predicate.test(new MeetingBuilder()
                .withPersons(new PersonBuilder().withName("Alice Lim").build()).build()));

        // Multiple keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), FindMeetingCommand.GET_PEOPLE);
        assertTrue(predicate.test(new MeetingBuilder()
                .withPersons(new PersonBuilder().withName("Alice Bob").build()).build()));

        // Only one matching keyword
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), FindMeetingCommand.GET_PEOPLE);
        assertTrue(predicate.test(new MeetingBuilder()
                .withPersons(new PersonBuilder().withName("Alice Bob").build()).build()));

        // Mixed-case keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), FindMeetingCommand.GET_PEOPLE);
        assertTrue(predicate.test(new MeetingBuilder()
                .withPersons(new PersonBuilder().withName("Alice Bob").build()).build()));
    }

    @Test
    public void test_meetingNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MeetingContainsKeywordsPredicate predicate =
                new MeetingContainsKeywordsPredicate(Collections.emptyList(), FindMeetingCommand.GET_PEOPLE);
        assertFalse(predicate.test(new MeetingBuilder()
                .withPersons(new PersonBuilder().withName("Alice").build()).build()));

        // Non-matching keyword
        predicate = new MeetingContainsKeywordsPredicate(List.of("Carol"), FindMeetingCommand.GET_PEOPLE);
        assertFalse(predicate.test(new MeetingBuilder()
                .withPersons(new PersonBuilder().withName("Alice Bob").build()).build()));

        // predicate searches people for COM1 and UTown, but meetings built with location of UTown
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("COM1", "UTown"),
                FindMeetingCommand.GET_PEOPLE);
        assertFalse(predicate.test(new MeetingBuilder()
                .withLocation("UTown").build()));
    }
}
