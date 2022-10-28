package coydir.logic.commands;

import static coydir.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static coydir.logic.parser.CliSyntax.PREFIX_ID;
import static coydir.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static java.util.Objects.requireNonNull;

import java.util.List;

import coydir.commons.core.Messages;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;
import coydir.model.person.Person;


/**
 * Add leave for employee
 */
public class AddLeaveCommand extends Command {

    public static final String COMMAND_WORD = "add-leave";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a leave period to an employee.\n"
            + "Parameters: "
            + PREFIX_ID + "ID "
            + PREFIX_STARTDATE + "START DATE "
            + PREFIX_ENDDATE + "END DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "1 "
            + PREFIX_STARTDATE + "01-01-2022 "
            + PREFIX_ENDDATE + "06-01-2022 ";

    public static final String MESSAGE_LEAVE_ADDED_SUCCESS = "Leave added successfully for %1$s";
    public static final String MESSAGE_DUPLICATE_LEAVE = "This leave period already exists";
    public static final String MESSAGE_OVERLAPPING_LEAVE = "Overlapping leaves are not allowed";
    private EmployeeId targetId;
    private Leave leave;

    /**
     * Constructor for AddLeaveCommand object.
     * @param targetid of the employee.
     * @param leave Leave object to add.
     */
    public AddLeaveCommand(EmployeeId targetid, Leave leave) {
        this.targetId = targetid;
        this.leave = leave;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getDatabase().getPersonList();
        for (Person person : lastShownList) {
            if (person.getEmployeeId().equals(targetId)) {
                if (person.getLeavesLeft() < leave.getTotalDays()) {
                    throw new CommandException(Messages.MESSAGE_INSUFFICIENT_LEAVES);
                } else if (person.getLeaves().contains(leave)) {
                    throw new CommandException(MESSAGE_DUPLICATE_LEAVE);
                }
                for (Leave otherLeave: person.getLeaves()) {
                    if (leave.isOverlapping(otherLeave)) {
                        throw new CommandException(MESSAGE_OVERLAPPING_LEAVE);
                    }
                }
                person.addLeave(leave);
                person.setLeavesLeft(person.getLeavesLeft() - leave.getTotalDays());
                return new CommandResult(String.format(MESSAGE_LEAVE_ADDED_SUCCESS, person.getName()));
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
