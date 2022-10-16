package seedu.address.testutil;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterCommandPredicate;
import seedu.address.model.person.Person;

/**
 * Stub class of FilterCommandPredicate for testing.
 */
public class FilterCommandPredicateStub extends FilterCommandPredicate {
    private Predicate<Person> predicate;

    /**
     * Creates a stub of FilterCommandPredicate that filters based on {@code predicate}.
     *
     * @param predicate
     */
    public FilterCommandPredicateStub(Predicate<Person> predicate) {
        super(Set.of(), Set.of());
        this.predicate = predicate;
    }

    @Override
    public boolean addNameFiltersToSet(Set<Predicate<Person>> predicateSet) {
        if (predicate == null) {
            return false;
        }
        return predicateSet.addAll(Set.of(predicate));
    }

    @Override
    public boolean addTagFiltersToSet(Set<Predicate<Person>> predicateSet) {
        return addNameFiltersToSet(predicateSet);
    }

    @Override
    public boolean removeNameFiltersFromSet(Set<Predicate<Person>> predicateSet) {
        if (predicate == null) {
            return false;
        }
        return predicateSet.removeAll(Set.of(predicate));
    }

    @Override
    public boolean removeTagFiltersFromSet(Set<Predicate<Person>> predicateSet) {
        return removeNameFiltersFromSet(predicateSet);
    }
}
