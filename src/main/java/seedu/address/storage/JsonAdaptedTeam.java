package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.team.Name;
import seedu.address.model.team.Team;



/**
 * Jackson-friendly version of {@link Team}.
 */
public class JsonAdaptedTeam {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Team's %s field is missing!";


    private final String teamName;
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedPerson> members = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTeam} with the given team details.
     */
    @JsonCreator
    public JsonAdaptedTeam(@JsonProperty("name") String teamName, @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
        @JsonProperty("members") List<JsonAdaptedPerson> members) {
        this.teamName = teamName;
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }
        if (members != null) {
            this.members.addAll(members);
        }
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTeam(Team source) {
        teamName = source.getName().fullName;

        List<Task> taskList = source.getTasksList();
        if (taskList != null) {
            tasks.addAll(taskList.stream()
                    .map(JsonAdaptedTask::new)
                    .collect(Collectors.toList()));

        }

        List<Person> memberList = source.getMemberList();
        if (memberList != null) {
            members.addAll(memberList.stream()
                    .map(JsonAdaptedPerson::new)
                    .collect(Collectors.toList()));
        }


    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Team toModelType() throws IllegalValueException {
        if (teamName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if (!Name.isValidName(teamName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final seedu.address.model.team.Name modelName = new Name(teamName);

        final List<Task> modelTasks = new ArrayList<>();

        final List<Person> modelMembers = new ArrayList<>();

        for (JsonAdaptedTask task : tasks) {
            modelTasks.add(task.toModelType());
        }

        for (JsonAdaptedPerson member : members) {
            modelMembers.add(member.toModelType());
        }

        return new Team(modelName, modelTasks, modelMembers);
    }
}
