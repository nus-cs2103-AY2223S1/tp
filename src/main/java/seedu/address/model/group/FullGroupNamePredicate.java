package seedu.address.model.group;

import java.util.function.Predicate;


public class FullGroupNamePredicate implements Predicate<Group> {
    private final String fullGroupName;

    public FullGroupNamePredicate(String fullGroupName) {
        this.fullGroupName = fullGroupName;

    }

    @Override
    public boolean test(Group group) {
        return group.getName().toString().equals(fullGroupName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FullGroupNamePredicate // instanceof handles nulls
                && fullGroupName.equals(((FullGroupNamePredicate) other).fullGroupName)); // state check
    }

}
