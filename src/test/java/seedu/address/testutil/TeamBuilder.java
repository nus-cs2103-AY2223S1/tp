package seedu.address.testutil;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.model.team.Name;
import seedu.address.model.team.Team;

/**
 * A utility class to help with building Team objects.
 */
public class TeamBuilder {

    public static final String DEFAULT_TEAM_NAME = "GUI";

    private Name name;
    private UniquePersonList members = new UniquePersonList();
    private UniqueTaskList tasks = new UniqueTaskList();

    /**
     * Creates a {@code TeamBuilder} with the default details.
     */
    public TeamBuilder() {
        name = new seedu.address.model.team.Name(DEFAULT_TEAM_NAME);
    }

    /**
     * Creates a {@code TeamBuilder} with the details of teamToCopy.
     */
    public TeamBuilder(Team teamToCopy) {
        name = teamToCopy.getName();
        members = teamToCopy.getMembers();
        tasks = teamToCopy.getTasks();
    }

    /**
     * Sets the {@code Name} of the {@code Team} that we are building.
     */
    public TeamBuilder withName(String name) {
        this.name = new seedu.address.model.team.Name(name);
        return this;
    }

    /**
     * Inserts the {@code members} into a {@code UniquePersonList} and set it to the {@code Team} that we are building.
     */
    public TeamBuilder withMembers(Person... members) {
        this.members = new UniquePersonList();
        for (Person p : members) {
            this.members.add(p);
        }
        return this;
    }

    /**
     * Inserts the {@code tasks} into a {@code UniqueTaskList} and set it to the {@code Team} that we are building.
     */
    public TeamBuilder withTasks(Task... tasks) {
        this.tasks = new UniqueTaskList();
        for (Task t : tasks) {
            TaskBuilder builder = new TaskBuilder(t);
            this.tasks.add(builder.build());
        }
        return this;
    }

    public Team build() {
        return new Team(name, tasks, members);
    }


}
