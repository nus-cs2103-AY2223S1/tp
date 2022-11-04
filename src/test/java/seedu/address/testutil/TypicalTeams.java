package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalTasks.PACK;
import static seedu.address.testutil.TypicalTasks.REVIEW;
import static seedu.address.testutil.TypicalTasks.STUDY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.team.Team;

/**
 * A utility class containing a list of {@code Teams} objects to be used in tests.
 */
public class TypicalTeams {

    public static final Team FRONTEND = new TeamBuilder().withName("Frontend")
            .withMembers(ALICE, BENSON, CARL).withTasks(STUDY, REVIEW, PACK).build();

    public static final Team FRONTENDCOPY = new TeamBuilder().withName("Frontend")
            .withMembers(ALICE, BENSON, CARL).withTasks(STUDY, REVIEW, PACK).build();

    public static final Team BACKEND = new TeamBuilder().withName("Backend")
            .withMembers(DANIEL, ELLE).build();

    public static final Team INTERNS = new TeamBuilder().withName("Interns")
            .withMembers(ALICE, BENSON, CARL).build();

    private TypicalTeams() {}

    public static AddressBook getTypicalAddressBookWithTeams() {
        AddressBook ab = TypicalPersons.getTypicalAddressBookWithPersons();
        for (Team t : getTypicalTeams()) {
            ab.addTeam(t);
        }
        return ab;
    }

    public static AddressBook getTypicalAddressBookWithTeams2() {
        AddressBook ab = TypicalPersons.getTypicalAddressBookWithPersons();
        for (Team t : getTypicalTeams2()) {
            ab.addTeam(t);
        }
        return ab;
    }

    public static List<Team> getTypicalTeams() {
        return new ArrayList<>(Arrays.asList(new TeamBuilder(FRONTEND).build(), new TeamBuilder(BACKEND).build()));
    }

    public static List<Team> getTypicalTeams2() {
        return new ArrayList<>(Arrays.asList(new TeamBuilder(FRONTENDCOPY).build(), new TeamBuilder(BACKEND).build()));
    }
}
