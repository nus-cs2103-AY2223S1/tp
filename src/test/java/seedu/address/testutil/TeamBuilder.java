package seedu.address.testutil;

import java.util.List;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.team.Description;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskList;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;
import seedu.address.model.team.UniqueLinkList;

public class TeamBuilder {
    public static final String DEFAULT_NAME = "default";
    public static final String DEFAULT_DESCRIPTION = "default description";
    public static final Person DEFAULT_MEMBER_ONE = TypicalPersons.ALICE;
    public static final Person DEFAULT_MEMBER_TWO = TypicalPersons.BENSON;
    public static final Task DEFAULT_TASK_ONE = TypicalTasks.TASK_1;
    public static final Task DEFAULT_TASK_TWO = TypicalTasks.TASK_2;
    public static final Link DEFAULT_LINK_ONE = TypicalLinks.LINK_GOOGLE;
    public static final Link DEFAULT_LINK_TWO = TypicalLinks.LINK_FACEBOOK;

    private TeamName name;
    private Description description;
    private UniquePersonList members = new UniquePersonList();
    private TaskList tasks = new TaskList();
    private UniqueLinkList links = new UniqueLinkList();

    /**
     * Creates a {@code TeamBuilder} with the default details.
     */
    public TeamBuilder() {
        name = new TeamName(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        members.add(DEFAULT_MEMBER_ONE);
        members.add(DEFAULT_MEMBER_TWO);
        tasks.add(DEFAULT_TASK_ONE);
        tasks.add(DEFAULT_TASK_TWO);
        links.add(DEFAULT_LINK_ONE);
        links.add(DEFAULT_LINK_TWO);
    }

    /**
     * Initializes the TeamBuilder with the data of {@code teamToCopy}.
     */
    public TeamBuilder(Team teamToCopy) {
        name = teamToCopy.getTeamName();
        description = teamToCopy.getDescription();
        members.setPersons(teamToCopy.getTeamMembers());
        tasks.setTasks(teamToCopy.getTaskList());
        links.setLinks(teamToCopy.getLinkList());
    }

    /**
     * Sets the {@code TeamName} of the {@code Team} that we are building.
     */
    public TeamBuilder withName(String name) {
        this.name = new TeamName(name);
        return this;
    }

    /**
     * Sets the {@code Description of the {@code Team} that we are building.
     */
    public TeamBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Assigns the {@code members} to the {@code Team} that we are building.
     */
    public TeamBuilder withMembers(List<Person> members) {
        this.members.setPersons(members);
        return this;
    }

    /**
     * Assigns the {@code members} to the {@code Team} that we are building.
     */
    public TeamBuilder withTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
        return this;
    }

    /**
     * Assigns the {@code members} to the {@code Team} that we are building.
     */
    public TeamBuilder withLinks(List<Link> links) {
        this.links.setLinks(links);
        return this;
    }

    public Team build() {
        return new Team(name, description,
                members.asUnmodifiableObservableList(),
                tasks.asUnmodifiableObservableList(),
                links.asUnmodifiableObservableList());
    }
}
