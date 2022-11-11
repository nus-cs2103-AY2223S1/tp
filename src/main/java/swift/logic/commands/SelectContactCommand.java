package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static swift.model.Model.PREDICATE_SHOW_ALL_BRIDGE;
import static swift.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.Prefix;
import swift.model.Model;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * Selects a task identified using it's displayed index from the address book.
 */
public class SelectContactCommand extends Command {

    public static final String COMMAND_WORD = "select_contact";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>(List.of(new Prefix("", "contact_index")));

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the contact identified by the index number used in the displayed contact list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_CONTACT_SUCCESS = "Contact Selected!";

    private final Index targetIndex;

    public SelectContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person selectedPerson = lastShownPersonList.get(targetIndex.getZeroBased());
        model.updateFilteredPersonList((person) -> person.equals(selectedPerson));

        model.updateFilteredBridgeList(PREDICATE_SHOW_ALL_BRIDGE);
        model.updateFilteredBridgeList((bridge) -> bridge.getPersonId().equals(selectedPerson.getId()));
        List<PersonTaskBridge> bridgeList = model.getFilteredBridgeList();

        // Predicate to check whether task exists within a bridgeList
        Predicate<Task> isTaskExist = (task) -> bridgeList.stream()
                .anyMatch((bridge) -> bridge.getTaskId().equals(task.getId()));

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.updateFilteredTaskList(isTaskExist);

        return new CommandResult(Messages.MESSAGE_PERSONS_SELECTED_OVERVIEW, CommandType.SELECT_CONTACT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectContactCommand // instanceof handles nulls
                && targetIndex.equals(((SelectContactCommand) other).targetIndex)); // state check
    }
}
