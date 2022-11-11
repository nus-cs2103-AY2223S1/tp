package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.team.Description;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * Jackson-friendly version of {@link Team}
 */
public class JsonAdaptedTeam {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Team's %s field is missing!";
    private final String teamName;
    private final String description;
    private final List<JsonAdaptedPerson> members = new ArrayList<>();
    private final List<JsonAdaptedLink> links = new ArrayList<>();
    private final List<JsonAdaptedTask> taskList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTeam} with the given person details and task list.
     */
    @JsonCreator
    public JsonAdaptedTeam(@JsonProperty("teamName") String teamName,
                           @JsonProperty("description") String description,
                           @JsonProperty("members") List<JsonAdaptedPerson> members,
                           @JsonProperty("taskList") List<JsonAdaptedTask> taskList,
                           @JsonProperty("links") List<JsonAdaptedLink> links) {
        this.teamName = teamName;
        this.description = description;

        if (members != null) {
            this.members.addAll(members);
        }

        if (taskList != null) {
            this.taskList.addAll(taskList);
        }

        if (links != null) {
            this.links.addAll(links);
        }
    }

    /**
     * Converts a given {@code Team} into this class for Jackson use.
     */
    public JsonAdaptedTeam(Team source) {
        teamName = source.getTeamName().teamName;
        description = source.getDescription().description;
        members.addAll(source.getTeamMembers().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
        taskList.addAll(source.getTaskList().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
        links.addAll(source.getLinkList().stream()
                .map(JsonAdaptedLink::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Team} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person or task.
     */
    public Team toModelType() throws IllegalValueException {
        final List<Person> modelMembers = new ArrayList<>();
        final List<Task> modelTasks = new ArrayList<>();
        final List<Link> modelLinks = new ArrayList<>();

        for (JsonAdaptedPerson member : members) {
            modelMembers.add(member.toModelType());
        }
        for (JsonAdaptedTask task : taskList) {
            modelTasks.add(task.toModelType());
        }

        for (JsonAdaptedLink link : links) {
            modelLinks.add(link.toModelType());
        }

        if (teamName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TeamName.class.getSimpleName()));
        }

        if (!TeamName.isValidTeamName(teamName)) {
            throw new IllegalValueException(TeamName.MESSAGE_CONSTRAINTS);
        }

        TeamName teamNameModel = new TeamName(teamName);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }

        if (!Description.isValidTeamDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        Description descriptionModel = new Description(description);

        return new Team(teamNameModel, descriptionModel, modelMembers, modelTasks, modelLinks);
    }
}
