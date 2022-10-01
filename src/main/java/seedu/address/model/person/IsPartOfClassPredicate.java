package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person} is part of a class
 * TODO: replace tags with class
 */
public class IsPartOfClassPredicate implements Predicate<Person> {
    private Tag targetClass;

    public IsPartOfClassPredicate(Tag targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * Tests if a Person object is part of the target class.
     *
     * @param person The Person to test.
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
