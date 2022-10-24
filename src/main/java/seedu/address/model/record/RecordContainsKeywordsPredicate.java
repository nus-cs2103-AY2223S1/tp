package seedu.address.model.record;

import static seedu.address.logic.commands.FindRecordCommand.FIND_DATE_FORMAT;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Record}'s {@code record} matches any of the keywords given.
 */
public class RecordContainsKeywordsPredicate implements Predicate<Record> {
    private final List<String> recordKeywords;
    private final List<String> medicationKeywords;
    private final Optional<String> dateKeyword;

    public RecordContainsKeywordsPredicate(List<String> recordKeywords) {
        this(recordKeywords, new ArrayList<>(), Optional.empty());
    }

    /**
     * Constructs a {@code RecordContainsKeywordsPredicate} with the specified fields for
     * rfind commands with multiple input parameters
     */
    public RecordContainsKeywordsPredicate(
            List<String> recordKeywords, List<String> medicationKeywords, Optional<String> dateKeyword) {
        this.recordKeywords = recordKeywords;
        this.medicationKeywords = medicationKeywords;
        this.dateKeyword = dateKeyword;
    }

    @Override
    public boolean test(Record record) {
        boolean recordDataMatch = recordKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(record.record, keyword));

        boolean medicationMatch = medicationKeywords.stream()
                .anyMatch(keyword -> record.getMedications().stream()
                        .anyMatch(meds -> StringUtil.containsWordIgnoreCase(meds.toString(), keyword)));

        String recordDate = record.getRecordDate().format(FIND_DATE_FORMAT);
        boolean dateMatch = dateKeyword.map(date -> date.equals(recordDate)).orElse(false);

        return dateMatch || medicationMatch || recordDataMatch;
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
