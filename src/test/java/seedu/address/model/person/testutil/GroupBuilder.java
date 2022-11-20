package seedu.address.model.person.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_NAME = "CS2103T Team Project";
    public static final Person MEMBER_ALICE = TypicalPersons.ALICE;
    public static final Person MEMBER_BOB = TypicalPersons.BOB;
    public static final Person MEMBER_CARL = TypicalPersons.CARL;

    private GroupName name;
    private Set<Person> members;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        name = new GroupName(DEFAULT_NAME);
        members = makeDefaultMemberSet();
    }

    /**
     * Initializes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        name = groupToCopy.getName();
        members = new HashSet<>(groupToCopy.getMembers());
    }

    /**
     * Sets the {@code name} of the {@code Group} that we are building.
     */
    public GroupBuilder withName(String name) {
        this.name = new GroupName(name);
        return this;
    }

    /**
     * Sets the {@code members} Set of the {@code Group} that we are building.
     */
    public GroupBuilder withMembers(Person... members) {
        Set<Person> newMembers = new HashSet<>();
        for (Person p : members) {
            newMembers.add(p);
        }
        this.members = newMembers;
        return this;
    }

    /**
     * Creates a {@code members} Set containing the data derived from TypicalPersons.
     */
    private Set<Person> makeDefaultMemberSet() {
        Set<Person> defaultSet = new HashSet<>();
        defaultSet.add(MEMBER_ALICE);
        defaultSet.add(MEMBER_BOB);
        defaultSet.add(MEMBER_CARL);
        return defaultSet;
    }


    public Group build() {
        return new Group(name, members);
    }
}
