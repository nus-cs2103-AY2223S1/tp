package seedu.address.testutil;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.model.team.TeamName;
import seedu.address.model.team.Team;

/**
 * A utility class to help with building Team objects.
 */
public class TeamBuilder {

    public static final String DEFAULT_TEAM_NAME = "GUI";

    private TeamName teamName;
    private UniquePersonList members = new UniquePersonList();
    private UniqueTaskList tasks = new UniqueTaskList();

    /**
     * Creates a {@code TeamBuilder} with the default details.
     */
    public TeamBuilder() {
        teamName = TeamName.of(DEFAULT_TEAM_NAME);
    }

    /**
     * Creates a {@code TeamBuilder} with the details of teamToCopy.
     */
    public TeamBuilder(Team teamToCopy) {
        teamName = TeamName.of(teamToCopy.getName().toString());

        UniquePersonList newMembers = new UniquePersonList();
        for (Person p : teamToCopy.getMembers()) {
            newMembers.add(new PersonBuilder(p).build());
        }
        members = newMembers;

        UniqueTaskList newTasks = new UniqueTaskList();
        for (Task t : teamToCopy.getTasks()) {
            newTasks.add(new TaskBuilder(t).build());
        }
        tasks = newTasks;
    }

    /**
     * Sets the {@code Name} of the {@code Team} that we are building.
     */
    public TeamBuilder withName(String name) {
        this.teamName = TeamName.of(name);
        return this;
    }

    /**
     * Inserts the {@code members} into a {@code UniquePersonList} and set it to the {@code Team} that we are building.
     */
    public TeamBuilder withMembers(Person... members) {
        this.members = new UniquePersonList();
        for (Person p : members) {
            this.members.add(new PersonBuilder(p).build());
        }
        return this;
    }

    /**
     * Inserts the {@code tasks} into a {@code UniqueTaskList} and set it to the {@code Team} that we are building.
     */
    public TeamBuilder withTasks(Task... tasks) {
        this.tasks = new UniqueTaskList();
        for (Task t : tasks) {
            this.tasks.add(new TaskBuilder(t).build());
        }
        return this;
    }

    public Team build() {
        return new Team(teamName, tasks, members);
    }


}
