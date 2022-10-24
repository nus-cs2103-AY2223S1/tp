package coydir.logic.commands;

import static java.util.Objects.requireNonNull;

import static coydir.logic.parser.CliSyntax.PREFIX_ID;
import static coydir.logic.parser.CliSyntax.PREFIX_STARTDATE;

import java.util.List;

import coydir.commons.core.Messages;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;
import coydir.model.person.Person;

import static coydir.logic.parser.CliSyntax.PREFIX_ENDDATE;

public class AddLeaveCommand extends Command {
    
    public static final String COMMAND_WORD = "addleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a leave period to an employee.\n"
            + "Parameters: "
            + PREFIX_ID + "ID "
            + PREFIX_STARTDATE + "START DATE "
            + PREFIX_ENDDATE + "END DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "1 "
            + PREFIX_STARTDATE + "010122 "
            + PREFIX_ENDDATE + "060122 ";
    
    public static final String MESSAGE_LEAVE_ADDED_SUCCESS = "Leave added successfully for %1$s";
    public static final String MESSAGE_DUPLICATE_LEAVE = "This leave period already exists";
    public static final String MESSAGE_OVERLAPPING_LEAVE = "You cannot have overlapping leaves";
    private EmployeeId targetId;
    private Leave leave;

    public AddLeaveCommand(EmployeeId targetid, Leave leave ) {
        this.targetId = targetid;
        this.leave = leave;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        for (Person person : lastShownList) {
            if (person.getEmployeeId().equals(targetId)) {
                if (person.getLeavesLeft() < leave.getTotalDays()) {
                    throw new CommandException(Messages.MESSAGE_INSUFFICIENT_LEAVES);
                }
                else if (person.getLeaves().contains(leave)) {
                    throw new CommandException(MESSAGE_DUPLICATE_LEAVE);
                }
                for (Leave otherLeave: person.getLeaves()) {
                    if (leave.isOverlapping(otherLeave)) {
                        throw new CommandException(MESSAGE_OVERLAPPING_LEAVE); 
                    }
                }
                person.addLeave(leave);
                return new CommandResult(String.format(MESSAGE_LEAVE_ADDED_SUCCESS,person.getName()));
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLeaveCommand // instanceof handles nulls
                && targetId.equals(((AddLeaveCommand) other).targetId)
                && leave.equals(((AddLeaveCommand) other).leave)); // state check
    }    
}
