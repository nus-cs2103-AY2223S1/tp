package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static swift.logic.parser.CliSyntax.PREFIX_CONTACT;
import static swift.logic.parser.CliSyntax.PREFIX_TASK;
import static swift.model.Model.PREDICATE_SHOW_ALL_BRIDGE;

import java.util.ArrayList;
import java.util.List;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.Prefix;
import swift.model.Model;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * Unassigns a task to a contact identified using their displayed index from the
 * address book.
 */
public class UnassignCommand extends Command {
    public static final String COMMAND_WORD = "unassign";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>(
            List.of(PREFIX_CONTACT, PREFIX_TASK));

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns the task to the contact identified by the index numbers used in the displayed contact list.\n"
            + "Parameters: "
            + PREFIX_CONTACT + "CONTACT_INDEX "
            + PREFIX_TASK + "TASK_INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTACT + "1 "
            + PREFIX_TASK + "2";

    public static final String MESSAGE_UNASSIGN_SUCCESS = "Task %1$s unassigned to contact %2$s!";
    public static final String MESSAGE_BRIDGE_DOESNT_EXIST = "This task is not assigned to this contact!";

    private final Index contactIndex;
    private final Index taskIndex;

    /**
     * Creates an UnassignCommand to add the specified {@code Task} to the specified
     * {@code Person}
     *
     * @param contactIndex Index of the contact to assign the task to.
     * @param taskIndex    Index of the task to be assigned.
     */
    public UnassignCommand(Index contactIndex, Index taskIndex) {
        this.contactIndex = contactIndex;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (contactIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Person selectedPerson = lastShownPersonList.get(contactIndex.getZeroBased());
        Task selectedTask = lastShownTaskList.get(taskIndex.getZeroBased());
        PersonTaskBridge bridge = new PersonTaskBridge(selectedPerson.getId(), selectedTask.getId());

        if (!model.hasBridge(bridge)) {
            throw new CommandException(MESSAGE_BRIDGE_DOESNT_EXIST);
        }
        model.deleteBridge(bridge);

        model.updateFilteredBridgeList(PREDICATE_SHOW_ALL_BRIDGE);
        model.updateFilteredBridgeList(filteredBridge -> model.getFilteredPersonList().stream()
                .anyMatch(person -> person.getId().equals(filteredBridge.getPersonId())));

        return new CommandResult(String.format(MESSAGE_UNASSIGN_SUCCESS, selectedTask, selectedPerson),
                CommandType.UNASSIGN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnassignCommand // instanceof handles nulls
                && contactIndex.equals(((UnassignCommand) other).contactIndex)
                && taskIndex.equals(((UnassignCommand) other).taskIndex)); // state check
    }
}
