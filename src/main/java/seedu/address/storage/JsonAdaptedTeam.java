package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;

/**
 * Jackson-friendly version of {@link Team}
 */
public class JsonAdaptedTeam {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Team's %s field is missing!";

    private final String teamName;
    private final List<JsonAdaptedPerson> members = new ArrayList<>();
    private final List<JsonAdaptedLink> links = new ArrayList<>();
    private final List<JsonAdaptedTask> taskList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTeam} with the given person details and task list.
     */
    @JsonCreator
    public JsonAdaptedTeam(@JsonProperty("teamName") String teamName,
                           @JsonProperty("members") List<JsonAdaptedPerson> members,
                           @JsonProperty("taskList") List<JsonAdaptedTask> taskList,
                           @JsonProperty("links") List<JsonAdaptedLink> links) {
        this.teamName = teamName;

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
        teamName = source.getTeamName();
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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Team.class.getSimpleName()));
        }
        return new Team(teamName, modelMembers, modelTasks, modelLinks);
    }
}
