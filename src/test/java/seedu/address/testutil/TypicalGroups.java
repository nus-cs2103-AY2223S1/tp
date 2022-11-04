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

    public static final Group ALPHA = new GroupBuilder().withName("Team_Alpha")
            .withTags("Important", "Gaming").withAttribute("Meetup", "Saturday").build();
    public static final Group BETA = new GroupBuilder().withName("Team_Beta")
            .withTags("Work", "School").withAttribute("Review", "Lecture 3")
            .withParent(ALPHA).build();
    public static final Group GAMMA = new GroupBuilder().withName("Team_Gamma")
            .withTags("Work", "Internship").withAttribute("Number of leave", "3").build();


    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addTeam(group);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(ALPHA, BETA, GAMMA));
    }
}
