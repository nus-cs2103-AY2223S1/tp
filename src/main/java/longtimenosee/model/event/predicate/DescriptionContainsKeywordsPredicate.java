package longtimenosee.model.event.predicate;

import java.util.function.Predicate;

import longtimenosee.model.event.Event;

/**
 * Tests that an {@code Event}'s {@code Description} contains the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Event> {
    private final String keywords;

    public DescriptionContainsKeywordsPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return event.getDescription().retrieveDescription().toLowerCase().contains(keywords.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof DescriptionContainsKeywordsPredicate) {
                return keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords);
            } else {
                return false;
            }
        }
    }
}

