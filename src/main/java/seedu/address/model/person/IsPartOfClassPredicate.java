package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person} is part of a class
 * TODO: replace tags with class
 */
public class IsPartOfClassPredicate implements Predicate<Person> {
    //TODO: replace with class
    private Tag targetClass;


    /**
     * TODO: replace with class
     * Returns a predicate that checks if a Person object is part of the {@code targetClass}.
     *
     * @param targetClass Class to check against the Person object.
     */
    public IsPartOfClassPredicate(Tag targetClass) {
        requireNonNull(targetClass);
        this.targetClass = targetClass;
    }

    //TODO: replace with class
    /**
     * Tests if a Person object is part of the target class.
     *
     * @param person The Person object to test.
     * @return True, if the Person object is part of the target class.
     */
    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(tag -> tag.equals(targetClass));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsPartOfClassPredicate // instanceof handles nulls
                && targetClass.equals(((IsPartOfClassPredicate) other).targetClass)); // state check
    }
}
