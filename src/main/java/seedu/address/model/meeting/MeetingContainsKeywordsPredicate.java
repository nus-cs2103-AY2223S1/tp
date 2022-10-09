package seedu.address.model.meeting;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

public class MeetingContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public MeetingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param meeting the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream().
                anyMatch(keyword -> StringUtil.containsWordIgnoreCase(meeting.getDescription(), keyword));
    }
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingContainsKeywordsPredicate
                && keywords.equals(((MeetingContainsKeywordsPredicate) other).keywords));
    }

}
