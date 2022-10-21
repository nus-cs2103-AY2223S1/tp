package longtimenosee.model.event.predicate;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.util.StringUtil;
import longtimenosee.model.event.Event;

/**
 * Tests that a {@code Event}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        for (String keyword : keywords) {
            if (StringUtil.containsWordIgnoreCase(event.getDescription().retrieveDescription(), keyword)) {
                return true;
            }
        }
        return false;
    }
}

