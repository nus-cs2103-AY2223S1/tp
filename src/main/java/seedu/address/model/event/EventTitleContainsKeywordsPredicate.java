package seedu.address.model.event;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that an {@code Event}'s {@code EventTitle} matches any of the keywords given.
 */
public class EventTitleContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public EventTitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getEventTitle().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventTitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EventTitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
