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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public static final String MESSAGE_USAGE =
            FULL_COMMAND + ": Edits a current task identified by the index number used in the displayed task list. \n"
                    + "Existing values will be overwritten by the input values. \n"
                    + "Parameters: INDEX (must be a positive integer) "
                    + FLAG_NAME_STR + " NAME "
                    + FLAG_DEADLINE_STR + " DEADLINE \n"
                    + FLAG_ASSIGNEE_STR + "MEMBER_INDEX \n"
                    + "Example: " + FULL_COMMAND + " 1 "
                    + FLAG_NAME_STR + " \"Review PR\" "
                    + FLAG_DEADLINE_STR + " \"02-Dec-2022 23:59\" "
                    + FLAG_ASSIGNEE_STR + "1";
    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "A task with the same name already exists.";
    public static final String MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS = "Invalid member index provided";
    public static final String MESSAGE_DEADLINE_BADLY_FORMATTED = "Deadline is badly formatted.";

    private final EditTaskDescriptor editTaskDescriptor;

    @CommandLine.Parameters(arity = "1", description = FLAG_TASK_INDEX_DESCRIPTION)
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
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        if (arguments.assignees.length != 1 || !Arrays.asList(arguments.assignees).contains("")) {
            editTaskDescriptor.setAssignees(Arrays.asList(arguments.assignees));
        }
        if (arguments.deadline != null) {
            editTaskDescriptor.setDeadline(arguments.deadline);
        }
        if (arguments.taskName != null) {
            editTaskDescriptor.setName(arguments.taskName);
        }
        List<Task> taskList = model.getTeam().getTaskList();

        if (index.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = taskList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
        List<Person> memberList = model.getTeam().getTeamMembers();
        if (editTaskDescriptor.getAssignees().isPresent()) {
            for (int i = 0; i < arguments.assignees.length; i++) {
                if (Integer.parseInt(editTaskDescriptor.getAssignees().get().get(i)) < 1
                        || Integer.parseInt(editTaskDescriptor.getAssignees().get().get(i)) > memberList.size()) {
                    throw new CommandException(MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS);
                }
            }
            List<Person> assigneePersonList = new java.util.ArrayList<>(List.of());
            for (String index : editTaskDescriptor.assignees) {
                int assigneeIndex = Integer.parseInt(index);
                assigneePersonList.add(memberList.get(assigneeIndex - 1));
            }
            for (Person assignee : assigneePersonList) {
                editedTask.assignTo(assignee);
            }
        }
        if (!taskToEdit.equals(editedTask) && model.getTeam().hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index) && arguments.equals(e.arguments);
    }

    private static class Arguments {
        @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, description = FLAG_TASK_NAME_DESCRIPTION)
        private TaskName taskName;

        @CommandLine.Option(names = {FLAG_DEADLINE_STR, FLAG_DEADLINE_STR_LONG},
                parameterConsumer = LocalDateTimeConverter.class, description = FLAG_TASK_DEADLINE_DESCRIPTION)
        private LocalDateTime deadline;

        @CommandLine.Option(names = {FLAG_ASSIGNEE_STR, FLAG_ASSIGNEE_STR_LONG}, defaultValue = "", description =
                FLAG_TASK_ASSIGNEES_DESCRIPTION, arity = "*")
        private String[] assignees;

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            } else if (other instanceof Arguments) {
                Arguments target = (Arguments) other;
                return this.taskName == null ? false : this.taskName.equals(target.taskName)
                        && this.deadline == null ? false : this.deadline.equals(target.deadline)
                        && Arrays.equals(assignees, target.assignees);
            } else {
                return false;
            }
        }
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private TaskName name;

        private LocalDateTime deadline;

        private List<String> assignees;

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

        public Optional<List<String>> getAssignees() {
            return Optional.ofNullable(assignees);
        }

        public void setAssignees(List<String> assignees) {
            this.assignees = assignees;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskCommand.EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskCommand.EditTaskDescriptor e = (EditTaskCommand.EditTaskDescriptor) other;

            return getName().equals(e.getName())
                    && getDeadline().equals(e.getDeadline())
                    && getAssignees().equals(e.getAssignees());
        }

    }
}
