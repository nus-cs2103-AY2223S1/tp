package seedu.address.model.teammate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s tags contains a specified set of tags.
 */
public class ContainsTagPredicate implements Predicate<Teammate> {

    private final Set<String> tags = new HashSet<>();

    /**
     * Constructs an ContainsTagPredicate.
     * @param tags the set of tags to search for
     */
    public ContainsTagPredicate(Collection<String> tags) {
        this.tags.addAll(tags);
    }

    @Override
    public boolean test(Teammate teammate) {
        return teammate.getTags().stream().map(x -> x.tagName.trim().toLowerCase()).anyMatch(tags::contains);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsTagPredicate // instanceof handles nulls
                && tags.equals(((ContainsTagPredicate) other).tags)); // state check
    }
}

