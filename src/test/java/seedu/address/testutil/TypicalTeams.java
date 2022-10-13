package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.person.Person;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;
/**
 * A utility class containing a list of {@code Team} objects to be used in tests.
 */
public class TypicalTeams {

    public static final ArrayList<Person> TYPICAL_MEMBERS = new ArrayList<>(
        Arrays.asList(TypicalPersons.BENSON, TypicalPersons.ALICE));
    public static final ArrayList<Task> TYPICAL_TASKS = new ArrayList<Task>(
        Arrays.asList(TypicalTasks.TASK_1, TypicalTasks.TASK_2));

    public static final Team FIRST = new Team("first", new ArrayList<>(), new ArrayList<>());
    public static final Team FIRST_DUPLICATE = new Team("first" , new ArrayList<>(), new ArrayList<>());
    public static final Team SECOND = new Team("second", TYPICAL_MEMBERS, TYPICAL_TASKS);

}
