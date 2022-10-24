package coydir.logic.commands;

import static coydir.logic.parser.CliSyntax.PREFIX_ID;
import static coydir.logic.parser.CliSyntax.PREFIX_STARTDATE;

import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;

import static coydir.logic.parser.CliSyntax.PREFIX_ENDDATE;

public class AddLeaveCommand extends Command {
    
    public static final String COMMAND_WORD = "addleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a leave period to an employee.\n"
            + "Parameters: "
            + PREFIX_ID + "ID"
            + PREFIX_STARTDATE + "START DATE"
            + PREFIX_ENDDATE + "END DATE"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "1"
            + PREFIX_STARTDATE + "010122"
            + PREFIX_ENDDATE + "060122";
    
    public static final String MESSAGE_SUCCESS = "Leave added successfully";
    private EmployeeId targetId;
    private Leave leave;

    public AddLeaveCommand(EmployeeId targetid, Leave leave ) {
        this.targetId = targetid;
        this.leave = leave;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO Auto-generated method stub
        return null;
    }

    
}
