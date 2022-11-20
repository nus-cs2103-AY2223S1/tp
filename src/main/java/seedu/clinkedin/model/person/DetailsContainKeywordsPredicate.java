package seedu.clinkedin.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public interface DetailsContainKeywordsPredicate extends Predicate<Person> {

    @Override
    boolean test(Person person);

}
