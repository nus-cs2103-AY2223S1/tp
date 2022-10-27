package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Predicate used to filter the clients in the {@code FilteredList}
 */
public abstract class FindPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs a {@code FindPredicate}.
     *
     * @param keywords keywords to be tested against.
     */
    protected FindPredicate(List<String> keywords) {
        this.keywords = keywords.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
    }

    public FindPredicate or(FindPredicate other) {
        return this.or(other);
    }
}
