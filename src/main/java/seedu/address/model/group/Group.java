package seedu.address.model.group;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;

/**
 * Represents a Group in the address book
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Group {

    private final GroupName name;

    private final Set<Person> members = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Group(GroupName name, Set<Person> members) {
        requireAllNonNull(name, members);
        this.name = name;
        this.members.addAll(members);
    }

    public GroupName getName() {
        return name;
    }

    public Set<Person> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    /**
     * Check if person is already in the group.
     *
     * @param toCheck person to check if in group
     * @return true if person is in group, false otherwise
     */
    public boolean contains(Person toCheck) {
        for (Person p : members) {
            if (p.isSamePerson(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both groups have the same name.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getName().equals(getName());
    }

    /**
     * Returns true if both groups have same GroupName and every member exhibits equality.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return otherGroup.getName().equals(getName())
                && otherGroup.getMembers().equals(getMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, members);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        builder.append(" | Members: ");

        if (!getMembers().isEmpty()) {
            for (Person p : getMembers()) {
                builder.append(p.getName());
                builder.append(" | ");
            }
        } else {
            builder.append("None | ");
        }
        return builder.toString();
    }
}
