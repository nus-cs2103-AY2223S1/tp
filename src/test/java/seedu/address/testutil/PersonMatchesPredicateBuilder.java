package seedu.address.testutil;




import java.util.Arrays;
import seedu.address.model.person.PersonMatchesPredicate;

/**
 * A utility class to help with building PersonMatchesPredicate objects.
 */
public class PersonMatchesPredicateBuilder {
    private PersonMatchesPredicate predicate;

    public PersonMatchesPredicateBuilder() {
        predicate = new PersonMatchesPredicate();
    }

    /**
     * Sets the List of Names for the PersonMatchesPredicate we are building.
     */
    public PersonMatchesPredicateBuilder withNames(String names) {
        predicate.setNamesList(Arrays.asList(names.split("\\s+")));
        return this;
    }

    public PersonMatchesPredicate build() {
        return predicate;
    }
}
