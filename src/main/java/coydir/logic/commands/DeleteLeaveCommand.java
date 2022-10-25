package coydir.logic.commands;

import static java.util.Objects.requireNonNull;

import static coydir.logic.parser.CliSyntax.PREFIX_ID;
import static coydir.logic.parser.CliSyntax.PREFIX_STARTDATE;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import coydir.commons.core.Messages;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;
import coydir.model.person.Person;

public class DeleteLeaveCommand extends Command {
    
    public static final String COMMAND_WORD = "deleteleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a leave period for an employee.\n"
            + "Parameters: "
            + PREFIX_ID + "ID "
            + PREFIX_STARTDATE + "START DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "1 "
            + PREFIX_STARTDATE + "01-01-22 ";

    public static final String MESSAGE_LEAVE_REMOVE_SUCCESS = "Leave removed successfully for %1$s";
    public static final String MESSAGE_NO_SUCH_LEAVE = "There is no such leave";
    public static final String MESSAGE_INVALID_INDEX = "Invalid index given";
    private EmployeeId targetId;
    private int index;
    
    public static class CustomLeaveComparator implements Comparator<Leave> {
        @Override
        public int compare(Leave o1, Leave o2) {
            return o1.startDate.compareTo(o2.startDate);
        }
    }

    public DeleteLeaveCommand(EmployeeId targetid, int index ) {
        this.targetId = targetid;
        this.index = index;
        
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index < 0) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }
        for (Person person : lastShownList) {
            if (person.getEmployeeId().equals(targetId)) {
                if (index >= person.getLeaves().size()) {
                    throw new CommandException(MESSAGE_INVALID_INDEX);
                }
                Leave removedLeave = null;
                Queue<Leave> newLeaves = new PriorityQueue<>(new CustomLeaveComparator());
                Queue<Leave> oldLeaves = person.getLeaves();
                int counter = 0;
                for (Leave leave: oldLeaves) {
                   Leave temp = oldLeaves.remove();
                   if (counter != index) {
                        newLeaves.add(temp);
                   } else {
                        removedLeave = leave;
                   }
                }
                if (removedLeave == null) {
                    throw new CommandException(MESSAGE_NO_SUCH_LEAVE);
                }
                return new CommandResult(String.format(MESSAGE_LEAVE_REMOVE_SUCCESS,person.getName()));
                    }
                }
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
                    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLeaveCommand // instanceof handles nulls
                && targetId.equals(((DeleteLeaveCommand) other).targetId)
                && index == ((DeleteLeaveCommand) other).index); // state check
    }    
}
