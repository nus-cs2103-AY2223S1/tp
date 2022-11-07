package seedu.address.model.record;

import static seedu.address.logic.commands.FindRecordCommand.FIND_RECORD_DATE_FORMAT;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that all fields in {@code Record} that are specified by the input prefix matches any of the keywords given.
 */
public class RecordContainsKeywordsPredicate implements Predicate<Record> {
    private final List<String> recordKeywords;
    private final List<String> medicationKeywords;
    private final String dateKeyword;

    /**
     * Constructs a {@code RecordContainsKeywordsPredicate} with the specified fields for
     * rfind commands with multiple input parameters
     */
    public RecordContainsKeywordsPredicate(
            List<String> recordKeywords, List<String> medicationKeywords, String dateKeyword) {
        this.recordKeywords = recordKeywords;
        this.medicationKeywords = medicationKeywords;
        this.dateKeyword = dateKeyword;
    }

    @Override
    public boolean test(Record record) {
        assert record != null;
        boolean recordDataMatch = recordKeywords.isEmpty()
                || recordKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(record.record, keyword));

        boolean medicationMatch = medicationKeywords.isEmpty()
                || medicationKeywords.stream()
                .anyMatch(keyword -> record.getMedications().stream()
                        .anyMatch(meds -> StringUtil.containsWordIgnoreCase(meds.toString(), keyword)));

        String recordDate = record.getRecordDate().format(FIND_RECORD_DATE_FORMAT);
        boolean dateMatch = dateKeyword.isBlank()
                || dateKeyword.equals(recordDate);

        return dateMatch && medicationMatch && recordDataMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordContainsKeywordsPredicate // instanceof handles nulls
                && recordKeywords.equals(((RecordContainsKeywordsPredicate) other).recordKeywords)// state check
                && medicationKeywords.equals(((RecordContainsKeywordsPredicate) other).medicationKeywords) //state check
                && dateKeyword.equals(((RecordContainsKeywordsPredicate) other).dateKeyword)); // state check
    }

}
