package seedu.address.model.group.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_IP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_TP;
import static seedu.address.model.person.testutil.TypicalPersons.ALICE;
import static seedu.address.model.person.testutil.TypicalPersons.BENSON;
import static seedu.address.model.person.testutil.TypicalPersons.CARL;
import static seedu.address.model.person.testutil.TypicalPersons.DANIEL;
import static seedu.address.model.person.testutil.TypicalPersons.ELLE;
import static seedu.address.model.person.testutil.TypicalPersons.FIONA;
import static seedu.address.model.person.testutil.TypicalPersons.GEORGE;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalPersons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Person;
import seedu.address.model.person.testutil.GroupBuilder;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final GroupName GROUPNAME_TP = new GroupName(VALID_GROUPNAME_TP);
    public static final GroupName GROUPNAME_IP = new GroupName(VALID_GROUPNAME_IP);
    public static final Group TEAM_PROJECT =
            new GroupBuilder().withName("Team Project").withMembers(ALICE, BENSON, CARL).build();

    public static final Group INDIVIDUAL_PROJECT =
            new GroupBuilder().withName("Individual Project").withMembers(DANIEL).build();

    public static final Group ORAL_PRESENTATION =
            new GroupBuilder().withName("Oral Presentation").withMembers(ELLE, FIONA, GEORGE).build();

    /**
     * Returns an {@code AddressBook} with all the typical persons and sample groups.
     * @return
     */
    public static AddressBook getTypicalAddressBookWithGroups() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(TEAM_PROJECT, INDIVIDUAL_PROJECT, ORAL_PRESENTATION));
    }
}
