package coydir.logic.commands;

import static coydir.logic.parser.CliSyntax.PREFIX_ID;
import static coydir.logic.parser.CliSyntax.PREFIX_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import coydir.commons.core.Messages;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;
import coydir.model.person.Person;

/**
 * Deletes a leave from the person.
 */
public class DeleteLeaveCommand extends Command {

    public static final String COMMAND_WORD = "delete-leave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a leave period for an employee.\n"
            + "Parameters: "
            + PREFIX_ID + "ID "
            + PREFIX_INDEX + "INDEX "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "1 "
            + PREFIX_INDEX + "1 ";

    public static final String MESSAGE_LEAVE_REMOVE_SUCCESS = "Leave removed successfully for %1$s";
    public static final String MESSAGE_NO_SUCH_LEAVE = "There is no such leave";
    public static final String MESSAGE_INVALID_INDEX = "Invalid index given";
    private EmployeeId targetId;
    private int index;

    /**
     * Creates a DeleteCommand object.
     * @param targetid of the employee to operate on.
     * @param index index of leave in queue to remove.
     */
    public DeleteLeaveCommand(EmployeeId targetid, int index) {
        this.targetId = targetid;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getDatabase().getPersonList();
        if (index < 0) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }
        Person targetPerson = null;
        for (Person person : lastShownList) {
            if (person.getEmployeeId().equals(targetId)) {
                targetPerson = person;
                break;
            }
        }
        if (targetPerson == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Queue<Leave> oldLeaves = targetPerson.getLeaves();
        if (index > oldLeaves.size()) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }
        Queue<Leave> newLeaves = new PriorityQueue<>(oldLeaves);
        int counter = 1;
        while (counter++ < index) {
            newLeaves.remove();
        }
        Leave removedLeave = newLeaves.remove();
        oldLeaves.remove(removedLeave);
        targetPerson.setLeavesLeft(targetPerson.getLeavesLeft() + removedLeave.getTotalDays());
        return new CommandResult(String.format(MESSAGE_LEAVE_REMOVE_SUCCESS, targetPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLeaveCommand // instanceof handles nulls
                && targetId.equals(((DeleteLeaveCommand) other).targetId)
                && index == ((DeleteLeaveCommand) other).index); // state check
    }
}
