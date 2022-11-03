package longtimenosee.model.event.predicate;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.util.StringUtil;
import longtimenosee.model.event.Event;

/**
 * Tests that an {@code Event}'s participant {@code Name} matches any of the keywords given.
 */
public class NameInEventContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public NameInEventContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        for (String keyword : keywords) {
            if (StringUtil.containsWordIgnoreCase(event.getPersonName().fullName, keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof NameInEventContainsKeywordsPredicate) {
                return keywords.equals(((NameInEventContainsKeywordsPredicate) other).keywords);
            } else {
                return false;
            }
        }
    }
}
