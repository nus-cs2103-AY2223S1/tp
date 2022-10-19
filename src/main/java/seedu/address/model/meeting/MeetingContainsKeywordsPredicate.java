package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.util.FindMeetingFunctionalInterface;

/**
 * Tests that a {@code Meetings}'s description matches any of the keywords given.
 */
public class MeetingContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;
    private final FindMeetingFunctionalInterface meetingField;

    /**
     * Constructor
     * @param keywords list of keywords to search for
     * @param meetingField meetingField to retrieve based on parameters from parser
     */
    public MeetingContainsKeywordsPredicate(List<String> keywords, FindMeetingFunctionalInterface meetingField) {
        this.keywords = keywords;
        this.meetingField = meetingField;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param meeting the input argument
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     */
    @Override
    public boolean test(Meeting meeting) {
        String searchContext = meetingField.getField(meeting);
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(searchContext, keyword));
    }
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingContainsKeywordsPredicate
                && keywords.equals(((MeetingContainsKeywordsPredicate) other).keywords));
    }

}
