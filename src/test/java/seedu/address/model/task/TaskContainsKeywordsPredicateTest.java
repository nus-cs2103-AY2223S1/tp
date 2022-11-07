package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.TaskBuilder;

public class TaskContainsKeywordsPredicateTest {

    private final String descriptionKeyword1 = "math";
    private final String descriptionKeyword2 = "Assignment";
    private final String descriptionKeyword3 = "7";
    private final String deadlineKeyword1 = "20-12-2022";
    private final String deadlineKeyword2 = "21-12-2022";
    private final String deadlineKeyword3 = "22-12-2022";
    private final Boolean completionStatusTrue = true;
    private final Boolean completionStatusFalse = false;
    private final String tagKeyword1 = "cs2101";
    private final String tagKeyword2 = "cs2103t";
    private final String tagKeyword3 = "ma3269";
    private final String tagKeyword4 = "ma1521";

    private final List<Description> descriptionKeywords =
            Arrays.asList(new Description(descriptionKeyword1), new Description(descriptionKeyword2));
    private final List<Deadline> deadlineKeywords =
        Arrays.asList(new Deadline(deadlineKeyword1), new Deadline(deadlineKeyword2));
    private final List<CompletionStatus> completionStatusKeywords =
            Arrays.asList(new CompletionStatus(completionStatusTrue), new CompletionStatus(completionStatusFalse));
    private final Set<Tag> tagKeywords =
            new HashSet<>(Arrays.asList(new Tag(tagKeyword1), new Tag(tagKeyword2)));

    private final List<Description> descriptionKeywordsAllUpperCase =
            Arrays.asList(new Description(descriptionKeyword1.toUpperCase()),
                    new Description(descriptionKeyword2.toUpperCase()));
    private final Set<Tag> tagKeywordsAllUpperCase =
            new HashSet<>(Arrays.asList(new Tag(tagKeyword1.toUpperCase()), new Tag(tagKeyword2.toUpperCase())));

    private final List<Description> descriptionKeywordsUnmatched = List.of(new Description(descriptionKeyword3));
    private final List<Deadline> deadlineKeywordsUnmatched = List.of(new Deadline(deadlineKeyword3));
    private final Set<Tag> tagKeywordsUnmatched =
            new HashSet<>(Arrays.asList(new Tag(tagKeyword3), new Tag(tagKeyword4)));

    private final List<Description> emptyDescriptionKeywords = new ArrayList<>();
    private final List<Deadline> emptyDeadlineKeywords = new ArrayList<>();
    private final List<CompletionStatus> emptyCompletionStatusKeywords = new ArrayList<>();
    private final Set<Tag> emptyTags = new HashSet<>();

    private final Task sampleTask1 = new TaskBuilder()
            .withDescription(descriptionKeyword1 + " " + descriptionKeyword2)
            .withDeadline(deadlineKeyword1)
            .withCompletionStatus(completionStatusFalse)
            .withTags(tagKeyword1, tagKeyword2)
            .build();

    private final Task sampleTask2 = new TaskBuilder()
            .withDescription(descriptionKeyword1 + " " + descriptionKeyword2)
            .withDeadline(deadlineKeyword2)
            .withCompletionStatus(completionStatusTrue)
            .withTags(tagKeyword1, tagKeyword2)
            .build();

    private final Task sampleTaskAllFieldsUpperCase = new TaskBuilder()
            .withDescription(descriptionKeyword1.toUpperCase() + " " + descriptionKeyword2.toUpperCase())
            .withDeadline(deadlineKeyword2)
            .withCompletionStatus(completionStatusFalse)
            .withTags(tagKeyword1.toUpperCase(), tagKeyword2.toUpperCase())
            .build();

    @Test
    public void equals() {
        TaskContainsKeywordsPredicate firstPredicate =
                new TaskContainsKeywordsPredicate(descriptionKeywords, deadlineKeywords, completionStatusKeywords);
        TaskContainsKeywordsPredicate secondPredicate =
                new TaskContainsKeywordsPredicate(emptyDescriptionKeywords,
                        emptyDeadlineKeywords, emptyCompletionStatusKeywords);
        TaskContainsKeywordsPredicate thirdPredicate =
                new TaskContainsKeywordsPredicate(tagKeywords);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsKeywordsPredicate firstPredicateCopy = new TaskContainsKeywordsPredicate(
                descriptionKeywords, deadlineKeywords, completionStatusKeywords);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // same values -> returns true (different constructor)
        TaskContainsKeywordsPredicate thirdPredicateCopy = new TaskContainsKeywordsPredicate(tagKeywords);
        assertTrue(thirdPredicate.equals(thirdPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_fieldsContainsKeywords_returnsTrue() {
        // Zero keywords, defined to be true (task not completed)
        TaskContainsKeywordsPredicate predicate =
                new TaskContainsKeywordsPredicate(emptyDescriptionKeywords,
                        emptyDeadlineKeywords, emptyCompletionStatusKeywords);
        assertTrue(predicate.test(sampleTask1));

        // Zero keywords, defined to be true (task completed)
        predicate = new TaskContainsKeywordsPredicate(emptyDescriptionKeywords,
                emptyDeadlineKeywords, emptyCompletionStatusKeywords);
        assertTrue(predicate.test(sampleTask2));

        //Zero tags, defined to be true (task not completed)
        predicate = new TaskContainsKeywordsPredicate(emptyTags);
        assertTrue(predicate.test(sampleTask1));

        //Zero tags, defined to be true (task completed)
        predicate = new TaskContainsKeywordsPredicate(emptyTags);
        assertTrue(predicate.test(sampleTask2));

        // Keywords match description, deadline, and completion status (task not completed)
        predicate = new TaskContainsKeywordsPredicate(descriptionKeywords,
                deadlineKeywords, completionStatusKeywords);
        assertTrue(predicate.test(sampleTask1));

        // Keywords match description, deadline, and completion status (task completed)
        predicate = new TaskContainsKeywordsPredicate(descriptionKeywords,
                deadlineKeywords, completionStatusKeywords);
        assertTrue(predicate.test(sampleTask2));

        // Keywords match description, deadline, and completion status (task description upper-case)
        predicate = new TaskContainsKeywordsPredicate(descriptionKeywords,
                deadlineKeywords, completionStatusKeywords);
        assertTrue(predicate.test(sampleTaskAllFieldsUpperCase));

        // Keywords match name, phone, email, address, remark (description keywords upper-case)
        predicate = new TaskContainsKeywordsPredicate(descriptionKeywordsAllUpperCase,
                deadlineKeywords, completionStatusKeywords);
        assertTrue(predicate.test(sampleTask1));

        // Keywords match tags
        predicate = new TaskContainsKeywordsPredicate(tagKeywords);
        assertTrue(predicate.test(sampleTask1));
    }

    @Test
    public void test_fieldsDoNotContainKeywords_returnsFalse() {
        // Keywords match deadline and completion status, but does not match description
        TaskContainsKeywordsPredicate predicate =
                new TaskContainsKeywordsPredicate(descriptionKeywordsUnmatched,
                        deadlineKeywords, completionStatusKeywords);
        assertFalse(predicate.test(sampleTask1));

        // Keywords match description and completion status, but does not match deadline
        predicate = new TaskContainsKeywordsPredicate(descriptionKeywords,
                deadlineKeywordsUnmatched, completionStatusKeywords);
        assertFalse(predicate.test(sampleTask1));

        // Keywords match description and deadline, but does not match completion status (task not completed)
        predicate = new TaskContainsKeywordsPredicate(descriptionKeywords,
                deadlineKeywords, List.of(new CompletionStatus(true)));
        assertFalse(predicate.test(sampleTask1));

        // Keywords match description and deadline, but does not match completion status (task completed)
        predicate = new TaskContainsKeywordsPredicate(descriptionKeywords,
                deadlineKeywords, List.of(new CompletionStatus(false)));
        assertFalse(predicate.test(sampleTask2));

        //Keywords do not match tags
        predicate = new TaskContainsKeywordsPredicate(tagKeywordsUnmatched);
        assertFalse(predicate.test(sampleTask1));

        //Keywords match tags but different case -> return false (Person's tags all upper-case)
        predicate = new TaskContainsKeywordsPredicate(tagKeywords);
        assertFalse(predicate.test(sampleTaskAllFieldsUpperCase));

        //Keywords match tags but different case -> return false (Tag keywords all upper-case)
        predicate = new TaskContainsKeywordsPredicate(tagKeywordsAllUpperCase);
        assertFalse(predicate.test(sampleTask1));
    }
}
