package longtimenosee.model.event.predicate;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.util.StringUtil;
import longtimenosee.model.event.Event;

/**
 * Tests that a {@code Event}'s participant {@code Name} matches any of the keywords given.
 */
public class NameInEventContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public NameInEventContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        for (String keyword : keywords) {
            if (StringUtil.containsWordIgnoreCase(event.getPersonName().personName, keyword)) {
                return true;
            }
        }
        return false;
    }
}
