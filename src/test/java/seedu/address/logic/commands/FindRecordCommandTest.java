package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_RECORDS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecords.RECORD1;
import static seedu.address.testutil.TypicalRecords.RECORD2;
import static seedu.address.testutil.TypicalRecords.RECORD3;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.record.RecordContainsKeywordsPredicate;
import seedu.address.testutil.TestUtil;

/**
 * Contains integration tests (interaction with the Model) for {@code FindRecordCommand}.
 */
public class FindRecordCommandTest {
    private Model model = TestUtil.prepareModel();
    private Model expectedModel = TestUtil.prepareModel();
    private final List<String> emptyList = Arrays.asList();

    /**
     * Parses {@code userInput} into a {@code RecordContainsKeywordsPredicate}.
     */
    public static RecordContainsKeywordsPredicate preparePredicateWithOnePrefix(String userInput) {
        return new RecordContainsKeywordsPredicate(
                Arrays.asList(userInput.split("\\s+")), Arrays.asList(), "");
    }

    /**
     * Parses {@code userInput} into a {@code RecordContainsKeywordsPredicate}.
     */
    public static RecordContainsKeywordsPredicate preparePredicateWithMultiplePrefix(
            String record, String medication, String date) {
        List<String> recordKeywords = Arrays.asList(record.split("\\s+"));
        List<String> medicationKeywords = Arrays.asList(medication.split("\\s+"));
        return new RecordContainsKeywordsPredicate(recordKeywords, medicationKeywords, date);
    }

    @Test
    public void equals() {
        RecordContainsKeywordsPredicate firstPredicate = preparePredicateWithOnePrefix("covid-10");
        RecordContainsKeywordsPredicate secondPredicate = preparePredicateWithOnePrefix("cold");

        FindRecordCommand findFirstCommand = new FindRecordCommand(firstPredicate);
        FindRecordCommand findSecondCommand = new FindRecordCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindRecordCommand findFirstCommandCopy = new FindRecordCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_unspecifiedPrefix_allRecordsFound() {
        String expectedMessage = String.format(MESSAGE_RECORDS_LISTED_OVERVIEW, 3);
        RecordContainsKeywordsPredicate predicate = preparePredicateWithOnePrefix(" ");
        FindRecordCommand command = new FindRecordCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RECORD3, RECORD1, RECORD2), model.getFilteredRecordList());
    }


    @Test
    public void execute_multiplePrefix_oneRecordFound() {
        String expectedMessage = String.format(MESSAGE_RECORDS_LISTED_OVERVIEW, 1);
        RecordContainsKeywordsPredicate predicate = preparePredicateWithMultiplePrefix(
                "covid-19", "Paracetamol", "10-2011");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RECORD1), model.getFilteredRecordList());
    }

    @Test
    public void execute_onePrefixMultipleKeywords_multipleRecordsFound() {
        String expectedMessage = String.format(MESSAGE_RECORDS_LISTED_OVERVIEW, 3);
        RecordContainsKeywordsPredicate predicate = preparePredicateWithOnePrefix("covid-19 rashes cold");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RECORD3, RECORD1, RECORD2), model.getFilteredRecordList());
    }

    @Test
    public void execute_onePrefix_noRecordsFound() {
        String expectedMessage = String.format(MESSAGE_RECORDS_LISTED_OVERVIEW, 0);
        RecordContainsKeywordsPredicate predicate = preparePredicateWithOnePrefix("thisshouldnotmatch");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(emptyList, model.getFilteredRecordList());
    }
}
