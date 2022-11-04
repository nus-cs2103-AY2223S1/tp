package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalGroups {

    public static final Group ALPHA = new GroupBuilder().withName("Team_Alpha")
            .withTags("Important", "Gaming").withAttribute("Meetup", "Saturday").build();

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addTeam(group);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
