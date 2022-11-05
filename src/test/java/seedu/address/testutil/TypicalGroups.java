package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in
 * tests.
 */
public class TypicalGroups {

    public static final Group TEAM_ALPHA = new GroupBuilder().withName("Team_Alpha")
            .withAttribute("Work", "Internship").withAttribute("Leave_number", "3")
            .build();
    public static final Group TEAM_BETA = new GroupBuilder().withName("Team_Beta").build();
    public static final Group TEAM_OMEGA = new GroupBuilder().withName("Team_Omega")
            .withAttribute("Urgent", "Do paperwork").withParent(TEAM_ALPHA).build();
    public static final Group TEAM_GAMMA = new GroupBuilder().withName("Team_Gamma").build();
    public static final Group TEAM_A = new GroupBuilder().withName("Team_A")
            .withAttribute("Meetup", "Saturday").withParent(TEAM_ALPHA).build();
    public static final Group TEAM_B = new GroupBuilder().withName("Team_B").withParent(TEAM_A).build();
    public static final Group AUTOMATION = new GroupBuilder().withName("Automation").build();

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addTeam(group);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(
            TEAM_OMEGA, TEAM_A, TEAM_ALPHA, TEAM_B, TEAM_BETA, AUTOMATION, TEAM_GAMMA));
    }
}
