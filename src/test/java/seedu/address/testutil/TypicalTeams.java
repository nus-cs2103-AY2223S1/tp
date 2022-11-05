package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.person.Person;
import seedu.address.model.team.Description;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * A utility class containing a list of {@code Team} objects to be used in tests.
 */
public class TypicalTeams {

    public static final ArrayList<Person> TYPICAL_MEMBERS = new ArrayList<>(
            Arrays.asList(TypicalPersons.BENSON, TypicalPersons.ALICE));
    public static final ArrayList<Task> TYPICAL_TASKS = new ArrayList<>(
            Arrays.asList(TypicalTasks.TASK_1, TypicalTasks.TASK_2));

    public static final ArrayList<Link> TYPICAL_LINKS = new ArrayList<>(
            Arrays.asList(TypicalLinks.LINK_GOOGLE, TypicalLinks.LINK_FACEBOOK));

    public static final Team FIRST = new Team(new TeamName("first"),
            Description.DEFAULT_DESCRIPTION);
    public static final Team FIRST_DUPLICATE = new Team(new TeamName("first"),
            Description.NO_DESCRIPTION.DEFAULT_DESCRIPTION);
    public static final Team FIRST_TEAM_DETAILS = new Team(new TeamName("first"),
            Description.DEFAULT_DESCRIPTION, TYPICAL_MEMBERS, TYPICAL_TASKS, TYPICAL_LINKS);
    public static final Team SECOND = new Team( new TeamName("third"),
            Description.DEFAULT_DESCRIPTION);
}
