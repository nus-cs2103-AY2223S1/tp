package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_ASSIGNEES_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_DEADLINE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_INDEX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_NAME_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_TASK_ASSIGNEES;
import static seedu.address.logic.parser.CliSyntax.LABEL_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.LABEL_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.LABEL_TASK_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.LocalDateTimeConverter;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskName;

/**
 * Edits the details of an existing task in TruthTable.
 */
@CommandLine.Command(name = EditTaskCommand.COMMAND_WORD,
        aliases = {EditTaskCommand.ALIAS}, mixinStandardHelpOptions = true)
public class EditTaskCommand extends Command {
    public static final String COMMAND_WORD = "task";
    public static final String ALIAS = "ta";
    public static final String FULL_COMMAND = EditCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to edit a task's details.\n";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited task: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "A task with the same name already exists.";
    public static final String MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS = "Invalid member index provided";

    private final EditTaskDescriptor editTaskDescriptor;

    @CommandLine.Parameters(arity = "1", paramLabel = LABEL_TASK_INDEX, description = FLAG_TASK_INDEX_DESCRIPTION)
    private Index index;

    @CommandLine.ArgGroup(exclusive = false, multiplicity = "1")
    private Arguments arguments;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    /**
     * Creates an EditTaskCommand to edit a {@code Task}.
     */
    public EditTaskCommand() {
        this.editTaskDescriptor = new EditTaskDescriptor();
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskCommand.EditTaskDescriptor editTaskDescriptor) {
        assert editTaskDescriptor != null;

        TaskName updatedName = editTaskDescriptor.getName().orElse(taskToEdit.getName());
        LocalDateTime updatedDeadline;
        if (editTaskDescriptor.getDeadline().isEmpty()) {
            updatedDeadline = taskToEdit.getDeadline().orElse(null);
        } else {
            updatedDeadline = editTaskDescriptor.getDeadline().get();
        }
        boolean updatedIsComplete = taskToEdit.isComplete();
        List<Person> assignees;
        if (editTaskDescriptor.getAssignees().isEmpty()) {
            assignees = taskToEdit.getAssigneesList();
        } else {
            assignees = List.of();
        }
        return new Task(updatedName, assignees, updatedIsComplete, updatedDeadline);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);

        editTaskDescriptor.setAssignees((arguments.assignees));
        if (arguments.deadline != null) {
            editTaskDescriptor.setDeadline(arguments.deadline);
        }
        if (arguments.name != null) {
            editTaskDescriptor.setName(arguments.name);
        }
        List<Task> taskList = model.getTeam().getFilteredTaskList();

        if (index.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = taskList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
        List<Person> memberList = model.getTeam().getTeamMembers();

        if (editTaskDescriptor.getAssignees().isPresent()) {
            List<Index> assignees = editTaskDescriptor.getAssignees().get();
            for (Index index : assignees) {
                if (index.getZeroBased() >= memberList.size()) {
                    throw new CommandException(MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS);
                }
            }
            List<Person> assigneePersonList = assignees.stream()
                    .map(index -> memberList.get(index.getZeroBased()))
                    .collect(Collectors.toList());

            for (Person assignee : assigneePersonList) {
                editedTask.addAssignee(assignee);
            }
        }
        if (!taskToEdit.equals(editedTask) && model.getTeam().hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));

    }

    private static class Arguments {
        @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG},
                paramLabel = LABEL_TASK_NAME,
                description = FLAG_TASK_NAME_DESCRIPTION)
        private TaskName name;

        @CommandLine.Option(names = {FLAG_DEADLINE_STR, FLAG_DEADLINE_STR_LONG},
                paramLabel = LABEL_TASK_DEADLINE,
                parameterConsumer = LocalDateTimeConverter.class, description = FLAG_TASK_DEADLINE_DESCRIPTION)
        private LocalDateTime deadline;

        @CommandLine.Option(names = {FLAG_ASSIGNEE_STR, FLAG_ASSIGNEE_STR_LONG},
                paramLabel = LABEL_TASK_ASSIGNEES,
                description = FLAG_TASK_ASSIGNEES_DESCRIPTION, arity = "*")
        private List<Index> assignees = new ArrayList<>();

    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private TaskName name;

        private LocalDateTime deadline;

        private List<Index> assignees;

        public EditTaskDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setName(toCopy.name);
            setDeadline(toCopy.deadline);
            setAssignees(toCopy.assignees);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, deadline);
        }

        public Optional<TaskName> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(TaskName name) {
            this.name = name;
        }

        public Optional<LocalDateTime> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setDeadline(LocalDateTime date) {
            this.deadline = date;
        }

        public Optional<List<Index>> getAssignees() {
            return Optional.ofNullable(assignees);
        }

        public void setAssignees(List<Index> assignees) {
            this.assignees = assignees;
        }

    }
}
