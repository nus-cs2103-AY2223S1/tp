package seedu.address.model.person.predicates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;



/**
 * List to store all fieldContainsPredicate to be tested on a {@code Person}'s various {@code fields}
 */
public class ListOfContainsKeywordsPredicates {
    private final List<Predicate<Person>> predicates;

    public ListOfContainsKeywordsPredicates(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    /**
     * Create a new empty List of FieldContainsKeywordsPredicates
     * @return ListOfContainsKeywordsPredicates
     */
    public static ListOfContainsKeywordsPredicates newListOfContainsKeywordsPredicates() {
        return new ListOfContainsKeywordsPredicates(new ArrayList<Predicate<Person>>());
    }

    /**
     * Add a predicate to the list
     * @param predicate
     */
    public void addPredicate(Predicate<Person> predicate) {
        predicates.add(predicate);
    }

    /**
     * Chains the predicates in the list together into a single predicate
     * @return chained predicate
     */
    public Predicate<Person> getChainedPredicate() {
        Predicate<Person> chainedPredicate = p -> true; // dummy initial predicate
        for (Predicate<Person> predicate: predicates) {
            chainedPredicate = chainedPredicate.and(predicate);
        }
        return chainedPredicate;
    }

    /**
     * Checks whether there is at least 1 predicate in the predicate list
     * @return boolean
     */
    public boolean hasPredicate() {
        return !predicates.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListOfContainsKeywordsPredicates // instanceof handles nulls
                && predicates.equals(((ListOfContainsKeywordsPredicates) other).predicates)); // state check
    }
}
