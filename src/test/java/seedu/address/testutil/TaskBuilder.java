package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskName;
import seedu.address.model.util.SampleDataUtil;

public class TaskBuilder {
    public static final String DEFAULT_NAME = "Code Review";
    public static final Person DEFAULT_ASSIGNEE_ONE = TypicalPersons.ALICE;
    public static final Person DEFAULT_ASSIGNEE_TWO = TypicalPersons.BENSON;
    public static final String DEFAULT_DEADLINE = "2022-12-02 23:59";

    private TaskName name;

    private UniquePersonList assignees = new UniquePersonList();

    private Optional<LocalDateTime> deadline;

    private boolean completionStatus = false;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new TaskName(DEFAULT_NAME);
        assignees.add(DEFAULT_ASSIGNEE_ONE);
        assignees.add(DEFAULT_ASSIGNEE_TWO);
        deadline = Optional.of(LocalDateTime.parse(DEFAULT_DEADLINE, formatter));
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        assignees.setPersons(taskToCopy.getAssigneesList());
        deadline = taskToCopy.getDeadline();
        completionStatus = taskToCopy.isComplete();
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new TaskName(name);
        return this;
    }

    /**
     * Assigns the {@code assignees} to the {@code Task} that we are building.
     */
    public TaskBuilder withAssignees(List<Person> assignees) {
        this.assignees.setPersons(assignees);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = Optional.of(LocalDateTime.parse(deadline, formatter));
        return this;
    }

    /**
     * Sets the completion status of the {@code Task} that we are building.
     */
    public TaskBuilder withCompletionStatus(boolean status) {
        this.completionStatus = status;
        return this;
    }


    public Task build() {
        return new Task(name, assignees.asUnmodifiableObservableList(), completionStatus , deadline.get());
    }
}
