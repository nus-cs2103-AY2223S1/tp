package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.model.team.Team;
/**
 * A utility class containing a list of {@code Team} objects to be used in tests.
 */
public class TypicalTeams {

    public static final Team FIRST = new Team("first", new ArrayList<>(), new ArrayList<>());
    public static final Team FIRST_DUPLICATE = new Team("first" , new ArrayList<>(), new ArrayList<>());
    public static final Team SECOND = new Team("second", new ArrayList<>(), new ArrayList<>());

}
