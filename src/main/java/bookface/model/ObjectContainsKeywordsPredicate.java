package bookface.model;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests that a {@code Object}'s fields specified in {@code wrappedPredicate} matches any of the keywords given.
 */
public class ObjectContainsKeywordsPredicate<T, R> implements Predicate<T> {
    private final List<? extends R> keywords;
    private final Function<? super T, Predicate<? super R>> wrappedPredicate;

    /**
     * Generates a {@code Predicate<T>} object for the UI
     * @param keywords A list of keywords to filter by
     * @param wrappedPredicate The predicate function to test by
     */
    public ObjectContainsKeywordsPredicate(List<? extends R> keywords,
                                           Function<? super T, Predicate<? super R>> wrappedPredicate) {
        this.keywords = keywords;
        this.wrappedPredicate = wrappedPredicate;
    }

    @Override
    public boolean test(T t) {
        return keywords.stream()
                .anyMatch(wrappedPredicate.apply(t));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ObjectContainsKeywordsPredicate<?, ?> // instanceof handles nulls
                && keywords.equals(((ObjectContainsKeywordsPredicate<?, ?>) other).keywords)); // state check
    }

}
