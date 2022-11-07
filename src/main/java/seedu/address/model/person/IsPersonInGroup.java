package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.group.Group;

/**
 * Tests that a {@code Person}'s {@code Fields} matches or contains any of the keywords given.
 */
public class IsPersonInGroup implements Predicate<Person> {
    private final Group group;

    public IsPersonInGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean test(Person person) {
        return person.getGroups().contains(group);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsPersonInGroup // instanceof handles nulls
                && group.equals(((IsPersonInGroup) other).group)); // state check
    }

}
